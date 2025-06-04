package com.cts.insurance.notification.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDTO {
	private Long id;
	private String name;
    private long customerId;

}
