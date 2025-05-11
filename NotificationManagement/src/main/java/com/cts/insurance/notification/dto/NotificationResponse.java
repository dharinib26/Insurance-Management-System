package com.cts.insurance.notification.dto;

import lombok.*;
import java.time.LocalDateTime;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private String message;
    private String recipient;
    //private LocalDateTime sentAt;
}
