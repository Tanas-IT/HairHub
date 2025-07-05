package com.tan.java.hairhub.dto.request;

import jakarta.persistence.*;

import com.tan.java.hairhub.entities.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateComboDTO {

    private String comboName;

    private String description;

    private int parentComboId;

    private int processId;

    private int serviceId;
    private double price;
    private String timeOfSerivce;
}
