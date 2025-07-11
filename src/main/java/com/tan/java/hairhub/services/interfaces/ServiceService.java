package com.tan.java.hairhub.services.interfaces;

import java.util.List;

import com.tan.java.hairhub.dto.request.CreateServiceDTO;
import com.tan.java.hairhub.dto.request.UpdateServiceDTO;
import com.tan.java.hairhub.dto.response.ServiceResponse;
import com.tan.java.hairhub.entities.Service;

public interface ServiceService {
    List<ServiceResponse> getAllService(int pageIndex, int pageSize);

    ServiceResponse getServiceById(int serviceId);

    ServiceResponse createService(CreateServiceDTO createServiceDTO);

    Service updateService(UpdateServiceDTO updateServiceDTO) throws Exception;

    void deleteService(int serviceId) throws Exception;
}
