package com.tangzhe.blog.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 唐哲
 * 2018-02-21 15:47
 */
@Data
public class JsonResponse<T> {

    private boolean success;

    private String message;

    private T body;

    public JsonResponse() {}
    public JsonResponse(boolean result) {
        this.success = result;
    }

    public static <T> JsonResponse success() {
        return new JsonResponse(true);
    }

    public static <T> JsonResponse success(T data) {
        JsonResponse response = new JsonResponse(true);
        response.setBody(data);
        return response;
    }

    public static <T> JsonResponse success(T data, String msg) {
        JsonResponse response = new JsonResponse(true);
        response.setMessage(msg);
        response.setBody(data);
        return response;
    }

    public static <T> JsonResponse success(Boolean result, T data) {
        JsonResponse response = new JsonResponse();
        response.setSuccess(result);
        response.setBody(data);
        return response;
    }

    public static JsonResponse fail() {
        return new JsonResponse(false);
    }

    public static JsonResponse fail(String msg) {
        JsonResponse response = new JsonResponse(false);
        response.setMessage(msg);
        return response;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", this.success);
        result.put("body", this.body);
        result.put("message", this.message);
        return result;
    }

}
