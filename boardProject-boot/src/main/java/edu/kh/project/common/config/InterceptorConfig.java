package edu.kh.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.kh.project.common.interceptor.BoardTypeIntercepteor;

// 인터셉터가 어떤 요청을 가로챌지 설정하는 클래스

@Configuration // 서버가 켜지면 내부의 모든 메서드를 수행하겠다
public class InterceptorConfig implements WebMvcConfigurer{

	// 인터셉터 클래스 Bean 등록
	@Bean
	public BoardTypeIntercepteor boardTypeIntercepteor() {
		return new BoardTypeIntercepteor();
	}
	// WebMvcConfigurer 상속 받은 이유
	// 동작할 인터셉터 객체를 추가하는 메서드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// Bean으로 등록된 BoardTypeInterceptorm를 얻어와서 매개변수로 전달
		registry
		.addInterceptor(boardTypeIntercepteor())
		.addPathPatterns("/**") // 가로챌 요청 주소 지정 /** : /이하 모든 요청 주소
		.excludePathPatterns("/css/**", "/js/**", "/images/**", "/favicon.ico"); // 가로채지 않을 주소 지정
		
	}
}
