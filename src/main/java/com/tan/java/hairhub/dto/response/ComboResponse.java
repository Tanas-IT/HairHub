package com.tan.java.hairhub.dto.response;

import java.util.List;

import jakarta.persistence.*;

import com.tan.java.hairhub.dto.request.ImageResourceDTO;
import com.tan.java.hairhub.entities.*;
import com.tan.java.hairhub.entities.Process;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComboResponse {

    private int comboId;

    private String comboName;

    private String description;

    private List<ImageResourceDTO> imageResources;

    private Combo parentCombo;

    private List<Combo> childCombos;

    private Process process;

    private List<OrderDetail> orderDetails;

    private Service service;
    private double price;
    private String timeOfSerivce;
}
