package com.jbhunt.fms.clientregister.controller;

import com.jbhunt.fms.clientregister.entity.ClientDetail;
import com.jbhunt.fms.clientregister.service.ClientDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private ClientDetailsService clientDetailsService;
    RegistrationController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @PostMapping
    public ResponseEntity<ClientDetail> registerUser(@Valid @RequestBody ClientDetail clientDetail) {
        ResponseEntity<ClientDetail>  responseEntity= null;
        try {
            responseEntity = ResponseEntity.ok(clientDetailsService.persistCustomer(clientDetail));
        } catch (Exception e) {
            responseEntity = ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
        return responseEntity;
    }
}
