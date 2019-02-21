package com.jbhunt.fms.clientregister.service;

import com.jbhunt.fms.clientregister.dto.CustomerAuthDTO;
import com.jbhunt.fms.clientregister.entity.ClientDetail;
import com.jbhunt.fms.clientregister.entity.SellerInformation;
import com.jbhunt.fms.clientregister.repository.ClientDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDetailsService {
    private final ClientDetailsRepository clientDetailsRepository;
    private final AuthCreateService authCreateService;

    ClientDetailsService(ClientDetailsRepository clientDetailsRepository, AuthCreateService  authCreateService) {
        this.clientDetailsRepository = clientDetailsRepository;
        this.authCreateService = authCreateService;
    }

    public ClientDetail persistCustomer(ClientDetail clientDetail) throws Exception {
        ClientDetail clientDetail1 = clientDetailsRepository.findByEmailAddress(clientDetail.getEmailAddress());
        if (clientDetail1 != null) {
                throw new Exception(" User Registered already");
        }
        return clientDetailsRepository.save(clientDetail);
    }

    public ClientDetail getInformationByEmail(String emailId) {
        return clientDetailsRepository.findByEmailAddress(emailId);
    }

    public List<SellerInformation> getSellerInformation(String id) {
        return clientDetailsRepository.findById(id)
                .map(clientDetail -> clientDetail.getSellerInformationList())
                .orElse(Collections.emptyList());
    }

    public ClientDetail loginService(ClientDetail clientDetail) {
        ClientDetail  clientDetail1 = clientDetailsRepository.findByEmailAddressAndPassword(clientDetail.getEmailAddress(), clientDetail.getPassword());

        return Optional.ofNullable(clientDetail1).orElse(null);
    }

    public ClientDetail addSeller(String id, List<SellerInformation> sellerInformations) {
        ClientDetail clientDetail = clientDetailsRepository.findById(id).orElse(null);
        if (clientDetail != null) {
            for (SellerInformation sellerInformation: sellerInformations
                 ) {
                CustomerAuthDTO  customerAuthDTO = CustomerAuthDTO.builder()
                        .clientId(sellerInformation.getSellerName())
                        .clientSecret(clientDetail.getId()).build();
                sellerInformation.setAuthId(authCreateService.generateAuth(customerAuthDTO));
                if (clientDetail.getSellerInformationList() == null) {
                    clientDetail.setSellerInformationList(new ArrayList<>());
                }
                clientDetail.getSellerInformationList().add(sellerInformation);
            }
            clientDetailsRepository.save(clientDetail);
        }

        return clientDetail;

    }

    public Optional<ClientDetail> getClientDetail(String id) {
        return clientDetailsRepository.findById(id);
    }
}
