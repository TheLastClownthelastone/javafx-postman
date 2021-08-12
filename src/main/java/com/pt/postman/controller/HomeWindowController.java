package com.pt.postman.controller;

import com.pt.postman.state.ComposerState;
import com.pt.postman.sync.SyncManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author nate-pt
 * @date 2021/8/12 11:28
 * @Since 1.8
 * @Description 主窗口
 */
public class HomeWindowController implements Initializable {

    /**
     * 创建主窗口栈面板
     */
    @FXML
    private StackPane homeWindowSP, dashboardContainer;

    /**
     * 分割面板
     */
    @FXML
    private SplitPane splitPane;

    /**
     * 导航栏面板
     */
    @FXML
    private TabPane tabPane;


    /**
     * 异步管理器
     */
    private SyncManager syncManager;





    /**
     * 元素元素加载完毕之后，初始化控制器
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        syncManager = new SyncManager(this);


    }

    /**
     * 添加历史栏
     * @param state
     */
    public void addHistoryItem(ComposerState state) {

    }
}
