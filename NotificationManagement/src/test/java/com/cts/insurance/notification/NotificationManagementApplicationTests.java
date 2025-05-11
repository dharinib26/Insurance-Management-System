package com.cts.insurance.notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.insurance.notification.dto.NotificationResponse;
import com.cts.insurance.notification.dto.PolicyDTO;
import com.cts.insurance.notification.feign.PolicyClient;
import com.cts.insurance.notification.model.Notification;
import com.cts.insurance.notification.repository.NotificationRepository;
import com.cts.insurance.notification.service.NotificationServiceImpl;
 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NotificationServiceImplTest {
 
    @Mock
    private NotificationRepository notificationRepository;
 
    @Mock
    private PolicyClient policyClient;
 
    @InjectMocks
    private NotificationServiceImpl notificationService;
 
    private AutoCloseable closeable;
 
    @BeforeAll
    void setupAll() {
        System.out.println("Starting NotificationService tests...");
    }
 
    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }
 
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
 
    @AfterAll
    void done() {
        System.out.println("Finished NotificationService tests.");
    }
 
    @Test
    void testSendNotification_Success() {
        // Arrange
        Long policyId = 1L;
        String message = "Test notification";
 
        PolicyDTO policyDTO = new PolicyDTO();
        policyDTO.setPolicyId(policyId);
        policyDTO.setEmail("test@example.com");
 
        Notification notification = Notification.builder()
                .message(message)
                .recipient("test@example.com")
                //.sentAt(LocalDateTime.now())
                .build();
 
        when(policyClient.getPolicyById(policyId)).thenReturn(policyDTO);
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);
 
        // Act
        NotificationResponse response = notificationService.sendNotification(policyId, message);
 
        // Assert
        assertNotNull(response);
        assertEquals("test@example.com", response.getRecipient());
        assertEquals(message, response.getMessage());
 
        verify(policyClient, times(1)).getPolicyById(policyId);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
 
    @Test
    void testNotifyPolicyHolder_DefaultMessage() {
        // Arrange
        Long policyId = 2L;
        String defaultMessage = "Your policy update notification.";
 
        PolicyDTO policyDTO = new PolicyDTO();
        policyDTO.setPolicyId(policyId);
        policyDTO.setEmail("holder@example.com");
 
        Notification notification = Notification.builder()
                .message(defaultMessage)
                .recipient("holder@example.com")
                //.sentAt(LocalDateTime.now())
                .build();
 
        when(policyClient.getPolicyById(policyId)).thenReturn(policyDTO);
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);
 
        // Act
        NotificationResponse response = notificationService.notifyPolicyHolder(policyId);
 
        // Assert
        assertNotNull(response);
        assertEquals("holder@example.com", response.getRecipient());
        assertEquals(defaultMessage, response.getMessage());
 
        verify(policyClient, times(1)).getPolicyById(policyId);
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
 
    @Test
    void testSendNotification_PolicyNotFound() {
        // Arrange
        Long invalidId = 999L;
        when(policyClient.getPolicyById(invalidId)).thenThrow(new RuntimeException("Policy not found"));
 
        // Act & Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            notificationService.sendNotification(invalidId, "Message");
        });
 
        assertEquals("Policy not found", ex.getMessage());
        verify(policyClient, times(1)).getPolicyById(invalidId);
        verify(notificationRepository, never()).save(any(Notification.class));
    }
}