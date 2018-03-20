package com.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/19 15:29
 * @Version 1.0
 */
public class CollectionUtils {
    private static Logger logger = LoggerFactory.getLogger(CollectionUtils.class);

    /**
     * 将list集合转化为Map,可将list对象的任意字段当Map的key
     * @param list          对象集合
     * @param methodName    获取字段值得方法, 比如 "getId"
     * @param <v>           key的类型
     * @param <T>           对象类型
     * @return              Map
     */
    public static <V, T> Map<V, List<T>> listToListMap(List<T> list, String methodName){
        Map<V, List<T>> map = new HashMap<>();
        if(list.size() == 0){
            return map;
        }
        try {
            for(T obj: list){
                Method method = obj.getClass().getMethod(methodName);
                V key = (V) method.invoke(obj);
                if (map.get(key) == null){
                    List<T> objList = new ArrayList<>();
                    objList.add(obj);
                    map.put(key, objList);
                }else {
                    map.get(key).add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("List 转 Map 失败~！");
        }
        return map;
    }
}
