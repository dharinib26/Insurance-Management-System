package com.cts.insurance.notification.service;

import com.cts.insurance.notification.model.Notification;

public interface NotificationService {
 
	String notify(Long policyId) throws Exception;
    String sendCustomNotification(Notification notification) throws Exception;
}
