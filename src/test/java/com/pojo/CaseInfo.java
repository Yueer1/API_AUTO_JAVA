package com.pojo;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @Project: api_auto
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Author: Administrator
 * @Create: 2020-09-04 21:53
 * @Desc： 映射实体类
 **/
public class CaseInfo {
    @Excel(name="用例编号")
    private int caseId;
    //CaseId(用例编号)	Name(接口名)	Type(接口提交类型)	Url(接口地址)
    // Desc(用例描述)	Params(参数)	 Content-Type

    //用例编号	用例描述	接口名称（没有写）	请求方式	url	参数	参数类型

    @Excel(name="请求方式")
    private String type;

    @Excel(name="url")
    private String url;

    @Excel(name="用例描述")
    private String desc;

    @Excel(name="参数")
    private String params;

    @Excel(name="参数类型")
    private String contentType;

    @Excel(name="期望结果")
    private String exceptResult;

    @Excel(name="sql")
    private String sql;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getExceptResult() {
        return exceptResult;
    }

    public void setExceptResult(String exceptResult) {
        this.exceptResult = exceptResult;
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
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                ", params='" + params + '\'' +
                ", contentType='" + contentType + '\'' +
                ", exceptResult='" + exceptResult + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}

