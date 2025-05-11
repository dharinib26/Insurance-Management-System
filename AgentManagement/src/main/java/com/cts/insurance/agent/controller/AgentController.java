package com.cts.insurance.agent.controller;
 
import com.cts.insurance.agent.model.Agent;
import com.cts.insurance.agent.service.AgentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/api/agents")
@RequiredArgsConstructor
@Slf4j
public class AgentController {
 
    private final AgentService service;
 
    @PostMapping("/create")
    public Agent create(@RequestBody Agent agent) {
        return service.createAgent(agent);
    }
 
    @GetMapping("/all")
    public List<Agent> getAll() {
        return service.getAllAgents();
    }
 
    @GetMapping("/{id}")
    public Agent getById(@PathVariable Long id) {
        return service.getAgentById(id);
    }
 
    @PutMapping("/update/{id}")
    public Agent update(@PathVariable Long id, @RequestBody Agent agent) {
        return service.updateAgent(id, agent);
    }
 
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAgent(id);
    }
}
 