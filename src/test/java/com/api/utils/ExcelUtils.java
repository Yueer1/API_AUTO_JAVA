package com.api.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.api.pojo.CaseInfo;
import com.api.pojo.MappingDemo;

import java.io.FileInputStream;
import java.util.List;

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
        read();
    }

    public static void read() throws Exception {
        //1、加载excel文件
        FileInputStream fis = new FileInputStream("src/test/resources/cases_v1.xls");
        //2、创建easypoi导入参数对象
        ImportParams params = new ImportParams();
        params.setStartSheetIndex(1);
        params.setSheetNum(1);
        /*//importExcel（excel文件流对象,excel映射实体类.class,导入参数）
        List<CaseInfo> caseInfosList = ExcelImportUtil.importExcel(fis, CaseInfo.class, params);
        for (CaseInfo caseInfo : caseInfosList) {
            System.out.println(caseInfo);
        }*/

        List<MappingDemo> mappingsList = ExcelImportUtil.importExcel(fis, MappingDemo.class, params);
        for (MappingDemo mapping : mappingsList) {
            System.out.println(mapping);
            fis.close();
        }
    }
}

