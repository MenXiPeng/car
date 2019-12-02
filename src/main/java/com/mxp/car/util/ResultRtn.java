package com.mxp.car.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.Utils;

import java.util.List;

/**
 * EMAIL menxipeng@gmail.com
 * AUTHOR:menxipeng
 * DATE: 2019/11/27
 * TIME: 22:56
 */
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultRtn<T> {
    private int code;
    private String msg;
    private List<T> dataList;

    @SafeVarargs
    public static <T> ResultRtn<T> of(StatusCode statusCode, T... t) {
        var result = new ResultRtn<T>();
        result.setCode(statusCode.code);
        result.setMsg(statusCode.msg);
        result.setDataList(List.of(t));
        return result;
    }

//    @Override
//    public String toString() {
//        return Utils.JSONUtils.objectToJson(this);
//    }
}
