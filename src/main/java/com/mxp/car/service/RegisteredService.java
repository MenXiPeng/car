package com.mxp.car.service;

import com.mxp.car.model.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface RegisteredService extends BaseService {

    Map<String, String> getPasswordAndMachineCode(Map<String, String> paramMap);

    User login(String username, String password);
}
