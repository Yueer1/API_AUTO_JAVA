package com.pojo;

public class WriteBackData {

/**
 * @Project: api_auto
 * @Site: http://www.lemonban.com
 * @Forum: http://testingpai.com
 * @Author: Administrator
 * @Create: 2020-09-04 21:53
 * @Desc： 回写类
 **/
    //回写行号
    private int rowNum;
    //回写列号
    private int cellNum;
    //回写sheetIndex
    private int sheetIndex;
    //回写内容
    private String content;

    public WriteBackData() {
    }

    public WriteBackData(int rowNum, int cellNum, int sheetIndex, String content) {
        this.rowNum = rowNum;
        this.cellNum = cellNum;
        this.sheetIndex = sheetIndex;
        this.content = content;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getCellNum() {
        return cellNum;
    }

    public void setCellNum(int cellNum) {
        this.cellNum = cellNum;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WriteBackData{" +
                "rowNum=" + rowNum +
                ", cellNum=" + cellNum +
                ", sheetIndex=" + sheetIndex +
                ", content='" + content + '\'' +
                '}';
    }
}
