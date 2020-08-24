package com.tdx.sharding.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResultModel {
    public static final int RESPONSE_CODE_SUCCESS = 0;
    public static final int RESPONSE_CODE_PARAMETER_ERROR = 400;
    public static final int RESPONSE_CODE_INTERNAL_ERROR = 500;

    public static final String RESPONSE_MESSAGE_SUCCESS = "响应成功";
    public static final String RESPONSE_MESSAGE_PARAMETER_ERROR = "请求参数错误";
    public static final String RESPONSE_MESSAGE_INTERNAL_ERROR = "服务器内部错误";

    private static final ResultModel parameterErrorRM = new ResultModel(ResultModel.RESPONSE_CODE_PARAMETER_ERROR,
            ResultModel.RESPONSE_MESSAGE_PARAMETER_ERROR);
    private static final ResultModel internalErrorRM = new ResultModel(ResultModel.RESPONSE_CODE_INTERNAL_ERROR,
            ResultModel.RESPONSE_MESSAGE_INTERNAL_ERROR);

    private int code;
    private String message;
    private int pos; // 用于分页
    private JSONArray fieldInfo;
    private JSONArray data;

    public ResultModel(int code, String message) {
        this(code, message, 0, new JSONArray(), new JSONArray());
    }

    public ResultModel(int code, String message, JSONArray data) {
        this(code, message, 0, new JSONArray(), data);
    }

    public ResultModel(int code, String message, JSONArray fieldInfo, JSONArray data) {
        this(code, message, 0, fieldInfo, data);
    }

    public ResultModel(int code, String message, int pos, JSONArray fieldInfo, JSONArray data) {
        this.code = code;
        this.message = message;
        this.pos = pos;
        this.fieldInfo = fieldInfo;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public JSONArray getFieldInfo() {
        return fieldInfo;
    }

    public void setFieldInfo(JSONArray fieldInfo) {
        this.fieldInfo = fieldInfo;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("status", this.code);
        obj.put("message", this.message);
        obj.put("pos", pos == 0 ? "" : String.valueOf(pos));
        obj.put("fieldInfo", this.fieldInfo);
        obj.put("data", this.data);

        return JSON.toJSONString(obj, SerializerFeature.WRITE_MAP_NULL_FEATURES);
    }

    public static ResultModel getParameterErrorRM() {
        return parameterErrorRM;
    }

    public static ResultModel getParameterErrorRM(String message) {
        return new ResultModel(ResultModel.RESPONSE_CODE_PARAMETER_ERROR, message);
    }

    public static ResultModel getInternalErrorRM() {
        return internalErrorRM;
    }

    public static ResultModel getErrorRM(String message) {
        return new ResultModel(ResultModel.RESPONSE_CODE_INTERNAL_ERROR, message);
    }

    public static ResultModel getSuccessRM(String message) {
        return new ResultModel(ResultModel.RESPONSE_CODE_SUCCESS, message);
    }

    public static ResultModel getSuccessRM(JSONArray data) {
        return new ResultModel(ResultModel.RESPONSE_CODE_SUCCESS, ResultModel.RESPONSE_MESSAGE_SUCCESS, 0, new JSONArray(), data);
    }

    public static ResultModel getSuccessRM(JSONArray fieldInfo, JSONArray data) {
        return new ResultModel(ResultModel.RESPONSE_CODE_SUCCESS, ResultModel.RESPONSE_MESSAGE_SUCCESS, 0, fieldInfo, data);
    }

    public static ResultModel getSuccessRM(int pos, JSONArray data) {
        return new ResultModel(ResultModel.RESPONSE_CODE_SUCCESS, ResultModel.RESPONSE_MESSAGE_SUCCESS, pos, new JSONArray(), data);
    }

    public static ResultModel getSuccessRM(int pos, JSONArray fieldInfo, JSONArray data) {
        return new ResultModel(ResultModel.RESPONSE_CODE_SUCCESS, ResultModel.RESPONSE_MESSAGE_SUCCESS, pos, fieldInfo, data);
    }
}
