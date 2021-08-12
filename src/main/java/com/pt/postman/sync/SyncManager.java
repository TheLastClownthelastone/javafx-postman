package com.pt.postman.sync;

import com.google.common.util.concurrent.MoreExecutors;
import com.pt.postman.controller.HomeWindowController;
import com.pt.postman.exception.DuplicateException;
import com.pt.postman.settings.Settings;
import com.pt.postman.state.ComposerState;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

import static com.pt.postman.logging.LoggingService._severe;

/**
 * @author nate-pt
 * @date 2021/8/12 11:39
 * @Since 1.8
 * @Description 异步管理器
 */
public class SyncManager {
    /**
     * 将对应数据管理存在内存中
     */
    private static HashMap<String,DataManager> managers;
    /**
     * 主窗口控制器
     */
    private static HomeWindowController homeWindowController;
    /**
     * 异步管理器中添加内置线程池
     */
    private static Executor executor = MoreExecutors.directExecutor();



    private static  HistorySaver historySaver;


    public SyncManager(HomeWindowController homeWindowController){
        SyncManager.homeWindowController = homeWindowController;
        managers = new HashMap<>();
        historySaver = new HistorySaver();
        try {
            _register_manager(new SQLiteManager());
        } catch (DuplicateException e) {
            _severe("SQLite 注册失败..",e, LocalDateTime.now());
        }
    }

    /**
     * 获取历史
     * @return
     */
    public List<ComposerState> getHistory(){
        List<ComposerState> history = null;
        try {
            if (managers.get(Settings.fetchSource) == null) {
                _severe("数据库管理未获取到。。",null,LocalDateTime.now());
                // 采用SQLite 获取
               history= managers.get("SQLite").getHistory();
            }else {
                history= managers.get(Settings.fetchSource).getHistory();
            }
        } catch (Exception e) {
            _severe("获取历史信息失败...",e,LocalDateTime.now());
        }
        return history;
    }


    /**
     * 存储请求信息
     * @param state
     */
    public void saveState(ComposerState state){
        // 如果与managers 最后一个相同的话不用相加
        if (state.equals(managers.get(Settings.fetchSource).getLastAdded())) {
            return;
        }
        // 调用线程异步添加请求详情
        historySaver.newState = state;
        executor.execute(historySaver);

        // 主窗口添加历史拦
        homeWindowController.addHistoryItem(state);
    }

    /**
     * 管理器进行注册
     * @param dataManager
     */
    private void _register_manager(DataManager dataManager) throws DuplicateException {
        if (managers.containsKey(dataManager.getIdentifier())) {
            throw  new DuplicateException("【DuplicateException】 当前管理: \'"+dataManager.getIdentifier()+"\'"+"已存在了。。");
        }else {
            managers.put(dataManager.getIdentifier(),dataManager);
        }
    }


    /**
     * 历史记录(利用异步线程记录)
     */
    private static class  HistorySaver implements  Runnable{

        private ComposerState newState;

        @Override
        public void run() {
            for (DataManager manager : managers.values()) {
                try {
                    manager.saveState(newState);
                } catch (Exception e) {
                    _severe("无法添加历史详情",e,LocalDateTime.now());
                }
            }
        }
    }

}
