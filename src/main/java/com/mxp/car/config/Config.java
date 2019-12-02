package com.mxp.car.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/10/11
 * TIME: 3:39 下午
 */
@Data
@Component
@PropertySource("classpath:config.yml")
public class Config implements InitializingBean {

    @Value("${request.timeout}")
    private long timeout;

    public static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    public static final String SUCCESS = "Success";
    public static final String ERROR = "Error";

    public static long TIMEOUT_TIME;


    @Override
    public void afterPropertiesSet() {
        TIMEOUT_TIME = this.timeout;
    }
}
