package com.example.utils.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * excel读取
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 13:55
 * @Version 1.0
 */
public class ExcelReadUtil {

    /**
     * InputStream 读取excel文件
     * @param in
     * @return
     * @throws Exception
     */
    public static final Workbook excelRead(InputStream in) throws Exception{
        if(!in.markSupported()){
            in = new PushbackInputStream(in, 8);
        }
        try {
            //for excel 2003
            if(POIFSFileSystem.hasPOIFSHeader(in)){
                HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
                return hssfWorkbook;
            }else if(POIXMLDocument.hasOOXMLHeader(in)) { //for excel 2007,2010
                XSSFWorkbook xssfWorkbook = new XSSFWorkbook(in);
                return xssfWorkbook;
            }//exception
            else{
                String exMsg="Your InputStream was neither an OLE2 stream, nor an OOXML stream";
                throw new Exception(exMsg);
            }
        } catch (Exception e) {
            throw new Exception("Open excel file error.");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据文件路径读取Excel文件
     * @param path
     * @return
     * @throws Exception
     */
    public static final Workbook excelRead(String path) throws Exception {
        InputStream in;
        try {
            in = new FileInputStream(path);
            return excelRead(in);
        } catch (FileNotFoundException e) {
            throw new Exception("this file is not excel, path:"+ path);
        }
    }

    /**
     * 根据文件获取文件
     * @param file
     * @return
     * @throws Exception
     */
    public static final Workbook excelRead(File file) throws Exception{
        InputStream in;
        try {
            in = new FileInputStream(file);
            return excelRead(in);
        } catch (Exception e) {
            throw new Exception("this file is not excel, path:"+ file.getAbsolutePath());
        }
    }

    /**
     * 根据sheetName获取Sheet
     * @param workbook
     * @param sheetName
     * @return
     */
    public static final Sheet getSheet(Workbook workbook, String sheetName){
        return workbook.getSheet(sheetName);
    }

    /**
     * 根据sheet页index 获取Sheet
     * @param wb
     * @param index
     * @return
     */
    public static final Sheet getSheet(Workbook wb , int index) {
        return wb.getSheetAt(index) ;
    }

    /**
     * 获取表数据
     * @param sheet
     * @param cellNum
     * @return
     */
    public static final List<Object[]> listFromSheet(Sheet sheet, int cellNum) {
        int rowTotal = sheet.getPhysicalNumberOfRows(); //excel所以行
        List<Object[]> list = new ArrayList<>();
        //遍历excel的所有行
        for(int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);

            if(row == null) continue;//跳过本次循环
            // 不能用row.getPhysicalNumberOfCells()，可能会有空cell导致索引溢出
            // 使用row.getLastCellNum()至少可以保证索引不溢出，但会有很多Null值，如果使用集合的话，就不说了
            Object[] cells = new Object[cellNum] ;
            for(int c = 0 ; c < cellNum ; c++) {
                Cell cell = row.getCell(c) ;

                cells[c] = getValueFromCell(cell) ;
            }
            list.add(cells) ;
        }

        return list;
    }

    /**
     * 根据每个cell的类型，返回cell内容
     * @param cell
     * @return
     */
    private static Object getValueFromCell(Cell cell) {
        if(cell == null) {
            return null ;
        }
        String value = null ;
        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC :   // 数字
                if(HSSFDateUtil.isCellDateFormatted(cell)) {        // 如果是日期类型
                    value = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cell.getDateCellValue()) ;
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    value = cell.getStringCellValue();
                }
                break ;
            case Cell.CELL_TYPE_STRING:     // 字符串
                value = cell.getStringCellValue() ;
                break ;
            case Cell.CELL_TYPE_FORMULA:    // 公式
                // 用数字方式获取公式结果，根据值判断是否为日期类型
                double numericValue = cell.getNumericCellValue() ;
                if(HSSFDateUtil.isValidExcelDate(numericValue)) {   // 如果是日期类型
                    value = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(cell.getDateCellValue()) ;
                } else  value = String.valueOf((int)numericValue) ;
                break ;
            case Cell.CELL_TYPE_BLANK:              // 空白
                value = "";
                break ;
            case Cell.CELL_TYPE_BOOLEAN:            // Boolean
                value = String.valueOf(cell.getBooleanCellValue()) ;
                break ;
            case Cell.CELL_TYPE_ERROR:              // Error，返回错误码
                value = String.valueOf(cell.getErrorCellValue()) ;
                break ;
            default:value = StringUtils.EMPTY ;break ;
        }
        // 使用[]记录坐标
        return value ;
    }
}
