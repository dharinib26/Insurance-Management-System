package com.cts.insurance.notification.dto;

import lombok.*;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private String message;
    private String recipient;
}
