package cn.gavinhuang.urule.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:urule-core-context.xml","classpath:action-context.xml"})
public class URuleClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(URuleClientApplication.class, args);
    }
}
