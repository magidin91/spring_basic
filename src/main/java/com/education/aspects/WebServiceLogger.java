package com.education.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class WebServiceLogger {
    private static final Logger LOG = LoggerFactory.getLogger(WebServiceLogger.class);

    /**
     * Пойнткат (точка соединения)
     * В данном случае - это методы, помеченные аннотацией @Loggable
     */
    @Pointcut("@annotation(com.education.annotation.Loggable)")
    public void loggableMethod() {
    }

    /**
     * Пойнткат для всех публичных методов из папки service
     * .*(..) - обозначает - "все методы"
     */
    @Pointcut(value = "execution(public * com.education.service.*.*(..))")
    public void serviceMethod() {
    }

    /**
     * Пойнткат "serviceMethod()" - любой публичный метод из ProductService
     */
    @Pointcut(value = "execution(public * com.education.service.ProductService.*(..))")
    public void productServiceMethod() {
    }

    /* Вызывается вместо методов, которые соответствуют serviceMethod() и также loggableMethod() */
    @Around(value = "productServiceMethod() && loggableMethod()")
    public Object logWebServiceLog(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName(); //thisJoinPoint - метод вместо, которого вызываем совет.
        Object[] methodArgs = thisJoinPoint.getArgs(); // получили аргументы метода
        LOG.info("Call method " + methodName + " with args " + Arrays.toString(methodArgs)); // пишем в лог, что вызывали данный метод
        Object result = thisJoinPoint.proceed(); // выполняем первоначальный метод и получаем  его результат
        LOG.info("Method " + methodName + " returns " + result);
        return result; //  вернули результат первоначального метода
    }

//    /**
//     * Совет, выполняеый до точки соединения
//     */
//    @Before(value = "serviceMethod()")
//    public void beforeWebServiceLog() {
//        LOG.info("Method starts working");
//    }
//
//    /**
//     * Совет, выполняеый после точки соединения
//     */
//    @After(value = "serviceMethod()")
//    public void afterWebServiceLog() {
//        LOG.info("Method has finished");
//    }
}
