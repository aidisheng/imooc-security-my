package com.hommin.web.interceptor;/**
 * Created by Hommin on 2018/3/8.
 */

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

/**
 * @author Hommin
 * @ClassName: TimeInterceptor
 * @Description: 时间记录拦截器
 * @data 2018年03月08日 下午8:43
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    private Long time;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("time interceptor start");
        time = Calendar.getInstance().getTimeInMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("time interceptor 耗时：" + (Calendar.getInstance().getTimeInMillis() - time));
        System.out.println("time interceptor finish");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("time interceptor 耗时：" + (Calendar.getInstance().getTimeInMillis() - time));
        System.out.println("time interceptor end");
    }
}
