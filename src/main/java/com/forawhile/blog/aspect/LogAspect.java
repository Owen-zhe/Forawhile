package com.forawhile.blog.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.forawhile.blog.web.*.*(..))")
    public void log(){

    }
    @Before("log()")
    public void deBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= attributes.getRequest();
        String url= request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." +joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Requestlog requestlog = new Requestlog(url,ip,classMethod,args);
        logger.info("Result : {}",requestlog);
    }

    @After("log()")
    public void deAfter(){
 //       logger.info("------doAfter-----");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void deAfterReturn(Object result){
        logger.info("Result : {}",result);
    }


    private class Requestlog{
        private String url;
        private String id;
        private String ClassMethod;
        private Object[] args;

        public Requestlog(String url, String id, String classMethod, Object[] args) {
            this.url = url;
            this.id = id;
            this.ClassMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "Requestlog{" +
                    "url='" + url + '\'' +
                    ", id='" + id + '\'' +
                    ", ClassMethod='" + ClassMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
