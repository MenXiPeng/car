package com.mxp.car.Handler;

import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author DongXing
 * @user Liang
 * @time 2019/12/7 13:49
 */
@Configuration
public class CarAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ResultRtn<Object> of = ResultRtn.of(StatusCode.LOGIN_INSUFFICIENT_PERMISSIONS);
        out.write(of.toJsonString());
        out.flush();
        out.close();
    }
}
