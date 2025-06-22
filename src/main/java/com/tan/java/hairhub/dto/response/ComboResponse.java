package com.tan.java.hairhub.dto.response;

import com.tan.java.hairhub.entities.*;
import com.tan.java.hairhub.entities.Process;
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
public class ComboResponse {

    private int comboId;

    private String comboName;

    private String description;

    private List<ImageResource> imageResources;

    private Combo parentCombo;

    private List<Combo> childCombos;

    private Process process;

    private List<OrderDetail> orderDetails;

    private Service service;

}
