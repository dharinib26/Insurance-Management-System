package com.cts.insurance.claim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.insurance.claim.exception.ClaimNotFoundException;
import com.cts.insurance.claim.model.Claim;
import com.cts.insurance.claim.repository.ClaimRepository;
import com.cts.insurance.claim.service.ClaimServiceImpl;
 
class ClaimServiceImplTest {
 
    @Mock
    private ClaimRepository claimRepository;
 
    @InjectMocks
    private ClaimServiceImpl claimService;
 
    private AutoCloseable closeable;
 
    private Claim mockClaim;
 
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
 
        mockClaim = new Claim();
        mockClaim.setId(1L);
        mockClaim.setPolicyId(101L);
        mockClaim.setClaimAmount(10000.0);
        mockClaim.setClaimReason("Accident");
        mockClaim.setClaimDate(LocalDate.of(2025, 5, 1));
        mockClaim.setClaimStatus("PENDING");
    }
 
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
 
    @Test
    void testGetClaimById_Success() {
        when(claimRepository.findById(1L)).thenReturn(Optional.of(mockClaim));
 
        Claim result = claimService.getClaimById(1L);
 
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(claimRepository, times(1)).findById(1L);
    }
 
    @Test
    void testGetClaimById_NotFound() {
        when(claimRepository.findById(2L)).thenReturn(Optional.empty());
 
        Exception exception = assertThrows(ClaimNotFoundException.class, () -> {
            claimService.getClaimById(2L);
        });
 
        assertTrue(exception.getMessage().contains("Claim not found with ID: 2"));
    }
 
    @Test
    void testGetAllClaims() {
        List<Claim> claims = List.of(mockClaim);
        when(claimRepository.findAll()).thenReturn(claims);
 
        List<Claim> result = claimService.getAllClaims();
 
        assertEquals(1, result.size());
        verify(claimRepository, times(1)).findAll();
    }
 
    @Test
    void testUpdateClaim_Success() {
        Claim updated = new Claim();
        updated.setClaimAmount(12000.0);
        updated.setClaimReason("Updated Reason");
        updated.setClaimDate(LocalDate.of(2025, 5, 10));
        updated.setClaimStatus("UNDER REVIEW");
 
        when(claimRepository.findById(1L)).thenReturn(Optional.of(mockClaim));
        when(claimRepository.save(any(Claim.class))).thenReturn(updated);
 
        Claim result = claimService.updateClaim(1L, updated);
 
        assertEquals("UNDER REVIEW", result.getClaimStatus());
        assertEquals("Updated Reason", result.getClaimReason());
        verify(claimRepository, times(1)).save(any(Claim.class));
    }
 
    @Test
    void testUpdateClaim_NotFound() {
        Claim updated = new Claim();
        updated.setClaimStatus("REJECTED");
 
        when(claimRepository.findById(10L)).thenReturn(Optional.empty());
 
        Exception exception = assertThrows(ClaimNotFoundException.class, () -> {
            claimService.updateClaim(10L, updated);
        });
 
        assertTrue(exception.getMessage().contains("Claim not found with ID: 10"));
    }
}