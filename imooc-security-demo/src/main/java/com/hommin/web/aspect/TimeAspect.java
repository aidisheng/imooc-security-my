package com.hommin.web.aspect;/**
 * Created by Hommin on 2018/3/9.
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author Hommin
 * @ClassName: TimeAspect
 * @Description: 时间切片
 * @data 2018年03月09日 上午9:25
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.hommin.web.controller.*.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable {
        // 获取参数
        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        System.out.println("time aspect start");
        long startTime = Calendar.getInstance().getTimeInMillis();
        Object proceed = point.proceed();
        System.out.println("time aspect 耗时：" + (Calendar.getInstance().getTimeInMillis() - startTime));
        System.out.println("time aspect finish");
        return proceed;
    }

}
