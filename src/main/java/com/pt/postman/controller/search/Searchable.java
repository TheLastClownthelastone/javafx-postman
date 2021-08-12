package com.pt.postman.controller.search;

/**
 * @author nate-pt
 * @date 2021/8/12 17:10
 * @Since 1.8
 * @Description 搜索栏面板
 */
public interface Searchable<T> {
    /**
     * 获取查询个数
     * @param str
     * @return
     */
    int getRelativityIndex(String str);

    /**
     *获取详情
     * @return
     */
    T getState();
}
