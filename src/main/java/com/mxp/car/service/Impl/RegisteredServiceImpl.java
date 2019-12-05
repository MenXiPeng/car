package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.UserMapper;
import com.mxp.car.model.User;
import com.mxp.car.service.RegisteredService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@Log4j2
@Service
@PropertySource("classpath:config.yml")
public class RegisteredServiceImpl extends BaseServiceImpl implements RegisteredService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${uuid}")
    private String configUUID;

    @Override
    public BaseMapper<User, Long> getMapper() {
        return userMapper;
    }

    @Override
    public Map<String, String> getPasswordAndMachineCode(String password, String uuid) {
        Map<String, String> result = new LinkedHashMap<>();
        if (configUUID.equals(uuid)) {
            result.put("password", passwordEncoder.encode(password));
            try {
                Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
                process.getOutputStream().close();
                Scanner sc = new Scanner(process.getInputStream());
                String property = sc.next();
                String serial = sc.next();
                log.info("{}:{}", password, serial);
                result.put("machine", serial);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
