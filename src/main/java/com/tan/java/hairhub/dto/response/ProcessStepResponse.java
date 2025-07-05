package com.tan.java.hairhub.dto.response;

import com.tan.java.hairhub.entities.Process;
import com.tan.java.hairhub.entities.Step;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessStepResponse {
    private int processStepId;

    private Process process;

    private Step step;

    private int orderNumber;
}
