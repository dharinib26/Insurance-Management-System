package com.cts.insurance.policy;
 
import com.cts.insurance.policy.dto.AgentDTO;
import com.cts.insurance.policy.dto.CustomerDTO;
import com.cts.insurance.policy.dto.PolicyDTO;
import com.cts.insurance.policy.exception.PolicyNotFoundException;
import com.cts.insurance.policy.feignclient.AgentClient;
import com.cts.insurance.policy.feignclient.CustomerClient;
import com.cts.insurance.policy.model.Policy;
import com.cts.insurance.policy.repository.PolicyRepository;
import com.cts.insurance.policy.service.PolicyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class PolicyServiceTest {
 
    @Mock
    private PolicyRepository repository;
 
    @Mock
    private CustomerClient customerClient;
 
    @Mock
    private AgentClient agentClient;
 
    @InjectMocks
    private PolicyService service;
 
    private Policy policy;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        policy = new Policy(1L, "Life Cover", "Life", "Covers life risk", 5000.0, 100000,1L,2L);
    }
 
    @Test
    void testCreatePolicy() {
        when(repository.save(policy)).thenReturn(policy);
        Policy result = service.createPolicy(policy);
        assertEquals(policy, result);
    }
 
    @Test
    void testGetAllPolicies() {
        List<Policy> policies = Arrays.asList(policy);
        when(repository.findAll()).thenReturn(policies);
        List<Policy> result = service.getAllPolicies();
        assertEquals(1, result.size());
        assertEquals("Life Cover", result.get(0).getName());
    }
 
    @Test
    void testGetPolicyById_Exists() {
        when(repository.findById(1L)).thenReturn(Optional.of(policy));
        Policy result = service.getPolicyById(1L);
        assertEquals(policy.getId(), result.getId());
    }
 
    @Test
    void testGetPolicyById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PolicyNotFoundException.class, () -> service.getPolicyById(1L));
    }
 
    @Test
    void testUpdatePolicy() {
        Policy updated = new Policy(1L, "Updated Name", "Health", "Updated desc", 6000.0, 120000,1L,2L);
        when(repository.findById(1L)).thenReturn(Optional.of(policy));
        when(repository.save(any(Policy.class))).thenReturn(updated);
        Policy result = service.updatePolicy(1L, updated);
        assertEquals("Updated Name", result.getName());
        assertEquals("Health", result.getPolicyType());
    }
 
    @Test
    void testDeletePolicy() {
        doNothing().when(repository).deleteById(1L);
        service.deletePolicy(1L);
        verify(repository, times(1)).deleteById(1L);
    }
 
    @Test
    void testGetPolicyWithCustomer() {
        CustomerDTO customer = new CustomerDTO();
        customer.setId(10L);
        customer.setName("John Doe");
        customer.setContactInfo("john@example.com");
 
        when(repository.findById(1L)).thenReturn(Optional.of(policy));
        when(customerClient.getCustomerById(10L)).thenReturn(customer);
 
        PolicyDTO dto = service.getPolicyWithCustomer(1L, 10L);
 
        assertEquals("Life Cover", dto.getPolicyName());
        assertNotNull(dto.getCustomer());
        assertEquals("John Doe", dto.getCustomer().getName());
    }
 
    @Test
    void testGetPolicyWithAgent() {
        AgentDTO agent = new AgentDTO();
        agent.setId(20L);
        agent.setName("Agent Smith");
        agent.setContactInfo("smith@example.com");
 
        when(repository.findById(1L)).thenReturn(Optional.of(policy));
        when(agentClient.getAgentById(20L)).thenReturn(agent);
 
        PolicyDTO dto = service.getPolicyWithAgent(1L, 20L);
 
        assertEquals("Life Cover", dto.getPolicyName());
        assertNotNull(dto.getAgent());
        assertEquals("Agent Smith", dto.getAgent().getName());
    }
}