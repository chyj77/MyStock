package com.cyj.mystock.cache;

import com.cyj.mystock.entity.CcgpVO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/9/7.
 */
public class ClientCache {

    private static ConcurrentHashMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

    public static Object get(String key){
        return cache.get(key);
    }
    public static void set(String key,Object obj){
        cache.put(key,obj);
    }
    public static void clear(){
        cache.clear();
    }
    public static Object remove(String key){
        return cache.remove(key);
    }
    public static boolean remove(String key,Object obj){
        return cache.remove(key,obj);
    }
    public static boolean isEmpty(){
        return cache.isEmpty();
    }
    public static boolean contains(String key){
        return cache.containsKey(key);
    }
    public static Map<String, Object> getAll(){
        return cache;
    }
}
