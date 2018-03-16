package com.example.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 16:27
 * @Version 1.0
 */
public class OrderAble {

    private static Log log = LogFactory.getLog(OrderAble.class);

    /**
     * 将map　中bykey转为double对比
     * @param list
     * @param byKey
     * @param desc
     */
    public static void order(List<Map> list, final String byKey, final boolean desc){
        Collections.sort(list, new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                int result=0;
                try {
                    double o1V=Double.valueOf(o1.get(byKey) + "") ;
                    double o2V=Double.valueOf(o2.get(byKey) + "");
                    result=Double.compare(o1V,o2V);
                } catch (Exception e) {
                    log.error("排序异常",e);
                }
                return desc?0-result:result;
            }
        });
    }
}
