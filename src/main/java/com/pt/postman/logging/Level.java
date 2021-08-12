package com.pt.postman.logging;

/**
 * @author nate-pt
 * @date 2021/8/11 16:50
 * @Since 1.8
 * @Description 日志层级
 */
public enum Level {
    /**
     * 严重日志
     */
    SEVERE,
    /**
     * 警告日志
     */
    WARNING,
    /**
     * 普通日志
     */
    INFO;

    /**
     * 获取日志优先级
     *
     * @return
     */
    int getValue() {
        switch (this) {
            case SEVERE:
                return 3;
            case WARNING:
                return 2;
            default:
                return 1;
        }
    }

    /**
     * 日志优先级比较
     *
     * @param level
     * @return
     */
    boolean greaterThanEqualTo(Level level) {
        return this.getValue() >= level.getValue();
    }

}
