package com.tan.java.hairhub.dto.response;


import com.tan.java.hairhub.entities.ProcessStep;
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
public class StepResponse {

    private int stepId;

    private String stepName;

    private String imageURL;

    private List<ProcessStep> processSteps;
}
