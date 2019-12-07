package com.mxp.car.Handler;

import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author DongXing
 * @user Liang
 * @time 2019/12/7 13:16
 */
@Configuration
public class CarAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        StatusCode loginError = null;
        if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
            loginError = StatusCode.LOGIN_INPUT_ERROR;
        } else if (exception instanceof DisabledException) {
            loginError = StatusCode.LOGIN_ACCOUNT_DISABLED;
        } else {
            loginError = StatusCode.LOGIN_ERROR;
            loginError.msg=exception.getMessage();
        }
        ResultRtn<Object> of = ResultRtn.of(loginError);
        out.write(of.toJsonString());
        out.flush();
        out.close();
    }
}
