package com.pt.postman.settings;

import com.fasterxml.jackson.databind.JsonNode;
import com.pt.postman.misc.SettingUtils;

import java.io.File;
import java.time.LocalDateTime;

import static com.pt.postman.logging.LoggingService._info;

/**
 * @author nate-pt
 * @date 2021/8/11 16:27
 * @Since 1.8
 * @Description 配置加载器(进行异步加载)
 */
public class SettingLoader implements Runnable {

    /**
     * 内部设置线程
     */
    public Thread settingsLoaderThead;

    /**
     * 配置信息
     */
    private JsonNode jsonNode;

    /**
     * 在构造方法中实例化内部线程并且启动
     */
    public SettingLoader() {
        settingsLoaderThead = new Thread(this, "配置加载线程");
        settingsLoaderThead.start();
    }

    @Override
    public void run() {
        try {
            // 获取配置文件
            File settingFile = new File("/config/settings.json");
            // 如果配置文件存在的话使用配置文件中的设置，否则走默认配置
            if (settingFile.exists()) {
                _info("配置文件找到，配置加载", LocalDateTime.now());
            } else {
                _info("配置文件未找到，无法加载配置", LocalDateTime.now());
                return;
            }
            // 将配置文件中的值取出
            jsonNode = SettingUtils.jsonMapper.readTree(settingFile);
            Settings.connectionTimeOutEnable = _set_boolean_setting(Settings.connectionTimeOutEnable,"connectionTimeOutEnable");
            // 设置链接超时时间
            if (Settings.connectionTimeOutEnable) {
                Settings.connectionTimeOut = _set_int_setting(Settings.connectionTimeOut,"connectionTimeOut");
            }

            Settings.readTimeOutEnable = _set_boolean_setting(Settings.readTimeOutEnable,"readTimeOutEnable");
            // 设置读取超时时间
            if (Settings.readTimeOutEnable) {
                Settings.readTimeOut = _set_int_setting(Settings.readTimeOut,"readTimeOut");
            }
            Settings.editorWrapText = _set_boolean_setting(Settings.editorWrapText, "editorWrapText");

            Settings.there = SettingUtils.trimString(_set_string_setting(Settings.there, "there"));
            Settings.syntaxThere = SettingUtils.trimString(_set_string_setting(Settings.syntaxThere, "syntaxTheme"));
            Settings.showHistoryRange = _set_int_setting(Settings.showHistoryRange, "showHistoryRange");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 配置中有则读取配置中的
     * 配置中没哟则用默认的
     * @param defaultValue
     * @param s
     * @return
     */
    private boolean _set_boolean_setting(boolean defaultValue, String s) {
        // 从全局配置获取内容
        JsonNode value = this.jsonNode.get(s);

        if (value != null) {
            defaultValue = value.asBoolean();
            _info("【"+s+"】:加载到："+defaultValue,LocalDateTime.now());
        }else {
            _info("【"+s+"】:未加载到配置",LocalDateTime.now());
        }
        return defaultValue;
    }


    /**
     * 获取配置中的int中如果没有获取到则使用默认值
     * @param defaultValue
     * @param s
     * @return
     */
    private int _set_int_setting(int defaultValue,String s){
        JsonNode value = jsonNode.get(s);

        if (value != null) {
            defaultValue = value.asInt();
           _info("[" + s + "]: Loaded: " + defaultValue, LocalDateTime.now());
        } else {
            _info("[" + s + "]: Not found. Using default value.", LocalDateTime.now());
        }

        return defaultValue;
    }

    private String _set_string_setting(String defaultValue, String s) {
        JsonNode value = jsonNode.get(s);

        if (value != null) {
            defaultValue = value.asText();
            _info("[" + s + "]: Loaded: " + defaultValue, LocalDateTime.now());
        } else {
            _info("[" + s + "]: Not found. Using default value.", LocalDateTime.now());
        }

        return defaultValue;
    }

}
