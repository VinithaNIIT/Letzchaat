package com.niit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
//similar to spring dispatcher
@Configuration
	@EnableWebMvc
	@ComponentScan(basePackages= "com.niit")
	public class AppConfig implements TransactionManagementConfigurer{

		/*private static final ch.qos.logback.classic.Logger Logger = LoggerFactory.getLogger(AppConfig.class);*/
		@Bean
		public ViewResolver viewResolver(){
			/*Logger.debug("Starting of the method viewResolver");*/
			
			InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
			viewResolver.setViewClass(JstlView.class);
			viewResolver.setPrefix("/WebContent/");
			viewResolver.setSuffix(".html");
			/*Logger.debug("Ending of the method viewResolver");*/
			return viewResolver;
		}

		public PlatformTransactionManager annotationDrivenTransactionManager() {
			// TODO Auto-generated method stub
			return null;
		}
		
		
		
		@Bean(name = "multipartResolver")
		public CommonsMultipartResolver createMultipartResolver() {
		    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		    resolver.setDefaultEncoding("utf-8");
		    resolver.setMaxUploadSize(1000000);
		    return resolver;
		}
		
		
	}



