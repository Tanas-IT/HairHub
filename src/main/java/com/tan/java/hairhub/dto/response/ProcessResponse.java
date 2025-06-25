package com.tan.java.hairhub.dto.response;

import com.tan.java.hairhub.entities.ImageResource;
import com.tan.java.hairhub.entities.ProcessStep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessResponse {
    private int processId;

    private String processName;

    private String description;

    private String comboName;


    private List<ProcessStep> processSteps;

    private List<ImageResource> imageResources;

}
