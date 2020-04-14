package com.mxp.car.controller;

import com.mxp.car.model.User;
import com.mxp.car.service.RegisteredService;
import com.mxp.car.util.ResultRtn;
import com.mxp.car.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

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

    @PostMapping("/login")
    public ResultRtn<Map<String, String>> login(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {
        User login = registeredService.login(username, password);
        if (Objects.nonNull(login)) {
            if (!login.getPassword().equals(password)) {
                return ResultRtn.of(StatusCode.LOGIN_INPUT_ERROR);
            } else {
                httpSession.setAttribute("user", login);
                httpSession.setMaxInactiveInterval(6000);
                return ResultRtn.of(StatusCode.LOGIN_SUCCESS);
            }
        } else {
            return ResultRtn.of(StatusCode.LOGIN_ERROR);
        }
    }
}
