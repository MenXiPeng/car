package com.mxp.car.Handler;

import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author DongXing
 * @user Liang
 * @time 2019/12/7 12:59
 */
@Configuration
public class CarAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResultRtn<Object> of = ResultRtn.of(StatusCode.LOGIN_SUCCESS);
        out.write(of.toJsonString());
        out.flush();
        out.close();
    }
}
