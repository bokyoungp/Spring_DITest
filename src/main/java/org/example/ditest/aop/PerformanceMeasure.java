package org.example.ditest.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PerformanceMeasure {

  // PostService 안에 있는 모든 메서드에 실행시간을 측정하는 부가기능을 붙여줌
  //@Around("execution(* org.example.ditest.service.PostService.*(..))")
  public Object performanceMeasure(ProceedingJoinPoint pjp) throws Throwable {
    String methodName = pjp.getSignature().getName();
    String className = pjp.getTarget().getClass().getName();
    long start = System.currentTimeMillis();

    Object proceedResult = pjp.proceed();

    long end = System.currentTimeMillis();
    log.info("{} - {} 가 실행되는데 걸린 시간은 {} ms 입니다.", className, methodName, (end - start));
    return proceedResult;
  }
}
