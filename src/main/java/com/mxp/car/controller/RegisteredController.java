package com.mxp.car.controller;

import com.mxp.car.service.RegisteredService;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RegisteredController {

    @Autowired
    private RegisteredService registeredService;

    @GetMapping("/password")
    public ResultRtn<Map<String, String>> getPasswordAndMachineCode(@RequestParam Map<String, String> paramMap) {
        Map<String, String> passwordAndMachineCode = registeredService.getPasswordAndMachineCode(paramMap);
        if (passwordAndMachineCode.size() > 0) {
            return ResultRtn.of(StatusCode.SUCCESS, passwordAndMachineCode);
        } else {
            return ResultRtn.of(StatusCode.ERROR);
        }
    }
}
