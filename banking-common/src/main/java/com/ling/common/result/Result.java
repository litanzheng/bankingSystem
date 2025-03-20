package com.ling.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造函数私有化
     */
    private Result(){}

    /**
     * 返回成功结果
     * @return
     */
    public static Result ok(){
        Result r = new Result();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * 返回失败结果
     * @return
     */
    public static Result error(){
        Result r = new Result();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /**
     * 设置特定的结果
     * @param responseEnum
     * @return
     */
    public static Result setResult(ResponseEnum responseEnum){
        Result r = new Result();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    /**
     * 设置特定的响应消息
     * @param message
     * @return
     */
    public Result message(String message){
        this.setMessage(message);
        return this;
    }


    /**
     * 设置特定的响应码
     * @param code
     * @return
     */
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }
}
