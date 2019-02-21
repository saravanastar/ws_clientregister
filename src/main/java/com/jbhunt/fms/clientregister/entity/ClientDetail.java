package com.jbhunt.fms.clientregister.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document("ClientDetail")
@Data
public class ClientDetail {
    @Id
    private String id;
    @NotNull
    private String password;
    @NotNull
    private String emailAddress;
    @NotNull
    private String phoneNumber;
    List<SellerInformation> sellerInformationList;
}
