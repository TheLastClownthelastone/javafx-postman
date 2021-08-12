package com.pt.ui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author nate-pt
 * @date 2021/8/12 17:31
 * @Since 1.8
 * @Description 铜鼓父模板控制 对应的是否显示
 */
public class Text extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane pane = new StackPane();
        TextField textField = new TextField("文本框");
        // 如果文本域中没有值得话则整个TextFiled 都不显示
        // 给文本域中输入框添加监听器
        textField.textProperty().addListener(new ChangeListener<String>() {
            /**
             * @param observable 最新文本对象
             * @param oldValue 老值
             * @param newValue 最新值
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("observable 值为："+observable);
                System.out.println("oldValue 值为："+oldValue);
                System.out.println("newValue 值为："+newValue);
            }
        });
        pane.visibleProperty().bind(textField.textProperty().isNotEmpty());
        pane.getChildren().add(textField);
        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.setHeight(200);
        primaryStage.setWidth(400);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
