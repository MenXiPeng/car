package com.mxp.car.configureation;

import com.mxp.car.Handler.CarAccessDeniedHandler;
import com.mxp.car.Handler.CarAuthenticationFailureHandler;
import com.mxp.car.Handler.CarAuthenticationSuccessHandler;
import com.mxp.car.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Autowired
    private CarAccessDeniedHandler carAccessDeniedHandler;
    @Autowired
    private CarAuthenticationSuccessHandler carAuthenticationSuccessHandler;
    @Autowired
    private CarAuthenticationFailureHandler carAuthenticationFailureHandler;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/password").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password").permitAll()
                .failureHandler(carAuthenticationFailureHandler)
                .successHandler(carAuthenticationSuccessHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(carAccessDeniedHandler)
                .and()
                .logout().permitAll();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}