package org.tongji.programming.service;

import org.tongji.programming.pojo.Notification;

import java.util.List;

/**
 * @author cinea
 */
public interface NotificationService {
    /**
     * 发送通知
     */
    void sendNotification(Notification notification);

    /**
     * 用户获取通知
     */
    List<Notification> getNotifications(String userId);

    /**
     * 用户已读通知
     */
    void userReadNotification(String notificationId, String userId);

    /**
     * 用户已读所有通知
     */
    void userReadAllNotification(String userId);

    /**
     * 用户删除所有通知
     */
    void userDeleteAllNotification(String userId);
}
