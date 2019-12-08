package com.mxp.car.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2019-08-05
 * Time : 16:46
 */
@Log4j2
@Component
public class TimeRecordInterceptor implements HandlerInterceptor {
    private final ThreadLocal<Long> timeRecorder = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request--[" + request.getRemoteAddr() + " ==> " + request.getRequestURL() + "]");
        var start = System.currentTimeMillis();
        timeRecorder.set(start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        var end = System.currentTimeMillis();
        var totalTime = end - timeRecorder.get();
         log.info("Response-[" + request.getRemoteAddr() + " <== " + request.getRequestURL() + "]-耗时 : " + totalTime + "MS");
    }
}
