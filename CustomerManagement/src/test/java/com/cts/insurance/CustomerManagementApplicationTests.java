package com.cts.insurance;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.insurance.customer.model.Customer;
import com.cts.insurance.customer.repository.CustomerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerManagementApplicationTest {

	@Autowired
	private CustomerRepository customerRepository;

	private static Customer testCustomer;

	@BeforeAll
	static void setupBeforeAll() {
		testCustomer = new Customer();
		testCustomer.setName("Test User");
		testCustomer.setEmail("testuser@example.com");
		testCustomer.setPhoneNumber("1234567890");
		testCustomer.setAddress("Test Address");
	}

	@BeforeEach
	void beforeEachTest() {
		customerRepository.save(testCustomer);
	}

	@AfterEach
	void afterEachTest() {
		customerRepository.deleteAll();
	}

	@Test
	@Order(1)
	void testCustomerCreation() {
		List<Customer> customers = customerRepository.findAll();
		assertFalse(customers.isEmpty(), "Customer list should not be empty after save");
	}

	@Test
	@Order(2)
	void testUpdateCustomer() {
		Customer existing = customerRepository.findAll().get(0);
		existing.setAddress("Updated Address");
		Customer updated = customerRepository.save(existing);
		assertEquals("Updated Address", updated.getAddress());
	}

	@Test
	@Order(3)
	void testDeleteCustomer() {
		Customer existing = customerRepository.findAll().get(0);
		customerRepository.delete(existing);
		assertTrue(customerRepository.findAll().isEmpty());
	}
}
