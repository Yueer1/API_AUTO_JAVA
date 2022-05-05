package com.api.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @Project: api_auto_recent
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-16 15:16
 * @Desc： excel映射实体类
 **/
public class CaseInfo {
    //表格读取数据的注解：该注解主要是为了将变量从注解对应的数据中捞出
    @Excel(name = "用例编号")
    private int caseId;

    @Excel(name = "用例描述")
    private String desc;

    @Excel(name = "接口名称")
    private String name;

    @Excel(name = "请求方式")
    private String type ;

    @Excel(name = "url")
    private String url;

    @Excel(name = "参数")
    private String params;

    @Excel(name = "参数类型")
    private String contentType;

    @Excel(name="期望结果")
    private String exceptedResult;

    @Excel(name="sql")
    private String sql;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getExceptedResult() {
        return exceptedResult;
    }

    public void setExceptedResult(String exceptedResult) {
        this.exceptedResult = exceptedResult;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "CaseInfo{" +
                "caseId=" + caseId +
                ", desc='" + desc + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", params='" + params + '\'' +
                ", contentType='" + contentType + '\'' +
                ", exceptedResult='" + exceptedResult + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
