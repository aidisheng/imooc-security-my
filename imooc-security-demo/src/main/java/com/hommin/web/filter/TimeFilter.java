package com.hommin.web.filter;/**
 * Created by Hommin on 2018/3/6.
 */

import javax.servlet.*;
import java.io.IOException;
import java.util.Calendar;

/**
 * @author Hommin
 * @ClassName: TimeFilter
 * @Description: 记录时间过滤器
 * @data 2018年03月06日 下午2:26
 */
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long startTime = Calendar.getInstance().getTimeInMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time filter 耗时：" + (Calendar.getInstance().getTimeInMillis() - startTime));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
