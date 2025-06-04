package com.cts.insurance.agent.service;

import com.cts.insurance.agent.exception.AgentNotFoundException;
import com.cts.insurance.agent.model.Agent;
import com.cts.insurance.agent.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgentServiceImpl implements AgentService {

	private final AgentRepository repo;// Repository for Agent entity

	@Override
	public Agent createAgent(Agent agent) {
		log.info("Creating agent: {}", agent);
		return repo.save(agent);// Saves the new agent to the database
	}

	@Override
	public List<Agent> getAllAgents() {
		log.info("Fetching all agents");
		return repo.findAll();// Retrieves all agents from the database
	}

	@Override
	public Agent getAgentById(Long id) {
		log.info("Fetching agent by ID: {}", id);
		return repo.findById(id).orElseThrow(() -> new AgentNotFoundException("Agent not found with ID: " + id));// Retrieves
																													// an
																													// agent
																													// by
																													// ID
																													// or
																													// throws
																													// an
																													// exception
																													// if
																													// not
																													// found
	}

	// Updates the agent details
	@Override
	public Agent updateAgent(Long id, Agent updated) {
		log.info("Updating agent with ID: {}", id);
		Agent existing = getAgentById(id);
		existing.setName(updated.getName());
		existing.setContactInfo(updated.getContactInfo());
		existing.setAssignedPolicies(updated.getAssignedPolicies());
		return repo.save(existing);
	}

	// Delete agent by id
	@Override
	public void deleteAgent(Long id) {
		log.info("Deleting agent with ID: {}", id);
		repo.deleteById(id);
	}
}