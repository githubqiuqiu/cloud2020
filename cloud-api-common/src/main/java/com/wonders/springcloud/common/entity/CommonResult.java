package com.wonders.springcloud.common.entity;

import com.wonders.springcloud.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiu
 * @date 2020-05-08 09:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private Integer code;
    private String message;
    private Object data;


    public static CommonResult success(){
        return success(null);
    }

    public static CommonResult success(Object data){
        return new CommonResult(StatusEnum.SUCCESS.getCode(),StatusEnum.SUCCESS.getMessage(),data);
    }

    public static CommonResult success(String message,Object data){
        return new CommonResult(StatusEnum.SUCCESS.getCode(),message,data);
    }

    public static CommonResult error(){
        return error(null);

    }

    public static CommonResult error(Object data){
        return new CommonResult(StatusEnum.ERROR.getCode(),StatusEnum.ERROR.getMessage(),data);
    }

    public static CommonResult error(Integer code,String message){
        return error(code,message,null);
    }

    public static CommonResult error(Integer code,String message,Object data){
        return new CommonResult(code,message,data);
    }

    public static CommonResult errorEnum(StatusEnum statusEnum){
        return errorEnum(statusEnum,null);
    }

    public static CommonResult errorEnum(StatusEnum statusEnum,Object data){
        return new CommonResult(statusEnum.getCode(),statusEnum.getMessage(),data);
    }

}
