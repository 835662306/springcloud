package com.example.utils.excel;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 16:20
 * @Version 1.0
 */
public class ExportExcelByJXLS {

    /**
     * 生成EXCEL
     * @param (path:模板路径,from:模板名称,to:生成文件名称,beans:文件数据)
     * */
    public static String doExcel(String path, String from, String to, Map beans) {
        XLSTransformer transformer = new XLSTransformer();
        String sfrom = path + "/" + from;// 模板文件
        String sto = path + "/" + to;// 要生成的文件
        try {
            transformer.transformXLS(sfrom, beans, sto);
        } catch (ParsePropertyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return sto;
    }

    /**
     *下载EXCEL
     * @param(path:要下载的文件(包含路径),name:文件名称,response)
     * */
    public static void doDownLoad(String path, String name, HttpServletResponse response) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream fos = null;
        InputStream fis = null;
        try {
            response.reset();
            /*response.setHeader("Content-disposition",
                    "attachment;success=true;filename ="
                            + URLEncoder.encode(name, "utf-8"));

            */
            response.setHeader("Content-Disposition", "attachment; filename="+ new String(name.getBytes( "utf-8"), "ISO-8859-1"));


            File uploadFile = new File(path);
            fis = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(fis);
            fos = response.getOutputStream();
            bos = new BufferedOutputStream(fos);
            // 弹出下载对话框
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            bos.flush();
        } catch (Exception e) {
        }finally {
            try {
                if(fis!=null){
                    fis.close();
                }
                if(bis!=null){
                    bis.close();
                }
                if(fos!=null){
                    fos.close();
                }
                if(bos!=null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 生成EXCEL
     * @param (path:模板路径,from:模板名称,to:生成文件名称,beans:文件数据)
     * */
    public static String doExcelSheets(String path, String from, String to, List list, List<String> sheetNames) {
        XLSTransformer transformer = new XLSTransformer();
        String sfrom = path + "/" + from;// 模板文件
        String sto = path + "/" + to;// 要生成的文件
        try {
            FileInputStream is = new FileInputStream(sfrom);
            Workbook workbook = transformer.transformMultipleSheetsList(is, list, sheetNames, "list",new HashMap(), 0);
            workbook.write(new FileOutputStream(sto));
        } catch (ParsePropertyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return sto;
    }
}
