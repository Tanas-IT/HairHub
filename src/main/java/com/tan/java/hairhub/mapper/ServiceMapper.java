package com.tan.java.hairhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.tan.java.hairhub.dto.request.CreateServiceDTO;
import com.tan.java.hairhub.dto.request.UpdateServiceDTO;
import com.tan.java.hairhub.dto.response.ServiceResponse;
import com.tan.java.hairhub.entities.Service;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ServiceMapper {

    ServiceResponse toServiceResponse(Service service);

    Service toService(CreateServiceDTO createServiceDTO);

    Service updateService(UpdateServiceDTO updateServiceDTO, @MappingTarget Service service);
}
