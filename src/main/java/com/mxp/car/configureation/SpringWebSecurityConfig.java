package com.mxp.car.configureation;

import com.mxp.car.service.Impl.UserDetailsServiceImpl;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 描述：
 *
 * @Author shf
 * @Date 2019/4/19 10:54
 * @Version V1.0
 **/
@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/password").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .failureHandler((request, response, exception) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    StatusCode loginError = null;
                    if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
                        loginError = StatusCode.LOGIN_INPUT_ERROR;
                    } else if (exception instanceof DisabledException) {
                        loginError = StatusCode.LOGIN_ACCOUNT_DISABLED;
                    } else {
                        loginError = StatusCode.LOGIN_ERROR;
                    }
                    ResultRtn<Object> of = ResultRtn.of(loginError);
                    out.write(of.toJsonString());
                    out.flush();
                    out.close();
                })
                .successHandler((request, response, authentication) -> {
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    ResultRtn<Object> of = ResultRtn.of(StatusCode.LOGIN_SUCCESS);
                    out.write(of.toJsonString());
                    out.flush();
                    out.close();
                }).and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
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
                });
    }

    @Bean
    public UserDetailsServiceImpl userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}