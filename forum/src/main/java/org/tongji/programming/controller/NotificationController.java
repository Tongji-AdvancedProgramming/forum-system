package org.tongji.programming.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.tongji.programming.dto.ApiDataResponse;
import org.tongji.programming.dto.ApiResponse;
import org.tongji.programming.pojo.Homework;
import org.tongji.programming.pojo.Notification;
import org.tongji.programming.service.HomeworkService;
import org.tongji.programming.service.NotificationService;

import java.security.Principal;
import java.util.List;

/**
 * 通知控制器类
 *
 * @author cinea
 */
@Tag(
        name = "Notification",
        description = "通知相关控制器类"
)
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "获取用户的通知"
    )
    @GetMapping
    public ApiDataResponse<List<Notification>> getMyNotifications(
            Principal principal
    ) {
        var userId = principal.getName();
        return ApiDataResponse.success(notificationService.getNotifications(userId));
    }

    @Secured("ROLE_USER")
    @Operation(
            summary = "用户已读通知"
    )
    @PostMapping("/read")
    public ApiResponse getMyNotifications(
            Principal principal,
            @RequestParam String notificationId
    ) {
        var userId = principal.getName();
        notificationService.userReadNotification(notificationId, userId);
        return ApiResponse.success();
    }
}
