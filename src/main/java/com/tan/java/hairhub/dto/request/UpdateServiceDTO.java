package com.tan.java.hairhub.dto.request;

import com.tan.java.hairhub.entities.Combo;
import com.tan.java.hairhub.entities.ImageResource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateServiceDTO {
    private int serviceId;

    private String title;

    private String description;

    private String timeOfSerivce;
}
