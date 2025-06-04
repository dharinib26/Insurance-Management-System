package com.cts.insurance.notification.controller;


import com.cts.insurance.notification.model.Notification;
import com.cts.insurance.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("/notify/{policyId}")
    public String notifyCustomer(@PathVariable Long policyId) throws Exception {
        return service.notify(policyId);
    }

    @PostMapping("/sendNotification")
    public String sendCustom(@RequestBody Notification notification) throws Exception {
        return service.sendCustomNotification(notification);
    }
}