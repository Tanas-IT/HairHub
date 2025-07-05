package com.tan.java.hairhub.dto.response;

import java.util.List;

import com.tan.java.hairhub.entities.ImageResource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private int storeId;

    private String storeName;

    private String ward;

    private String district;

    private String province;

    private String description;

    private String address;

    private String logoURL;

    private List<ImageResource> imageResources;

    private String storePhoneNumber;

    private String emailOfOwner;
}
