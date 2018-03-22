package com.example.utils;

import jodd.io.StringInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/22 10:20
 * @Version 1.0
 */
public class StreamUtil {
    private static Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    private static final int BUFFER_SIZE = 4096;

    /**
     * 将inputStream转String
     * @param inputStream
     * @return
     */
    public static String inputStreamToString(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[BUFFER_SIZE];
        String str = null;
        int count;
        try {
            while ((count = inputStream.read(bytes, 0 , BUFFER_SIZE)) != -1){
                outputStream.write(bytes, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            str = new String(outputStream.toByteArray(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeStream(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 将inputStream转为指定字符的字符串
     * @param inputStream
     * @param encodeing
     * @return
     */
    public static String inputStreamToString(InputStream inputStream, String encodeing) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[BUFFER_SIZE];
        int count;
        try {
            while ((count = inputStream.read(bytes, 0, BUFFER_SIZE)) != -1) {
                outputStream.write(bytes, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = null;
        try {
              str = new String(outputStream.toByteArray(), encodeing);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeStream(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * 将字符串转为InputStream
     * @param str
     * @return
     */
    public static InputStream stringToInputStream(String str){
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeStream(inputStream, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }

    public static byte[] stringToByte(String str){
        byte[] bytes = null;
        try {
            bytes = inputStreamToByte(stringToInputStream(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 将inputStream转byte数组
     * @param inputStream
     * @return
     */
    public static byte[] inputStreamToByte(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[BUFFER_SIZE];
        int count;
        try {
            while ((count = inputStream.read(bytes, 0, BUFFER_SIZE)) != -1) {
                outputStream.write(bytes,0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeStream(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * byte数组转inputStream
     * @param bytes
     * @return
     */
    public static InputStream byteToInputStream(byte[] bytes) {
        InputStream inputStream = null;
        try {
            inputStream = new StringInputStream(inputStreamToString(byteToInputStream(bytes)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeStream(inputStream, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }

    /**
     * byte数组转inputStream
     * @param bytes
     * @return
     */
    public static InputStream byteArrayToInputStream(byte[] bytes) {
        ByteArrayInputStream inputStream = null;
        try {
            inputStream = new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeStream(inputStream, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }

    /**
     * byte数组转String
     * @param bytes
     * @return
     */
    public static String byteToString(byte[] bytes) {
        InputStream inputStream = null;
        try {
            inputStream = byteToInputStream(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStreamToString(inputStream);
    }

    /**
     * InputStream 转换成byte[]
     * @param is
     * @return
     * @throws IOException
     */
    public byte[] inputStreamToBytes(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[BUFFER_SIZE];
        int len = 0;

        while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
            baos.write(b, 0, len);
        }
        baos.flush();
        byte[] bytes = baos.toByteArray();
        logger.info(new String(bytes));
        return bytes;
    }

    /**
     * 关闭流
     * @param inputStream
     * @param outputStream
     * @throws Exception
     */
    private static void closeStream (InputStream inputStream, OutputStream outputStream) throws Exception{
        inputStream.close();
        outputStream.close();
    }
}
