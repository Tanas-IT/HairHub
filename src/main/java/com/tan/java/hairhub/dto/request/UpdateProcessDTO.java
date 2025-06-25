package com.tan.java.hairhub.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProcessDTO {
    private int processId;
    private String processName;

    private String description;

    private int comboId;
}
