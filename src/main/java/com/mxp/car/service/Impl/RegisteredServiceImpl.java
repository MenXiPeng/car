package com.mxp.car.service.Impl;

import com.mxp.car.mapper.BaseMapper;
import com.mxp.car.mapper.UserMapper;
import com.mxp.car.model.User;
import com.mxp.car.service.RegisteredService;
import com.mxp.car.util.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
@Service
@PropertySource("classpath:config.yml")
public class RegisteredServiceImpl extends BaseServiceImpl implements RegisteredService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${uuid}")
    private String configUUID;

    @Override
    public BaseMapper<User, Long> getMapper() {
        return userMapper;
    }

    @Override
    public Map<String, String> getPasswordAndMachineCode(Map<String, String> paramMap) {
        Map<String, String> result = new LinkedHashMap<>();
        if (configUUID.equals(paramMap.get("uuidKey"))) {
            result.put("password", bCryptPasswordEncoder.encode(paramMap.get("password")));
            result.put("username", paramMap.get("username"));
            String machine = Utils.CarUtil.getMachine();
            log.info("{}:{}", paramMap.get("password"), machine);
            result.put("machine", configUUID);
            User user = new User();
            user.setName("");
            user.setVerification(result.get("machine"));
            user.setUsername(result.get("username"));
            user.setPassword(result.get("password"));
            user.setCompany("");
            user.setPhone("");
            user.setAddress("");
            user.setUserId(1L);
            userMapper.insert(user);
        }
        return result;
    }
}
