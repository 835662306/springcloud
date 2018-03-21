package com.example.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 文件操作工具类
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/21 16:48
 * @Version 1.0
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    public static String getFileExtend(String fileName) {
        if(fileName != null && (fileName.length() > 0)){
            int i = fileName.indexOf(".");
            if(i > 0 && i < (fileName.length() - 1)){
                return (fileName.substring(i+1)).toLowerCase();
            }
        }
        return "";
    }

    /**
     * 获取文件名（不含后缀）,将空白替换为""
     * @param fileName
     * @return
     */
    public static String getFilePrefix(String fileName){
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
    }

    /**
     * 复制文件
     * @param inputFile
     * @param outputFile
     * @throws FileNotFoundException
     */
    public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException{
        File input = new File(inputFile);
        File output = new File(outputFile);
        FileInputStream inputStream = new FileInputStream(input);
        FileOutputStream outputStream = new FileOutputStream(output);

        int temp = 0;
        byte[] bytes = new byte[10240];
        try {
            while ((temp = inputStream.read(bytes))!= -1) {
                outputStream.write(bytes, 0 , temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 判断文件是否是图片
     * @param fileName
     * @return
     */
    public static boolean isPicture(String fileName) {
        if(StringUtils.isEmpty(fileName)){
            return false;
        }
        // 获得文件后缀名
        String tmpName = getFileExtend(fileName);
        // 声明图片后缀名数组
        String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" },
                { "gif", "2" }, { "jfif", "3" }, { "jpe", "4" },
                { "jpeg", "5" }, { "jpg", "6" }, { "png", "7" },
                { "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
        // 遍历名称数组
        for (int i = 0; i < imgeArray.length; i++) {
            // 判断单个类型文件的场合
            if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除指定的文件
     *
     * @param strFileName
     *            指定绝对路径的文件名
     * @return 如果删除成功true否则false
     */
    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);

        if (!fileDelete.exists() || !fileDelete.isFile()) {
            logger.info("错误: " + strFileName + "不存在!");
            return false;
        }

        logger.info("--------成功删除文件---------"+strFileName);
        return fileDelete.delete();
    }

    public static void main(String [] args){
//        System.out.println(getFilePrefix("123 1 32.tex"));

        System.out.println(isPicture("123.gisf"));
    }
}
