package org.tongji.programming.service.impl;

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
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loggerMapper.logLogin(stuNo, ipAddr, userAgent, comment);
            }
        });
    }
}
