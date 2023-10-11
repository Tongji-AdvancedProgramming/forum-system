package org.tongji.programming.service.impl;

import org.springframework.stereotype.Component;
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

    @Override
    public void logLogin(String stuNo, String ipAddr, String userAgent, String comment) {

    }
}
