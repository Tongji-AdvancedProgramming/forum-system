package org.tongji.programming.service;

/**
 * @author cinea
 */
public interface LogService {
    void logLogin(String stuNo, String ipAddr, String userAgent, String comment);

    void logPost(Integer postId, String stuNo, String ipAddr, String comment);
}
