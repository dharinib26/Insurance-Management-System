package com.cts.insurance.agent.service;

import com.cts.insurance.agent.model.Agent;

import java.util.List;

//Service interface for managing Agent entities

public interface AgentService {
	Agent createAgent(Agent agent);

	List<Agent> getAllAgents();

	Agent getAgentById(Long id);

	Agent updateAgent(Long id, Agent agent);

	void deleteAgent(Long id);
}