package com.tan.java.hairhub.mapper;

import com.tan.java.hairhub.dto.request.CreateComboDTO;
import com.tan.java.hairhub.dto.request.UpdateComboDTO;
import com.tan.java.hairhub.dto.response.ComboResponse;
import com.tan.java.hairhub.entities.Combo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ComboMapper {

    ComboResponse toComboResponse(Combo combo);

    Combo toCombo(CreateComboDTO createComboDTO);

    Combo updateCombo(UpdateComboDTO updateComboDTO, @MappingTarget Combo combo);
}
