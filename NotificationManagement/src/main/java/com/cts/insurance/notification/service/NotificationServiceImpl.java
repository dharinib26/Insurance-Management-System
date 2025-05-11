package com.cts.insurance.notification.service;

import com.cts.insurance.notification.dto.NotificationResponse;
import com.cts.insurance.notification.dto.PolicyDTO;
import com.cts.insurance.notification.model.Notification;
import com.cts.insurance.notification.feign.PolicyClient;
import com.cts.insurance.notification.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.time.LocalDateTime;
 
@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {
 
    @Autowired
    private NotificationRepository notificationRepository;
 
    @Autowired
    private PolicyClient policyClient;
 
    // Send a custom notification to a policy holder
    @Override
    public NotificationResponse sendNotification(Long policyId, String customMessage) {
        PolicyDTO policy = policyClient.getPolicyById(policyId);
 
        Notification notification = Notification.builder()
                .message(customMessage)
                .recipient(policy.getEmail())
                //.sentAt(LocalDateTime.now())
                .build();
 
        notificationRepository.save(notification);
 
        log.info("Notification sent to policy holder with ID: {}", policyId);
 
        return NotificationResponse.builder()
                .message(notification.getMessage())
                .recipient(notification.getRecipient())
                //.sentAt(notification.getSentAt())
                .build();
    }
 
    // Notify with default message
    @Override
    public NotificationResponse notifyPolicyHolder(Long policyId) {
        return sendNotification(policyId, "Your policy update notification.");
    }
}