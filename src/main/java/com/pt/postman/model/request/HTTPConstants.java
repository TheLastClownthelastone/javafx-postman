package com.pt.postman.model.request;

import javax.ws.rs.core.MediaType;

/**
 * @author nate-pt
 * @date 2021/8/12 16:28
 * @Since 1.8
 * @Description request 信息
 */
public class HTTPConstants {
    /**
     * get请求
     */
    public static final String GET = "GET";
    /**
     * post 请求
     */
    public static final String POST = "POST";
    /**
     * put请求
     */
    public static final String PUT = "PUT";
    /**
     * patch 请求
     */
    public static final String PATCH = "PATCH";
    /**
     * delete 请求
     */
    public static final String DELETE = "DELETE";


    /**
     * 在请求体重选择raw 模式的时候PLAIN_TEXT
     */
    public static final String PLAIN_TEXT = "PLAIN TEXT";
    /**
     * 在请求体重选择raw 模式的时候JSON
     */
    public static final String JSON = "JSON";
    /**
     * 在请求体重选择raw 模式的时候XML
     */
    public static final String XML = "XML";
    /**
     * 在请求体重选择raw 模式的时候HTML
     */
    public static final String HTML = "HTML";

    /**
     * 获取对应contentType 对应的raw格式
     *
     * @param contentType
     * @return
     */
    public static String getSimpleContentType(String contentType) {
        switch (contentType) {
            case MediaType.APPLICATION_JSON:
                return JSON;
            case MediaType.APPLICATION_XML:
                return XML;
            case MediaType.TEXT_HTML:
                return HTML;
            default:
                return PLAIN_TEXT;
        }
    }

    /**
     * 根据rawType 获取contentType 对应的值
     * @param rawType
     * @return
     */
    public static String getComplexContentType(String rawType) {
        switch (rawType) {
            case JSON:
                return MediaType.APPLICATION_JSON;
            case XML:
                return MediaType.APPLICATION_XML;
            case HTML:
                return MediaType.TEXT_HTML;
            default:
                return MediaType.TEXT_PLAIN;
        }
    }
}
