package org.tongji.programming.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.tongji.programming.pojo.Notification;
import org.tongji.programming.repository.NotificationRepository;
import org.tongji.programming.service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cinea
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    NotificationRepository repository;

    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    void removeOldNotifications() {
        repository.deleteAllByDateTimeBefore(LocalDateTime.now().minusWeeks(1));
        repository.deleteAllByDateTimeBeforeAndRead(LocalDateTime.now().minusDays(1), true);
    }

    @Override
    public void sendNotification(Notification notification) {
        notification.setDateTime(LocalDateTime.now());
        notification.setRead(false);
        repository.save(notification);
    }

    @Override
    public List<Notification> getNotifications(String userId) {
        return repository.findAllByReceiverOrderByDateTimeDesc(userId);
    }

    @Override
    public void userReadNotification(String notificationId, String userId) {
        var e = repository.findById(notificationId);
        if (e.isPresent()) {
            var entity = e.get();
            if (entity.getReceiver().equals(userId)) {
                entity.setRead(true);
                repository.save(entity);
            }
        }
    }

    @Override
    public void userReadAllNotification(String userId) {
        var notifications = repository.findAllByReceiverAndRead(userId, false);
        notifications.forEach(notification -> {
            notification.setRead(true);
            repository.save(notification);
        });
    }

    @Override
    public void userDeleteAllNotification(String userId) {
        repository.deleteAllByReceiver(userId);
    }
}
