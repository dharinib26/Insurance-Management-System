package com.cts.insurance.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.insurance.agent.model.Agent;

//Repository interface for Agent entity, providing CRUD operations
public interface AgentRepository extends JpaRepository<Agent, Long> {
}