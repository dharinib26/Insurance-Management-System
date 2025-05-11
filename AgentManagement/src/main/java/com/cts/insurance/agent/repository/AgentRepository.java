package com.cts.insurance.agent.repository;
 
import com.cts.insurance.agent.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface AgentRepository extends JpaRepository<Agent, Long> {
}