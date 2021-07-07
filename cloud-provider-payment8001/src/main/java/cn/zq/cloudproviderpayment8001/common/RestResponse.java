package cn.zq.cloudproviderpayment8001.common;

import lombok.Data;

import java.util.List;

@Data
public class RestResponse<T> {
    private String code;
    private String message;
    private T data;

    private static final String SUCCESS_CODE = "200";
    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAILD_CODE = "500";
    private static final String FAILD_MESSAGE = "faild";

    public RestResponse(String code, String message) {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
    }

    public RestResponse(T data) {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
        this.data = data;
    }
}
