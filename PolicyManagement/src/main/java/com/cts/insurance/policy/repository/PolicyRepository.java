package com.cts.insurance.policy.repository;
 
import com.cts.insurance.policy.model.Policy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
public interface PolicyRepository extends JpaRepository<Policy, Long> {

	List<Policy> findByCustomerId(Long customerId);

}