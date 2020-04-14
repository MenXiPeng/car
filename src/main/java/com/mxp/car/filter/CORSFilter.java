package com.mxp.car.filter;


import com.mxp.car.model.User;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User : Pengfei Zhang
 * Mail : Tubetrue01@gmail.com
 * Date : 2019-08-05
 * Time : 16:48
 */
@Log4j2
@Order(1)
@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        log.info("-==前端请求，开始处理跨域！==- {}", request.getMethod());
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST,DELETE");
        response.addHeader("Access-Control-Allow-Headers", "content-type");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Max-Age", "3600");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if (request.getRequestURL().indexOf("/password") != -1 || request.getRequestURL().indexOf("/login") != -1) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (Objects.isNull(user)) {
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print(ResultRtn.of(StatusCode.LOGOUT_PLEASE).toJsonString());
            } else {
                chain.doFilter(request, response);
            }
        }
    }

}

