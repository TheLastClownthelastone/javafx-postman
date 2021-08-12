package com.pt.logging;

import com.pt.postman.logging.LoggingService;

import java.time.LocalDateTime;

import static com.pt.postman.logging.LoggingService._info;
import static com.pt.postman.logging.LoggingService._warning;
import static com.pt.postman.logging.LoggingService._severe;

/**
 * @author nate-pt
 * @date 2021/8/11 17:57
 * @Since 1.8
 * @Description 测试日志打印功能
 */
public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        _info("dddddd", LocalDateTime.now());
        _severe("dddddd",new RuntimeException("ddddddd"), LocalDateTime.now());
        _warning("dddddd",new RuntimeException("ddddddd"), LocalDateTime.now());
    }
}
