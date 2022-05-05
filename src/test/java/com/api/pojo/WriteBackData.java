package com.api.pojo;

/**
 * @Project: api_auto_recent_V2
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-04-29 22:51
 * @Desc： excel回写类
 **/
public class WriteBackData {
    //回写行号
    private int rownum;
    //回写列号
    private int cellnum;
    //回写sheetIndex
    private int sheetIndex;
    //回写内容
    private String context;

    public WriteBackData() {

    }

    public WriteBackData(int rownum, int cellnum, int sheetIndex, String context) {
        this.rownum = rownum;
        this.cellnum = cellnum;
        this.sheetIndex = sheetIndex;
        this.context = context;
    }

    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public int getCellnum() {
        return cellnum;
    }

    public void setCellnum(int cellnum) {
        this.cellnum = cellnum;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "WriteBackData{" +
                "rownum=" + rownum +
                ", cellnum=" + cellnum +
                ", sheetIndex=" + sheetIndex +
                ", context='" + context + '\'' +
                '}';
    }
}
