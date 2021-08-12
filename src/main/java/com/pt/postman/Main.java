package com.pt.postman;

import com.pt.postman.settings.SettingLoader;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author nate-pt
 * @date 2021/8/11 16:25
 * @Since 1.8
 * @Description 程序入口
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载配置
        SettingLoader loader = new SettingLoader();
        // 加入主线程，要等改线程执行完毕，往下执行主线程
        loader.settingsLoaderThead.join();

        // Fxml 加载javafx应用

    }
}
