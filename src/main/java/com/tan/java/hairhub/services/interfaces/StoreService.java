package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateStoreDTO;
import com.tan.java.hairhub.dto.request.UpdateStoreDTO;
import com.tan.java.hairhub.dto.response.StoreResponse;
import com.tan.java.hairhub.entities.Store;

public interface StoreService {

    List<StoreResponse> getAllStore(int pageIndex, int pageSize);

    StoreResponse getStoreById(int storeId);

    StoreResponse createStore(CreateStoreDTO createStoreDTO);

    Store updateStore(UpdateStoreDTO updateStoreDTO) throws Exception;

    void deleteStore(int storeId) throws Exception;
}
