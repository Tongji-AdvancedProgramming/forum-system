package org.tongji.programming.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tongji.programming.pojo.Notification;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cinea
 */
@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findAllByReceiverOrderByDateTimeDesc(String receiver);

    void deleteAllByDateTimeBefore(LocalDateTime before);

    void deleteAllByDateTimeBeforeAndRead(LocalDateTime before, boolean read);
}
