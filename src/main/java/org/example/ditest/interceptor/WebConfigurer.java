package org.example.ditest.interceptor;

import lombok.RequiredArgsConstructor;
import org.example.ditest.session.SessionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfigurer implements WebMvcConfigurer {
  private final SessionManager sessionManager;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheckInterceptor(sessionManager))
        .addPathPatterns("/**")        // 모든 경로에 적용 (예>/posts/**) //  한곳에서 경로 기반으로 로그인이 필요한 것, 필요하지 않은 곳을 다룰 수 있어서 유리
        .excludePathPatterns(          // 로그인 없이 접근 가능한 경로 제외
            "/login",
            "/join",
            "/css/**",
            "/js/**",
            "/images/**"
        );
  }
}


//    registry.addInterceptor(new LogInterceptor())
//        .order(1)
//        .addPathPatterns("/posts/**");
//        .excludePathPatterns()



//    registry.addInterceptor(new LogInterceptor2())
//        .order(2)
//        .addPathPatterns("/posts/**");
//
//    registry.addInterceptor(new LogInterceptor3())
//        .order(3)
//        .addPathPatterns("/posts/**");


