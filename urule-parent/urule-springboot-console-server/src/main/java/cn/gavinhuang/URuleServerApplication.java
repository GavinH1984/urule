package cn.gavinhuang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author gavin huang
 * @since 2020/2/6
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource({"classpath:urule-console-context.xml","classpath:action-context.xml"})
public class URuleServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(URuleServerApplication.class,args);
	}

}
