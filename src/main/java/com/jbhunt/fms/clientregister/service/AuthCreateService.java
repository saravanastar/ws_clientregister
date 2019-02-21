package com.jbhunt.fms.clientregister.service;

import com.jbhunt.fms.clientregister.dto.CustomerAuthDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthCreateService {

    private RestTemplate restTemplate;

    @Value("${auth.creation.url}")
    private String url;

    AuthCreateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateAuth(CustomerAuthDTO  customerAuthDTO) {
        HttpEntity<CustomerAuthDTO> httpEntity = new HttpEntity(customerAuthDTO);
        ResponseEntity<CustomerAuthDTO> customerAuthDTO1 = restTemplate.exchange(url, HttpMethod.POST, httpEntity, CustomerAuthDTO.class);
        return customerAuthDTO1.getBody().getId();
    }
}
