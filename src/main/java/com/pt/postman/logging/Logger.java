package com.pt.postman.logging;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author nate-pt
 * @date 2021/8/11 16:49
 * @Since 1.8
 * @Description 日志对象
 */
public class Logger {
    /**
     * 日志层级
     */
    private Level writeLevel;

    /**
     * 日志空模板
     */
    private String logEntryTemplate;

    /**
     * 日志文件路径
     */
    private String logFilePath = "/logs/" + LocalDate.now() + ".html";


    Logger(Level level) {
        this.writeLevel = level;
        // 创建日志文件
        _create_logs_file();
        // 将空模板进行解析
        logEntryTemplate = _read_file(getClass().getResourceAsStream("/templates/LogEntry.html"));
    }

    /**
     * 创建日志文件
     */
    private void _create_logs_file() {
        // 获取日志目录
        File logDirectory = new File("/logs/");
        if (!logDirectory.exists()) {
            logDirectory.mkdir();
        }

        File logFile = new File(logFilePath);

        try {
            // 如果该文件已经存在了，则没必要重新新建
            if (logFile.exists()) {
                return;
            }
            logFile.createNewFile();

            //获取日志文件写缓冲流
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath));
            // 获取日志模板
            String logFileTemplates = _read_file(getClass().getResourceAsStream("/templates/LogsFile.html"));
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            logFileTemplates = logFileTemplates.replace("%% Date %%", dateTimeFormatter.format(LocalDate.now()));
            writer.flush();
            writer.write(logFileTemplates);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写日志
     */
    synchronized void log() {
        // info 日志
        if (LoggingService.log.level.equals(Level.INFO)) {
            System.out.println(LoggingService.log.level + " " + LoggingService.log.time + " " + LoggingService.log.message);
        } else {
            System.err.println(LoggingService.log.level + " " + LoggingService.log.time + " " + LoggingService.log.message);
        }

        if (LoggingService.log.level.greaterThanEqualTo(this.writeLevel)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
                writer.flush();
                writer.append(_get_entry_log(LoggingService.log));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 读取文件
     *
     * @param in
     * @return
     */
    private String _read_file(InputStream in) {
        StringBuilder sb = new StringBuilder();
        // 利用Scanner 读取该输入流
        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            // 一行一行解析
            sb.append(scanner.nextLine());
            sb.append("\n");
        }
        scanner.close();

        return sb.toString();
    }


    /**
     * 获取空白日志
     *
     * @param log
     * @return
     */
    private String _get_entry_log(Log log) {
        String logEntry = this.logEntryTemplate;
        logEntry = logEntry.replace("%% LogLevel %%", log.level.toString());
        logEntry = logEntry.replace("%% Time %%", log.time);
        logEntry = logEntry.replace("%% Message %%", log.message);
        StringBuilder builder = new StringBuilder();

        // 日志中有异常
        if (log.exception != null) {
            // 获取异常信息
            StackTraceElement[] stackTrace = log.exception.getStackTrace();
            builder.append(log.exception.toString());
            builder.append("<br>\n");
            // 如果jvm 返回的堆栈信息不为空的话
            if (stackTrace.length != 0) {
                for (StackTraceElement element : stackTrace) {
                    builder.append(" -- ");
                    builder.append(element.toString());
                    builder.append("<br>\n");
                }
            } else {
                builder.append(" 无堆栈信息。。。。");
            }
        }
        logEntry = logEntry.replace("%% StackTrace %%", builder.toString());

        return logEntry;
    }
}
