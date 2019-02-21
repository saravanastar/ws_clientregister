package com.jbhunt.fms.clientregister.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAuthDTO {
    private String clientId;
    private String clientSecret;
    private String token;
    private String id;
}
