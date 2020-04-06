package com.plan.respository.usermanagement.util;

import com.plan.respository.usermanagement.util.bean.ExcelBean;
import com.plan.respository.usermanagement.util.enums.FileName;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class ReadExcel {


    /**
     * 判断是否是07版本的excel文件，若是则返回true,若是03版本则返回false,若都不是则抛出异常
     * @param path 文件路径
     * @return
     */
    public static Workbook chargeFileName(String path) throws Exception {
        if (path.endsWith(FileName.EXCEL_XLSX)){
            return new XSSFWorkbook(new FileInputStream(path));
        }else if (path.endsWith(FileName.EXCEL_XLS)){
           return new HSSFWorkbook(new FileInputStream(path));
        }else {
            throw new RuntimeException("It's not a excel file!");
        }
    }

    /**
     * 读取excel表格里面的所有内容
     * @param sheet 文件路径
     * @return
     * @throws Exception
     */
    public static String readContainer(String sheet) throws Exception {
        StringBuffer value = new StringBuffer();
        Workbook sheets = chargeFileName(sheet);
        for (int i = 0; i < sheets.getNumberOfSheets(); i++) {
            if (null != sheets.getSheetAt(i)){
                Sheet sheetAt = sheets.getSheetAt(i);
                for (int j = 0; j <= sheetAt.getLastRowNum(); j++) {
                    if (null != sheetAt.getRow(j)){
                        Row row = sheetAt.getRow(j);
                        for (int k = 0; k <=row.getLastCellNum(); k++) {
                            if (null != row.getCell(k)) {
                                Cell cell = row.getCell(k);
                                switch (cell.getCellTypeEnum()) {
                                    case _NONE:
                                        System.out.printf("第%d表,第%d行，第%d列的值是:%s\n" ,i,j,k, cell);
                                        value.append(cell+"\n");
                                        break;
                                    case STRING:
                                        System.out.printf("第%d表,第%d行，第%d列的值是:%s\n" ,i,j,k, cell);
                                        value.append(cell+"\n");
                                        break;
                                    case FORMULA:
                                        System.out.println("第" + i + "表；第" + j + "行，第" + k + "列的值是：" + cell);
                                        value.append(cell+"\n");
                                        break;
                                    case NUMERIC:
                                        System.out.println("第" + i + "表；第" + j + "行，第" + k + "列的值是：" + cell);
                                        value.append(cell+"\n");
                                        break;
                                    case BOOLEAN:
                                        System.out.println("第" + i + "表；第" + j + "行，第" + k + "列的值是：" + cell);
                                        value.append(cell+"\n");
                                        break;
                                    case BLANK:
                                        System.out.println("第" + i + "表；第" + j + "行，第" + k + "列的值是：" + cell);
                                        value.append(cell+"\n");
                                        break;
                                    case ERROR:
                                        System.out.println("第" + i + "表；第" + j + "行，第" + k + "列的值是：" + cell);
                                        break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return value.toString();
    }

    /**
     * 创建工作表
     * @param path 输出的excel文件和路径和名称
     * @param sheetName 工作薄的名称的数组
     * @return
     */
    public static Workbook createExcel(String path,String ... sheetName){
        FileOutputStream fos = null;
        //创建工作薄
        Workbook workbook = null;
        try {
            if (StringUtils.substringAfterLast(path,".").equals(FileName.EXCEL_XLS)){
                workbook = new HSSFWorkbook();
            }
            if (StringUtils.substringAfterLast(path,".").equals(FileName.EXCEL_XLSX)){
                workbook = new XSSFWorkbook();
            }
            if (workbook == null){
                throw new RuntimeException("This file isn't excel!");
            }
            fos = new FileOutputStream(path);
            //创建工作表
            workbook.createSheet();
            //设置工作表名
            for (int i = 0; i < sheetName.length; i++) {
                workbook.setSheetName(i,sheetName[i]);
            }
            workbook.write(fos);
           fos.close();
           return workbook;
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (fos != null){
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return workbook;
    }

    /**
     * 写到Excel中
     * @param fileName 写出的Excel文件名和路径
     * @param list 写入数据的集合
     * @param sheetName 工作表的集合
     */
    public static void writeExcel(String fileName, List<ExcelBean> list, String ... sheetName){
        OutputStream os = null;
        //创建workbook
        Workbook workbook = createExcel(fileName, sheetName);
        //取第一个sheet工作表
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            try {
                //获取工作表
                Sheet sheet = workbook.getSheetAt(i);
                //设置单元格属性
                CellStyle cellStyle = workbook.createCellStyle();
                //水平居中
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                //垂直居中
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                cellStyle.setBorderRight(BorderStyle.DASH_DOT);
                //文字属性
                Font font;
                //颜色样式
                CellStyle colorStyle = workbook.createCellStyle();
                font = workbook.createFont();
                font.setColor(IndexedColors.BLUE_GREY.getIndex());
                colorStyle.setFont(font);
                colorStyle.setAlignment(HorizontalAlignment.CENTER);
                colorStyle.setVerticalAlignment(VerticalAlignment.JUSTIFY);
                //加粗样式
                CellStyle boldStyle = workbook.createCellStyle();
                font = workbook.createFont();
                font.setBold(true);
                boldStyle.setFont(font);
                boldStyle.setAlignment(HorizontalAlignment.CENTER);
                boldStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                //写入数据
                for (int j = 0; j < list.size(); j++) {
                    //创建行
                    Row row = sheet.createRow(j);
                    ExcelBean excelBean = list.get(j);
//                    Class<?> aClass = Class.forName("com.plan.respository.usermanagement.util.bean.ExcelBean");
//                    Class<? extends ExcelBean> aClass = excelBean.getClass();
//                    Class<ExcelBean> aClass = ExcelBean.class;
                    ClassLoader loader = excelBean.getClass().getClassLoader();
                    Class<? extends ClassLoader> aClass = loader.getClass();
                    Field[] fields = aClass.getFields();
                    for (int k = 0; k < fields.length; k++) {
                        //创建列
                        Cell cell = row.createCell(k);
                        cell.setCellValue(excelBean.getId());
                        cell.setCellValue(excelBean.getName());
                        cell.setCellValue(excelBean.getAddress());
                        cell.setCellValue(excelBean.getCodeId());
                        cell.setCellValue(excelBean.getBirthday());
                        if (k ==1){
                            cell.setCellStyle(boldStyle);
                        }
                        if (k == fields.length-1){
                            cell.setCellStyle(colorStyle);
                        }
                    }
                }
                sheet.setColumnWidth(1,(int)((10+0.72)*256));
                os = new FileOutputStream(fileName);
                workbook.write(os);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (os != null){
                        os.flush();
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除Excel里面的一些数据
     * @param rowMin 起始行
     * @param rowMax 结束行
     * @param path Excel文件的全路径和名称
     * @param sheetName sheet工作表名
     * @throws Exception
     */
    public static void deleteContainer(int rowMin,int rowMax,String path,String sheetName) throws Exception {
        OutputStream os = null;
        try {
            Workbook workbook = chargeFileName(path);
            //获取与sheetName相同的工作表
            Sheet sheet = workbook.getSheet(sheetName);
            //原始数据总行数
            int rowNum = sheet.getLastRowNum() + 1;
            if (rowNum<rowMax){
                rowMax = rowNum;
            }
            if (rowNum<rowMin){
                throw new RuntimeException("Sorry, there isn't data...");
            }
            //删除数据
            for (int i = rowMin; i <= rowMax; i++) {
                Row row = sheet.getRow(i - 1);
                if (row != null){
                    sheet.removeRow(row);
                }
            }
            os = new FileOutputStream(path);
            workbook.write(os);
        } finally {
            if (os != null){
                os.flush();
                os.close();
            }
        }

    }


    public static void main(String[] args) throws Exception {
        String s = readContainer("C:/Users/86187/Documents/WeChat Files/wxid_7975429753623/FileStorage/File/2020-02/3-网上服务大厅厅局演练问题汇总及开发计划表-20200120.xls");
        System.out.println(s);
    }
}
