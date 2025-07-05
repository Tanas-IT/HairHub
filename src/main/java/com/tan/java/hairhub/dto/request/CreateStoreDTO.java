package com.tan.java.hairhub.dto.request;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStoreDTO {

    private String storeName;

    private String ward;

    private String district;

    private String province;

    private String description;

    private String address;

    private String logoURL;

    private String storePhoneNumber;

    private int userId;
}
