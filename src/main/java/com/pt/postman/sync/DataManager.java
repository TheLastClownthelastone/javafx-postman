package com.pt.postman.sync;

import com.pt.postman.state.ComposerState;

import java.util.List;

/**
 * @author nate-pt
 * @date 2021/8/12 11:44
 * @Since 1.8
 * @Description 数据管理器
 */
public interface DataManager {
    String HEADER = "Header";
    String PARAM = "Param";
    String URL_STRING = "URLString";
    String FORM_STRING = "FormString";
    String AUTH_METHOD = "AuthMethod";
    String FILE = "File";
    String BASIC = "Basic";
    String DIGEST = "Digest";
    String ID = "ID";

    /**
     * 保存请求信息
     * @param state
     */
    void saveState(ComposerState state) throws Exception;


    /**
     * 获取管理器的标识
     * @return
     */
    String getIdentifier();

    /**
     * 获取历史详情
     * @return
     * @throws Exception
     */
    List<ComposerState> getHistory() throws Exception;

    /**
     * 获取最后添加的详情
     * @return
     */
    ComposerState getLastAdded();

}
