package com.cts.insurance.notification.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyDTO {
    private Long policyId;
    private String policyHolderName;
    private String policyType;
    private String email;
}
