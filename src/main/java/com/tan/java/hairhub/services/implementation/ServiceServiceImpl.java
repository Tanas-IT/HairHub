package com.tan.java.hairhub.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tan.java.hairhub.dto.request.CreateServiceDTO;
import com.tan.java.hairhub.dto.request.UpdateServiceDTO;
import com.tan.java.hairhub.dto.response.ServiceResponse;
import com.tan.java.hairhub.entities.Combo;
import com.tan.java.hairhub.mapper.ServiceMapper;
import com.tan.java.hairhub.repositories.ComboRespository;
import com.tan.java.hairhub.repositories.ProcessRepository;
import com.tan.java.hairhub.repositories.ServiceRepository;
import com.tan.java.hairhub.services.interfaces.ServiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;
    private ServiceMapper serviceMapper;
    private ComboRespository comboRespository;
    private ProcessRepository processRepository;

    @Autowired
    public ServiceServiceImpl(
            ServiceRepository serviceRepository,
            ServiceMapper serviceMapper,
            ComboRespository comboRespository,
            ProcessRepository processRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceMapper = serviceMapper;
        this.comboRespository = comboRespository;
        this.processRepository = processRepository;
    }

    @Override
    public List<ServiceResponse> getAllService(int pageIndex, int pageSize) {
        List<com.tan.java.hairhub.entities.Service> listService =
                this.serviceRepository.getAllService((pageIndex - 1) * pageSize, pageSize);
        List<ServiceResponse> result = new ArrayList<>();
        listService.forEach(service -> {
            ServiceResponse serviceResponse = this.serviceMapper.toServiceResponse(service);
            List<Combo> listCombo = this.comboRespository.getAllComboByServiceId(serviceResponse.getServiceId());
            listCombo.forEach(x -> x.setProcess(null));
            listCombo.forEach(x -> x.setService(null));
            result.add(serviceResponse);
        });
        return result;
    }

    @Override
    public ServiceResponse getServiceById(int serviceId) {
        Optional<com.tan.java.hairhub.entities.Service> service = this.serviceRepository.findById(serviceId);
        if (service.isPresent()) {
            log.info(service.toString());
            ServiceResponse serviceResponse = this.serviceMapper.toServiceResponse(service.get());
            List<Combo> listCombo = this.comboRespository.getAllComboByServiceId(serviceId);
            listCombo.forEach(x -> {
                x.setProcess(this.processRepository
                        .findById(x.getProcess().getProcessId())
                        .get());
            });
            listCombo.forEach(x -> x.setService(null));
            return serviceResponse;
        }
        return null;
    }

    @Override
    public ServiceResponse createService(CreateServiceDTO createServiceDTO) {
        com.tan.java.hairhub.entities.Service service = this.serviceMapper.toService(createServiceDTO);
        if (service != null) {
            this.serviceRepository.save(service);
            ServiceResponse serviceResponse = this.serviceMapper.toServiceResponse(service);
            return serviceResponse;
        }
        return null;
    }

    @Override
    public com.tan.java.hairhub.entities.Service updateService(UpdateServiceDTO updateServiceDTO) throws Exception {
        Optional<com.tan.java.hairhub.entities.Service> checkServiceExist =
                this.serviceRepository.findById(updateServiceDTO.getServiceId());
        if (checkServiceExist.isPresent()) {
            com.tan.java.hairhub.entities.Service updateService =
                    this.serviceMapper.updateService(updateServiceDTO, checkServiceExist.get());
            this.serviceRepository.save(updateService);
            return updateService;
        }
        throw new Exception("Service does not exist");
    }

    @Override
    public void deleteService(int serviceId) throws Exception {
        Optional<com.tan.java.hairhub.entities.Service> checkServiceExist = this.serviceRepository.findById(serviceId);
        if (!checkServiceExist.isPresent()) {
            throw new Exception("Service does not exist");
        }
        this.serviceRepository.delete(checkServiceExist.get());
    }
}
