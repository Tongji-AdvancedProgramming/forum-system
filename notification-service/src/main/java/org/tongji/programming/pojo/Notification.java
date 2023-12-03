package org.tongji.programming.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tongji.programming.enums.NotificationType;

import java.time.LocalDateTime;

/**
 * @author cinea
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Notification")
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private String title;
    private String content;
    private String receiver;
    private LocalDateTime dateTime;
    private boolean read;
}
