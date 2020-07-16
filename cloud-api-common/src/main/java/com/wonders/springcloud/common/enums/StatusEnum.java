package com.wonders.springcloud.common.enums;

import lombok.Getter;

/**
 * @author qiu
 * @date 2020-05-08 09:59
 */
@Getter
public enum StatusEnum {
    SUCCESS(200,"请求成功"),
    ERROR(-1,"请求异常")
    ;
    private int code;
    private String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }}
