package com.sevenXnetworks.treasure.model;

import java.util.Map;

public enum CustomerErrorConstants implements LocalError {


    //#####################业务错误码#####################
    FILE_NOT_EXIST("100031", "文件不存在"),
    FILE_MOVE_FILE("100032", "文件移动失败"),


    //#####################请求参数错误码#####################
    TOKEN_AUTHENTICATION_ERROR("200000", "用户认证失败"),

    GRADE_UPLOAD_FORMAT_ERROR("200001", "分数上传参数错误");


    private String code;

    private String message;

    private Map<String, Object> errorParams;


    CustomerErrorConstants(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Map<String, Object> getErrorParams() {
        return errorParams;
    }

    public LocalError setErrorParams(Map<String, Object> errorParams) {
        this.errorParams = errorParams;
        return this;
    }

    public String toString() {
        return "[" + code + "] " + message;
    }

}
