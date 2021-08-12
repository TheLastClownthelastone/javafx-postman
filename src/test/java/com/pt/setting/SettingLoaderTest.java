package com.pt.setting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static com.pt.postman.logging.LoggingService._info;

/**
 * @author nate-pt
 * @date 2021/8/12 10:59
 * @Since 1.8
 * @Description 测绘配置文件修改系统变量中的值
 */
public class SettingLoaderTest implements Runnable {
    private Thread innerThread;

    private JsonNode jsonNode;


    public SettingLoaderTest() {
        innerThread = new Thread(this, "配置加载线程启动中。。。。。");
        innerThread.start();
    }

    @Override
    public void run() {
        _info(Thread.currentThread().getName(), LocalDateTime.now());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        try {
            this.jsonNode = objectMapper.readTree(getClass().getResourceAsStream("/conf/test.json"));
            JsonNode a = jsonNode.get("a");
            if (a != null) {
                String s = a.asText();
                SettingTest.default_a = s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println(SettingTest.default_a);
        SettingLoaderTest settingLoaderTest = new SettingLoaderTest();
        // 睡眠主线程等待加载配置线程执行完成
        //TimeUnit.SECONDS.sleep(6);

        // 也可以join方法让主线程等待
        settingLoaderTest.innerThread.join();
        System.out.println(SettingTest.default_a);
    }
}
