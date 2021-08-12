package com.pt.postman.logging;

/**
 * @author nate-pt
 * @date 2021/8/11 16:50
 * @Since 1.8
 * @Description 日志内容
 */
public class Log
{
    /**
     * 日志级别
     */
    Level level;

    /**
     * 日志层级
     */
    String message;

    /**
     * 日志时间
     */
    String time;

    /**
     * 执行异常
     */
    Exception exception;
}
