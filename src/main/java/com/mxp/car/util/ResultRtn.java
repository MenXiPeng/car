package com.mxp.car.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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

    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PageInfo {

        private final int pageNum;
        private final int pageSize;
        private final long total;

        public static PageInfo of(int pageNum, int pageSize, long total) {
            return new PageInfo(pageNum, pageSize, total);
        }
    }
}
