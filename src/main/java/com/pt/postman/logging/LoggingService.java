package com.pt.postman.logging;

import com.google.common.util.concurrent.MoreExecutors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;

/**
 * @author nate-pt
 * @date 2021/8/11 16:46
 * @Since 1.8
 * @Description 日志处理
 */
public class LoggingService {
    /**
     * 创建线程池（使用guava工具类进行创建）
     */
    private static Executor executor = MoreExecutors.directExecutor();
    /**
     * 日志对象
     */
    private static Logger logger = new Logger(Level.INFO);
    /**
     * 日期解析器
     */
    private static DateTimeFormatter dateFormatter =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    /**
     * 日志内容对象
     */
    static final  Log log = new Log();


    /**
     * 错误日志线程
     */
    private static Runnable _severe_logger = ()->{
        log.level = Level.SEVERE;
        logger.log();
    };

    /**
     * 警告日志线程
     */
    private static Runnable _warning_logger = ()->{
        log.level = Level.WARNING;
        logger.log();
    };

    /**
     * 普通日志线程
     */
    private static Runnable _info_logger = ()->{
        log.level = Level.INFO;
        logger.log();
    };

    /**
     * 写错误日志
     * @param message
     * @param e
     * @param dateTime
     */
    public static void _severe(String message, Exception e , LocalDateTime dateTime){
        _set_values(message, e, dateTime);
        // 异步执行写入日志
        executor.execute(_severe_logger);
    }

    /**
     * 写警告日志
     * @param message
     * @param e
     * @param dateTime
     */
    public static void _warning(String message, Exception e , LocalDateTime dateTime){
        _set_values(message, e, dateTime);
        executor.execute(_warning_logger);
    }

    /**
     * 写普通日志
     * @param message
     * @param dateTime
     */
    public static  void _info(String message,LocalDateTime dateTime){
        _set_values(message,null,dateTime);
        executor.execute(_info_logger);
    }

    /**
     * 给日志内容对象进行赋值
     * @param message
     * @param exception
     * @param dateTime
     */
    private static void _set_values(String message, Exception exception , LocalDateTime dateTime){
        log.message = message;
        log.exception = exception;
        log.time = dateFormatter.format(dateTime);
    }


}
