package com.cts.insurance.notification.repository;

import com.cts.insurance.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
