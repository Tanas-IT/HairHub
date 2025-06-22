package com.tan.java.hairhub.services.interfaces;

import com.tan.java.hairhub.dto.request.CreateComboDTO;
import com.tan.java.hairhub.dto.request.CreateServiceDTO;
import com.tan.java.hairhub.dto.request.UpdateComboDTO;
import com.tan.java.hairhub.dto.request.UpdateServiceDTO;
import com.tan.java.hairhub.dto.response.ComboResponse;
import com.tan.java.hairhub.dto.response.ServiceResponse;
import com.tan.java.hairhub.entities.Combo;
import com.tan.java.hairhub.entities.Service;

import java.util.List;

public interface ComboService {

    List<ComboResponse> getAllCombo(int pageIndex, int pageSize);

    ComboResponse getComboById(int comboId);
    ComboResponse createCombo(CreateComboDTO createComboDTO);

    Combo updateCombo(UpdateComboDTO updateComboDTO) throws Exception;
    void deleteCombo(int comboId) throws Exception;
}
