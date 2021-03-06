package cn.gavinhuang.urule.client;

import cn.gavinhuang.model.Customer;
import com.alibaba.fastjson.JSONObject;
import com.bstek.urule.Utils;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.KnowledgeSessionFactory;
import com.bstek.urule.runtime.service.KnowledgeService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RuleController {

    @RequestMapping("/rule1")
    public String  getRara(@RequestParam String data)throws IOException {
        KnowledgeService knowledgeService = (KnowledgeService) Utils.getApplicationContext().getBean(KnowledgeService.BEAN_ID);

        //参数，Urule项目名/知识包名
        KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("gavintest/gavin01");
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);

        Integer integer = Integer.valueOf(data);
        Map<String, Object> param = new HashMap();

        //参数，var，传入参数，和参数库中定义一致
        param.put("var", integer);
        session.fireRules(param);

        //result，返回参数，和参数库中定义一致
        Integer result = (Integer) session.getParameter("result");
        return String.valueOf(result);
    }

    @PostMapping("/rule2")
    @ResponseBody
    public void GetTest2(@RequestBody JSONObject data)throws IOException {
        Customer customer = data.toJavaObject(Customer.class);
        KnowledgeService knowledgeService = (KnowledgeService) Utils.getApplicationContext().getBean(KnowledgeService.BEAN_ID);

        //参数，Urule项目名/知识包名
        KnowledgePackage knowledgePackage = knowledgeService.getKnowledge("gavintest/gavin02");
        KnowledgeSession session = KnowledgeSessionFactory.newKnowledgeSession(knowledgePackage);
        session.insert(customer);
        session.fireRules();
    }
}
