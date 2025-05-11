package com.cts.insurance.notification.service;

import com.cts.insurance.notification.dto.NotificationResponse;

public interface NotificationService {
 
    NotificationResponse sendNotification(Long policyId, String customMessage);
 
    NotificationResponse notifyPolicyHolder(Long policyId);
}
