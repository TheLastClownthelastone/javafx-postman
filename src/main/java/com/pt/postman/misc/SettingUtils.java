package com.pt.postman.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author nate-pt
 * @date 2021/8/12 9:50
 * @Since 1.8
 * @Description 配置相关工具类
 */
public class SettingUtils {
    /**
     * 获取配置文件的对象
     */
    public static ObjectMapper jsonMapper;


    static {
        // 在静态块中给配置设置进行赋值
        jsonMapper = new ObjectMapper();
        jsonMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
    }


    /**
     * 删除头尾的引号
     * @return
     */
    public static String trimString(String s){
        return s.replaceAll("\"", "");
    }

}
