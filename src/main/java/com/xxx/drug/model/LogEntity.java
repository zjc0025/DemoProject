package com.xxx.drug.model;

import java.io.Serializable;

/**
 * @ClassName LogEntity
 * @Description
 * @Author ZJC
 * @Date 2019/4/18 16:40
 */
public class LogEntity implements Serializable {


    private String loginName;

    private String drugId;

    /**
     * 操作时间
     **/
    private String updateDate;

    /**
     * 操作方式：入库 or 出库
     **/
    private String describe;

    private String module;

    private String method;

    private String ip;

    /**
     * 响应时间
     **/
    private String responseTime;

    private String result;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "loginName='" + loginName + '\'' +
                ", drugId='" + drugId + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", describe='" + describe + '\'' +
                ", module='" + module + '\'' +
                ", method='" + method + '\'' +
                ", ip='" + ip + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
