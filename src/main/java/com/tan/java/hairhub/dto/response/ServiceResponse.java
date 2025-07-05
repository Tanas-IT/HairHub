package com.tan.java.hairhub.dto.response;

import java.util.List;

import com.tan.java.hairhub.dto.request.ImageResourceDTO;
import com.tan.java.hairhub.entities.Combo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse {

    private int serviceId;

    private String title;

    private String description;

    private List<ImageResourceDTO> imageResources;

    private List<Combo> combos;
    private String typeOfService;
}
