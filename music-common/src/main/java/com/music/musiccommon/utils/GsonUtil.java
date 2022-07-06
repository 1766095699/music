package com.music.musiccommon.utils;

import com.google.gson.Gson;

import java.util.Objects;

/**
 * @ClassName GsonUtil
 * @Description TODO
 * @Author 86183
 * @Date2022-07-0516:17
 * @Version 1.0
 **/
public class GsonUtil {
    public static String simpleObjToJson(Object obj) {
        if (Objects.isNull(obj)) return "";
        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 简单Json转对象
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T simpleJsonToObj(String json, Class<T> cls) {
        Gson gson = new Gson();
        if (Objects.isNull(json)) return null;
        T obj = gson.fromJson(json, cls);
        if (Objects.isNull(obj)) {
            return null;
        } else {
            return obj;
        }
    }

}
