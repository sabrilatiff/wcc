package wcc.util;

import org.springframework.context.ApplicationContext;

public class SpringUtil {
	
	private static ApplicationContext ctx;
	
	public static <T extends Object> T lookupBean(Class<T> clazz) {
		 return (T) ctx.getBean(clazz);
	}
	
	public static void setContext(ApplicationContext ctx) {
		SpringUtil.ctx = ctx;
	}
}
