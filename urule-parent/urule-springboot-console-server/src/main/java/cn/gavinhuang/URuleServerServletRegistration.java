package cn.gavinhuang;

import com.bstek.urule.console.servlet.URuleServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;

/**
 * @author gavin huang
 * @since 2020/2/6
 */
@Component
public class URuleServerServletRegistration {
	@Bean
	public ServletRegistrationBean<HttpServlet> registerURuleServlet(){
		return new ServletRegistrationBean<HttpServlet>(new URuleServlet(),"/urule/*");
	}
	@Bean
	public ServletRegistrationBean<HttpServlet> registerIndexServlet(){
		return new ServletRegistrationBean<HttpServlet>(new IndexServlet(),"/");
	}
	
}
