package com.tan.java.hairhub.dto.response;

import com.tan.java.hairhub.entities.Combo;
import com.tan.java.hairhub.entities.ImageResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private int serviceId;

    private String title;

    private String description;

    private String timeOfSerivce;

    private List<ImageResource> imageResources;

    private List<Combo> combos;

}
