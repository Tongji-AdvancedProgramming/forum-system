package org.tongji.programming.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tongji.programming.mapper.LoggerMapper;
import org.tongji.programming.service.LogService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 对日志过程，我们使用线程池来异步执行
 *
 * @author cineazhan
 */
@Slf4j
@Component
public class LogServiceImpl implements LogService {
    private final ExecutorService executor = Executors.newCachedThreadPool();

    LoggerMapper loggerMapper;

    @Autowired
    public void setLoggerMapper(LoggerMapper loggerMapper) {
        this.loggerMapper = loggerMapper;
    }

    @Override
    public void logLogin(String stuNo, String ipAddr, String userAgent, String comment) {
        // 如果StuNo为空就没必要执行了。这是因为数据库当前设计不支持错误的stuNo。
        if (stuNo.isEmpty()) return;
        
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    loggerMapper.logLogin(stuNo, ipAddr, userAgent, comment);
                } catch (Exception e) {
                    log.error("录入登录日志时失败：{}", e.getMessage());
                }
            }
        });
    }
}
