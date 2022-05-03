package com.http.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.pojo.CaseInfo;
import com.pojo.WriteBackData;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @Project: api_auto
 * @Forum: http://testingpai.com
 * @Author: wangxy
 * @Create: 2020-09-05 00:31
 * @Desc： excel工具类
 **/

public class ExcelUtils {
    //回写数据集合
    public static List<WriteBackData> wbdList = new ArrayList<>() ;

    /*public static void main(String[] args) throws Exception {
        //read(0,1);
        }*/

        public static Object[] read ( int startSheetIndex, int sheetNum) throws Exception {
            //1.加载excel文件
            FileInputStream fis = new FileInputStream(Constants.ExcelPath);
            //2.创建easypoi导入参数对象
            ImportParams params = new ImportParams();
            //表示从0开始读取
            params.setStartSheetIndex(startSheetIndex);
            params.setSheetNum(sheetNum);
            //importExcel(excel文件流对象，excel实体类.class,easypoi导入参数)
            List<CaseInfo> caseInfos = ExcelImportUtil.importExcel(fis, CaseInfo.class, params);
        /*for(CaseInfo caseInfo:caseInfos){
            System.out.println(caseInfo);
        }*/
            Object[] objects = caseInfos.toArray();
            /* System.out.println(caseInfos);*/
            fis.close();
            return objects;

        }
        /*批量回写
        */
        public static void batchWrite() throws Exception{
            //excel批量回写操作
            //1.读取excel中内容
            FileInputStream fis = new FileInputStream(Constants.ExcelPath);
            Workbook sheets = WorkbookFactory.create(fis);
            //2.循环遍历wbdList集合
            for(WriteBackData writeBackData:wbdList) {
                int sheetIndex = writeBackData.getSheetIndex();
                int rowNum = writeBackData.getRowNum();
                int cellNum = writeBackData.getCellNum();
                String content = writeBackData.getContent();
                //获取sheet
                Sheet sheet= sheets.getSheetAt(sheetIndex);
                //获取row
                Row row = sheet.getRow(rowNum);
                //获取cell
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                //回写内容
                cell.setCellValue(content);
            }
            //3.回写excel内容
            FileOutputStream fos = new FileOutputStream(Constants.ExcelPath);
            sheets.write(fos);
            fis.close();
            fos.close();

        }


}


