package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/15 15:16
 * @Version 1.0
 */
public class ReadFileUtil {

    private static final Logger log = LoggerFactory.getLogger(ReadFileUtil.class);
    private static final TreeMap<String, String> map = new TreeMap<>();//会自动对数据排序

    static {
        BufferedReader reader = null;
        try {
            File file = ResourceUtils.getFile("classpath:config/region_code");
            reader = new BufferedReader(new FileReader(file));

            List<String> tagNameList = new ArrayList<>();

            //当返回null时，表示读到文件末尾
            String line = null;
            while ((line = reader.readLine()) != null) {
                //将每一行放入list
                tagNameList.add(line);
            }
            reader.close();
            for(String tagName : tagNameList){
                if(tagName.indexOf("\t\t\t") == -1){
                    String[] split = tagName.split("\\t+");
                    map.put(split[0].trim(), split[1].trim());
                }
            }
        } catch (Exception e) {
            log.error("init region_code file error !!!");
            try {
                reader.close();
            } catch (IOException e1) {
                reader = null;
            }
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                reader = null;
            }
        }
    }

    /**
     * 通过id 获取 城市 或 省份 名称
     */
    public static String getRegionName(String id) {
        if (id == null) {
            return null;
        }
        return map.get(id) == null ? id : map.get(id);
    }

    public static void main(String [] args){
        System.out.println(map);
    }
}
