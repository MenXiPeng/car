package com.mxp.car.util;



/**
 /**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 22:56
 */
public enum StatusCode {
    SUCCESS(10010, "请求成功"),
    ERROR(10011, "操作失败"),
    NULL_RESULT(10014, "空结果集"),
    LOGIN_SUCCESS(10028, "登录成功"),
    LOGIN_ERROR(10035, "登录失败"),
    LOGIN_INPUT_ERROR(10036, "用户名或密码输入错误，登录失败!"),
    LOGIN_ACCOUNT_DISABLED(10037, "账户被禁用，登录失败，请联系管理员!"),
    LOGIN_INSUFFICIENT_PERMISSIONS(10038, "权限不足，请联系管理员!"),
    LOGOUT_ERROR(10039, "注销失败"),
    REPEAT_ERROR(10051, "重复错误"),
    TIMEOUT_OR_ERROR(10231, "超时异常"),
    UNKNOWN_ERROR(40015,"状态码异常错误"),
    STATION_ERROR(70010,"站点信息错误"),
    DECRYPTION_ERROR(90010,"加密方式错误"),
    PARAMETER_ERROR(10012,"兑奖票面码为空"),
    TIMOUT_ERROR(10230,"超时"),;
     

    public int code;
    public String msg;

    StatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
