package com.oyxt.example.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author 20190712713
 * @date 2020/1/16 11:07
 */
public class JsonTools {
    private static TypeReference<Map<String,String>> typeReference = new TypeReference<Map<String, String>>(){};

    public static Map<String,String> toMap(String string) {
        return JSON.parseObject(string, typeReference);
    }

    public static String toJsonStr(Map<String,String> value) {
        return JSON.toJSONString(value);
    }
}
