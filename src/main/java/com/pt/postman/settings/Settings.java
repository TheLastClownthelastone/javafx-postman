package com.pt.postman.settings;

/**
 * @author nate-pt
 * @date 2021/8/11 16:27
 * @Since 1.8
 * @Description 配置对象
 */
public class Settings {
    /**
     * 是否开启链接超时
     */
    public static boolean  connectionTimeOutEnable = false;
    /**
     * 设置超时时间为10秒
     */
    public static int connectionTimeOut = 10000;

    /**
     * 是否开启读取超时
     */
    public static boolean readTimeOutEnable = false;

    /**
     * 设置读取超时时间为30秒
     */
    public static int readTimeOut  = 30000;

    /**
     * 设置默认主题
     */
    public static String there = "Adreana";

    /**
     * 设置默认字体主题
     */
    public static String syntaxThere = "Moondust";

    /**
     * 默认历史范围
     */
    public static int showHistoryRange = 7;

    /**
     * 默认开启富文本编辑
     */
    public static  boolean editorWrapText = true;

    /**
     * 默认获取源冲内存数据中进行获取
     */
    public static String fetchSource = "SQLite";
}
