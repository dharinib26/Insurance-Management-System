package com.cts.insurance.notification.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cts.insurance.notification.dto.PolicyDTO;
import com.cts.insurance.notification.feign.PolicyClient;
import com.cts.insurance.notification.model.Notification;
import com.cts.insurance.notification.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository repository;
	private final PolicyClient policyClient;
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	// Notify about policy validity
	public String notify(Long policyId) throws Exception {
		PolicyDTO policy = policyClient.getById(policyId);
		if (policy == null)
			throw new Exception("Policy not found");

		Notification notification = new Notification();
		notification.setPolicyId(policy.getId());
		notification.setCustomerId(policy.getCustomerId());
		notification.setMessage("Your policy validity notificatoin");
		notification.setDate(LocalDate.now().toString());

		repository.save(notification);
		logger.info("Notification sent for policy {}", policyId);
		return "Notification sent";
	}

	// Send a custom notification
	public String sendCustomNotification(Notification notification) throws Exception {
		if (notification.getMessage() == null || notification.getCustomerId() == null) {
			throw new Exception("Invalid notification request");
		}

		notification.setDate(LocalDate.now().toString());
		repository.save(notification);
		logger.info("Custom notification sent to customer {}", notification.getCustomerId());
		return "Custom notification sent";
	}
}