package com.cts.insurance.agent;
 
import com.cts.insurance.agent.model.Agent;
import com.cts.insurance.agent.repository.AgentRepository;
import com.cts.insurance.agent.service.AgentServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.util.*;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
 class AgentManagementApplicationTests {
 
    @InjectMocks
    private AgentServiceImpl service;
 
    @Mock
    private AgentRepository repo;
 
    private AutoCloseable closeable;
 
    private Agent sampleAgent;
 
    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        sampleAgent = new Agent(1L, "Alice", "alice@domain.com", "P123,P456");
    }
 
    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
 
    @Test
    void testCreateAgent() {
        when(repo.save(sampleAgent)).thenReturn(sampleAgent);
        Agent result = service.createAgent(sampleAgent);
        assertEquals("Alice", result.getName());
    }
 
    @Test
    void testGetAllAgents() {
        when(repo.findAll()).thenReturn(List.of(sampleAgent));
        List<Agent> agents = service.getAllAgents();
        assertEquals(1, agents.size());
    }
 
    @Test
    void testGetAgentById() {
        when(repo.findById(1L)).thenReturn(Optional.of(sampleAgent));
        Agent agent = service.getAgentById(1L);
        assertEquals("Alice", agent.getName());
    }
 
    @Test
    void testUpdateAgent() {
        when(repo.findById(1L)).thenReturn(Optional.of(sampleAgent));
        when(repo.save(any())).thenReturn(sampleAgent);
        Agent updated = new Agent(1L, "Bob", "bob@domain.com", "P789");
        Agent result = service.updateAgent(1L, updated);
        assertEquals("Bob", result.getName());
    }
 
    @Test
    void testDeleteAgent() {
        doNothing().when(repo).deleteById(1L);
        service.deleteAgent(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}