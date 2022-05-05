package com.api.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.api.pojo.CaseInfo;
import com.api.pojo.MappingDemo;
import com.api.pojo.WriteBackData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.omg.CORBA.portable.ValueOutputStream;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.api.utils.Constants.EXCEL_PATH;

/**
 * @Project: api_auto_recent
 * @Site:
 * @Forum:
 * @Copyright: 2022 版权所有
 * @Author: wangxy
 * @Create: 2022-02-16 15:12
 * @Desc： Excel表单处理工具类
 **/
public class ExcelUtils {
    public static void main(String[] args) throws Exception {
        //read("1","1");
    }

    public static List<WriteBackData> wbdlist = new ArrayList<>();

    public static Object[] read(int StartSheetIndex,int SheetNum) throws Exception {
        //1、加载excel文件
        FileInputStream fis = new FileInputStream(Constants.EXCEL_PATH);
        //2、创建easypoi导入参数对象
        ImportParams params = new ImportParams();
        params.setStartSheetIndex(StartSheetIndex);
        params.setSheetNum(SheetNum);
        /*//importExcel（excel文件流对象,excel映射实体类.class,导入参数）
        List<CaseInfo> caseInfosList = ExcelImportUtil.importExcel(fis, CaseInfo.class, params);
        for (CaseInfo caseInfo : caseInfosList) {
            System.out.println(caseInfo);
        }*/

        //List<MappingDemo> mappingsList = ExcelImportUtil.importExcel(fis, MappingDemo.class, params);
       /* for (MappingDemo mapping : mappingsList) {
            System.out.println(mapping);
            fis.close();
        }*/
        List<CaseInfo> caseInfosList = ExcelImportUtil.importExcel(fis, CaseInfo.class, params);
        Object[] objects = caseInfosList.toArray();
        fis.close();
        return objects;
    }

    public static void batchwrite() throws Exception{
        //1、读取文件中的内容
        FileInputStream fis = new FileInputStream(Constants.EXCEL_PATH);
        Workbook sheets = WorkbookFactory.create(fis);
        //2、循环遍历wdblist集合
        for (WriteBackData writeBackData:wbdlist){
           int sheetIndex = writeBackData.getSheetIndex();
           int rowNum = writeBackData.getRownum();
           int cellNum = writeBackData.getCellnum();
           String content = writeBackData.getContext();
           //获取sheet
           Sheet sheet = sheets.getSheetAt(sheetIndex);
           //获取row
            Row row = sheet.getRow(rowNum);
            //获取cell
            Cell cell = row.getCell(cellNum,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            //回写内容
            cell.setCellValue(content);
        }
        //回写excel
        FileOutputStream fos = new FileOutputStream(Constants.EXCEL_PATH);
        sheets.write(fos);
        fis.close();
        fos.close();


    }
}

