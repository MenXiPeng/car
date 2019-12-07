package com.mxp.car.service;

import java.util.Map;

public interface RegisteredService extends BaseService {

    Map<String ,String> getPasswordAndMachineCode(Map<String, String> paramMap);
}
