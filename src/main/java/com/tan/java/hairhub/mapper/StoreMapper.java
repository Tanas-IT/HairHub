package com.tan.java.hairhub.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.tan.java.hairhub.dto.request.CreateStoreDTO;
import com.tan.java.hairhub.dto.request.UpdateStoreDTO;
import com.tan.java.hairhub.dto.response.StoreResponse;
import com.tan.java.hairhub.entities.Store;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StoreMapper {
    Store toStore(CreateStoreDTO createStoreDTO);

    StoreResponse toStoreResponse(Store store);

    Store updateStore(UpdateStoreDTO updateStoreDTO, @MappingTarget Store store);
}
