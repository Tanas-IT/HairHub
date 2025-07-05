package com.tan.java.hairhub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStoreDTO {

    private int storeId;

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
