package com.zjy.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 * 返回给前端vo
 *
 * @author zhaojianyu
 * @create 2018-12-14 7:32 PM
 */
@Getter
@Setter
public class Result {
    /**
     * -3 异常
     * 0  通
     * 1
     * 2
     *
     */
    private Integer code;
    private String status;
    private Object data;
    public Result(){
    }

    public Result(String status, Integer code) {
        this.code = code;
        this.status = status;
        this.data = null;
    }

    public Result(String status, Integer code, String data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }
}
