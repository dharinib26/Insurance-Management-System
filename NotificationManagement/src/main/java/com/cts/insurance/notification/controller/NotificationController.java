package com.cts.insurance.notification.controller;

import com.cts.insurance.notification.dto.NotificationResponse;
import com.cts.insurance.notification.model.NotificationRequest;
import com.cts.insurance.notification.service.NotificationService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@Slf4j
@AllArgsConstructor
public class NotificationController {

	private NotificationService notificationService;

	// Send a custom notification
	@PostMapping("/send/{policyId}")
	public NotificationResponse send(@RequestBody NotificationRequest request) {
		log.info("Sending custom notification for policy ID: {}", request.getPolicyId());
		return notificationService.sendNotification(request.getPolicyId(), request.getMessage());
	}

	// Notify policy holder with default message
	@PostMapping("/notify/{policyId}")
	public NotificationResponse notify(@PathVariable Long policyId) {
		log.info("Sending default notification for policy ID: {}", policyId);
		return notificationService.notifyPolicyHolder(policyId);
	}
}