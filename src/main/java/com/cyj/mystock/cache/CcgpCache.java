package com.cyj.mystock.cache;

import com.cyj.mystock.entity.CcgpVO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/9/7.
 */
public class CcgpCache {

    private static ConcurrentHashMap<String, CcgpVO> cache = new ConcurrentHashMap<String, CcgpVO>();

    public static CcgpVO get(String key){
        return cache.get(key);
    }
    public static void set(String key,CcgpVO ccgp){
        cache.put(key,ccgp);
    }
    public static void clear(){
        cache.clear();
    }
    public static CcgpVO remove(String key){
        return cache.remove(key);
    }
    public static boolean remove(String key,CcgpVO ccgp){
        return cache.remove(key,ccgp);
    }
    public static boolean isEmpty(){
        return cache.isEmpty();
    }
    public static boolean contains(String key){
        return cache.containsKey(key);
    }
    public static Map<String, CcgpVO> getAll(){
        return cache;
    }
}
