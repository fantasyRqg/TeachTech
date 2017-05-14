package com.wjy.tt.entity;

import com.wjy.tt.Commons;

/**
 * Created by wjy on 07/05/2017.
 */
public class Response<T> {
    private String status;
    private String message;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> Response<T> noNUllResponse(T a, String errMsg) {
        Response<T> response = new Response<>();
        if (a == null) {
            response.setMessage(errMsg);
            response.setStatus(Commons.FAILURE);
            return response;
        }
        response.setStatus(Commons.SUCCESS);
        response.setMessage(Commons.SUCCESS);
response.setData(a);
        return response;
    }
}
