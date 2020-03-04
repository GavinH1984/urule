package cn.gavinhuang.urule.client;

import com.bstek.urule.KnowledgePackageReceiverServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;

/**
 * @author gavin huang
 * @since 2020/2/6
 */
@Component
public class URuleClientServletRegistration {
	@Bean
	public ServletRegistrationBean<HttpServlet> registerURuleServlet(){
		return new ServletRegistrationBean<HttpServlet>(new KnowledgePackageReceiverServlet(),"/knowledgepackagereceiver");
	}
}
