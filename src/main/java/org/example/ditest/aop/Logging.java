package org.example.ditest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class Logging {

  // controller/Post 로 시작하는 컨트롤러 안에 있는 모든 메서드의 시작 전에 로깅을 남기는 부가기능을 붙여줌
  //@Before("execution(* org.example.ditest.controller.Post*.*(..))")
  public void leaveLog(JoinPoint jp) {
    String methodName = jp.getSignature().getName();
    String className = jp.getTarget().getClass().getName();
    log.info("{} class - {} method 가 실행되기 전에 남겨진 로그입니다.", className, methodName);
  }

}
