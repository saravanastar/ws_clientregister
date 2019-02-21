package com.jbhunt.fms.clientregister.controller;

import com.jbhunt.fms.clientregister.entity.ClientDetail;
import com.jbhunt.fms.clientregister.entity.SellerInformation;
import com.jbhunt.fms.clientregister.service.ClientDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientDetailController {

    private ClientDetailsService clientDetailsService;

    ClientDetailController(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<ClientDetail> loginUser(@RequestBody ClientDetail clientDetail, HttpServletRequest httpServletRequest) {
        ClientDetail clientDetail1 = clientDetailsService.loginService(clientDetail);
        if (clientDetail == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsernamePasswordAuthenticationToken authentication = new
                UsernamePasswordAuthenticationToken(clientDetail1, null, null);

        authentication.setDetails(new
                WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(
                authentication);
        return ResponseEntity.ok(clientDetail1);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDetail> getInformation(@PathVariable("id") String id) {
        ClientDetail clientDetail = clientDetailsService.getClientDetail(id).orElse(null);

        if (clientDetail == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(clientDetail);
    }

    @PostMapping("/{id}/seller")
    public ResponseEntity<ClientDetail> storeSellerDetail(@PathVariable("id") String id, @RequestBody List<SellerInformation> sellerInformations) {
        return ResponseEntity.ok(clientDetailsService.addSeller(id, sellerInformations));
    }



}
