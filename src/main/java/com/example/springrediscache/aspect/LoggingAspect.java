package com.example.springrediscache.aspect;


import lombok.extern.log4j.Log4j;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import java.util.Arrays;

@Aspect
@Component
@Log4j
public class LoggingAspect {
    private static final Logger LOGGER = (Logger) LogManager.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.springrediscache.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Yakalanan yöntem ayrıntılarını alın
        String className = methodSignature.getDeclaringType().getSimpleName();
        String returnType = methodSignature.getReturnType().getSimpleName();
        String[] parameters = methodSignature.getParameterNames();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Yöntem yürütme süresini ölçün
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Günlük yöntemi yürütme süresi
      LOGGER.info("Working method: " +" " + className +"."+ methodName+ " parameters: " + Arrays.toString(parameters)+" return type: " + returnType + " :: " + stopWatch.getTotalTimeMillis() + " ms");//console
//        log.info("Working method: " +" " + className +"."+ methodName+ " parameters: " + Arrays.toString(parameters)+" return type: " + returnType + " :: " + stopWatch.getTotalTimeMillis() + " ms");//txt
        return result;
    }
}