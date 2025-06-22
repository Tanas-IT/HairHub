package com.tan.java.hairhub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateComboDTO {

    private int comboId;

    private String comboName;

    private String description;

    private int parentComboId;

    private int processId;

    private int serviceId;

}
