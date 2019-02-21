package com.jbhunt.fms.clientregister.repository;

import com.jbhunt.fms.clientregister.entity.ClientDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends MongoRepository<ClientDetail, String> {

    public ClientDetail findByEmailAddressAndPassword(String userName, String password);

    public ClientDetail findByEmailAddress(String emailAddress);


}
