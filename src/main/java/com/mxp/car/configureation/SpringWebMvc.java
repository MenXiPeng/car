package com.mxp.car.configureation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/26
 * TIME: 20:44
 */
@Log4j2
@Configuration
public class SpringWebMvc implements WebMvcConfigurer {

    @Autowired
    private HandlerInterceptor timeInterceptor;
    @Autowired
    private MappingJackson2HttpMessageConverter converter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor).addPathPatterns("/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        var objectMapper = new ObjectMapper();
        var simpleModule = new SimpleModule();

        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);

        objectMapper.registerModule(simpleModule);
        converter.setObjectMapper(objectMapper);

        converters.add(0, converter);
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter() {
            @Override
            protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                if (object instanceof String) {
                    var charset = Optional.ofNullable(this.getDefaultCharset()).orElse(StandardCharsets.UTF_8);
                    StreamUtils.copy((String) object, charset, outputMessage.getBody());
                } else {
                    super.writeInternal(object, type, outputMessage);
                }
            }
        };
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        var configurer = new PropertySourcesPlaceholderConfigurer();
        var yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("config.yml"));
        configurer.setProperties(yaml.getObject());
        return configurer;
    }

    @Bean
    public ErrorPageRegistrar errorPageRegistrar() {
        return registry -> registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/index.html"));
    }

}
