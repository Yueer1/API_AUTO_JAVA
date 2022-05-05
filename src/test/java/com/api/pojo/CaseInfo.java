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
    @Excel(name = "CaseId(用例编号)")
    private int caseId;

    @Excel(name = "Name(接口名)")
    private String name;

    @Excel(name = "Type(接口提交类型)")
    private String type ;

    @Excel(name = "Url(接口地址)")
    private String url;

    @Excel(name = "Desc(用例描述)")
    private String desc;

    @Excel(name = "Params(参数)")
    private String params;

    @Excel(name = "Content-Type")
    private String contentType;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
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

    @Override
    public String toString() {
        return "CaseInfo{" +
                "caseId=" + caseId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                ", params='" + params + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
