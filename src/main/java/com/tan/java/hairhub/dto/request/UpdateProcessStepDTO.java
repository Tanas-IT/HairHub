package com.tan.java.hairhub.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProcessStepDTO {
    private int processStepId;

    private int orderNumber;

}
