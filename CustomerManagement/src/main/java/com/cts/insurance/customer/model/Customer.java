package com.cts.insurance.customer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Entity class representing a customer
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
	// Unique identifier for the customer
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Customer name must not be blank")
	private String name;

	@NotBlank(message = "Email must not be blank")
	private String email;

	@NotBlank(message = "Phone number must not be blank")
	private String phoneNumber;

	@NotBlank(message = "Address must not be blank")
	private String address;
	private Long assignedPolicyId;

}