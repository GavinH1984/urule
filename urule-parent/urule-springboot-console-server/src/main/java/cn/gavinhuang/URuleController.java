package cn.gavinhuang;

import com.bstek.urule.Utils;
import com.bstek.urule.builder.KnowledgeBase;
import com.bstek.urule.builder.KnowledgeBuilder;
import com.bstek.urule.builder.ResourceBase;
import com.bstek.urule.console.User;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.servlet.common.CommonServletHandler;
import com.bstek.urule.console.servlet.respackage.HttpSessionKnowledgeCache;
import com.bstek.urule.runtime.cache.CacheUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
public class URuleController {

    @Resource
    private CommonServletHandler commonServletHandler;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private KnowledgeBuilder knowledgeBuilder;

    @Resource
    private HttpSessionKnowledgeCache httpSessionKnowledgeCache;

    @RequestMapping("/addrule")
    public String  AddRule(@RequestParam String data) throws Exception {

        // get the current ruleset file.
        List<Object> result = commonServletHandler.loadXml(data);
        Document document = (Document) result.get(0);
        Element element = document.getRootElement();

        int count = 0;
        DefaultElement rule_de=null;
        for (Object item: element.content() ) {
            DefaultElement de = (DefaultElement) item;
            if (de.getQName().getName().equals("rule")) {
                rule_de = de;
                count ++;
            }
        }

        int newRule_id = count + 1;

        // clone the rule element, and edit the element with necessary.
        DefaultElement new_de = (DefaultElement) rule_de.clone();
        new_de.attribute(0).setValue("rule" + newRule_id);

        // add it into the document.
        element.content().add(new_de);

        // conver it back to string.
        String update_content = document.asXML();

        User user = new User() {
            @Override
            public String getUsername() {
                return "admin";
            }

            @Override
            public String getCompanyId() {
                return "rest-update";
            }

            @Override
            public boolean isAdmin() {
                return true;
            }
        };


        // save it back to repos
        repositoryService.saveFile(data, update_content, true, "update by rest api, new rule: rule" + newRule_id, user);

        // refresh knowlege package
        RefreshKnowledgePackage("gavintest/gavin02", "jcr:" + data);

        return String.valueOf(result);
    }

    private void RefreshKnowledgePackage(String packageId, String files) throws IOException {
        files= Utils.decodeURL(files);
        ResourceBase resourceBase=knowledgeBuilder.newResourceBase();
        String[] paths=files.split(";");
        for(String path:paths){
            String[] subpaths=path.split(",");
            path=subpaths[0];
            String version=null;
            if(subpaths.length>1){
                version=subpaths[1];
            }
            resourceBase.addResource(path,version);
        }

        KnowledgeBase knowledgeBase=knowledgeBuilder.buildKnowledgeBase(resourceBase);

        CacheUtils.getKnowledgeCache().putKnowledge(packageId, knowledgeBase.getKnowledgePackage());
    }

}
