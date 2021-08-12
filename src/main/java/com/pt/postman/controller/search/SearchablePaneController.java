package com.pt.postman.controller.search;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author nate-pt
 * @date 2021/8/12 17:12
 * @Since 1.8
 * @Description 查询面板控制器
 */
public class SearchablePaneController<T> implements Initializable {
    /**
     * 创建栈式面板
     */
    @FXML
    private StackPane searchPromptLayer, searchLayer, searchFailedLayer;

    /**
     * 清除记录按钮
     */
    @FXML
    private JFXButton clearSearchFieldButton;

    /**
     * 查询文本域
     */
    @FXML
    private TextField searchTextField;

    /**
     * 设置垂下面板
     */
    private VBox searchTab, searchBox, searchPane;

    /**
     * 历史面板
     */
    private List<Searchable<T>> searchItems;


    /**
     * 元素加载完毕 初始化控制器
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchItems = new ArrayList<>();
        // 如果文本域中没有值得话不显示改面板
        searchLayer.visibleProperty().bind(searchTextField.textProperty().isNotEmpty());
       // 给文本域添加change事件
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {

        });


    }

    /**
     * 查询内容对象
     * @param <T>
     */
    protected  static  class SearchEntry<T>{
        /**
         * 上级内容
         */
        private final Parent fxmlItem;
        /**
         * 搜索栏面板
         */
        private final Searchable<T> searchable;

        public SearchEntry(Parent fxmlItem, Searchable<T> searchable) {
            this.fxmlItem = fxmlItem;
            this.searchable = searchable;
        }

        public Parent getFxmlItem() {
            return fxmlItem;
        }

        public Searchable<T> getSearchable() {
            return searchable;
        }
    }
}
