package com.ERR.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ERR.common.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer{
	
	
	    
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			
			registry.addInterceptor(new CheckLoginSessionInterceptor()) //LoginCheckInterceptor 등록
					.addPathPatterns("/*Xdm*") // 로그인없이는 전체경로 다막음
					
					.excludePathPatterns(	//예외 경로 
							"xdm/**",
							"usr/**",
							"index/**",   // static 경로 (css 망가짐방지를 위한 예외설정)
							"/adminMemberLogin",
							"/adminLoginRegister"
							
					)
			;
		}
	    
	}

