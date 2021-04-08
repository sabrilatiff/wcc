package wcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import wcc.util.SpringUtil;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"wcc.config","wcc.service"})
@EnableJpaRepositories("wcc.repository")
@EntityScan("wcc.domain")
public class WCCApplication {
	
	Logger log = LoggerFactory.getLogger(WCCApplication.class);
	
	private static ApplicationContext ctx;
    
	public static void main(String[] args) {
		ctx = SpringApplication.run(WCCApplication.class,args);
		SpringUtil.setContext(ctx);
	}
	
	public static ApplicationContext getApplicationContext() {
		return ctx;
	}
}
