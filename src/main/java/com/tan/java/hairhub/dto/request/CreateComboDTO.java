package com.tan.java.hairhub.dto.request;

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
public class CreateComboDTO {

    private String comboName;

    private String description;

    private int parentComboId;

    private int processId;

    private int serviceId;

}
