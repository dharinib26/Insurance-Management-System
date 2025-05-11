package com.cts.insurance.notification.controller;

import com.cts.insurance.notification.dto.NotificationResponse;
import com.cts.insurance.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/notifications")
@Slf4j
public class NotificationController {
 
    @Autowired
    private NotificationService notificationService;
 
    // Send a custom notification
    @PostMapping("/send/{policyId}")
    public NotificationResponse send(@PathVariable Long policyId, @RequestParam String message) {
        log.info("Sending custom notification for policy ID: {}", policyId);
        return notificationService.sendNotification(policyId, message);
    }
 
    // Notify policy holder with default message
    @PostMapping("/notify/{policyId}")
    public NotificationResponse notify(@PathVariable Long policyId) {
        log.info("Sending default notification for policy ID: {}", policyId);
        return notificationService.notifyPolicyHolder(policyId);
    }
}