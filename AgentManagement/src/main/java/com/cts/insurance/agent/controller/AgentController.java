package com.cts.insurance.agent.controller;

import com.cts.insurance.agent.model.Agent;
import com.cts.insurance.agent.service.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agents")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class AgentController {

	private final AgentService service;

	// Method to create a new agent
	@PostMapping("/create")
	public Agent create(@RequestBody Agent agent) {
		return service.createAgent(agent);
	}

	// Method to get all agents
	@GetMapping("/all")
	public List<Agent> getAll() {
		return service.getAllAgents();
	}

	// Method to get an agent by ID
	@GetMapping("/{id}")
	public Agent getById(@PathVariable Long id) {
		return service.getAgentById(id);
	}

	// Method to update an agent by ID
	@PutMapping("/update/{id}")
	public Agent update(@PathVariable("id") Long id, @RequestBody Agent agent) {
		return service.updateAgent(id, agent);
	}

	// Method to delete an agent by ID
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteAgent(id);
	}
}
