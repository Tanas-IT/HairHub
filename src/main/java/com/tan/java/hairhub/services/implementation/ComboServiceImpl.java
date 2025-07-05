package com.tan.java.hairhub.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tan.java.hairhub.dto.request.CreateComboDTO;
import com.tan.java.hairhub.dto.request.UpdateComboDTO;
import com.tan.java.hairhub.dto.response.ComboResponse;
import com.tan.java.hairhub.entities.Combo;
import com.tan.java.hairhub.mapper.ComboMapper;
import com.tan.java.hairhub.repositories.ComboRespository;
import com.tan.java.hairhub.repositories.ServiceRepository;
import com.tan.java.hairhub.services.interfaces.ComboService;

@Service
public class ComboServiceImpl implements ComboService {

    private ComboRespository comboRespository;
    private ServiceRepository serviceRepository;
    private ComboMapper comboMapper;

    @Autowired
    public ComboServiceImpl(
            ComboRespository comboRespository, ComboMapper comboMapper, ServiceRepository serviceRepository) {
        this.comboRespository = comboRespository;
        this.comboMapper = comboMapper;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ComboResponse> getAllCombo(int pageIndex, int pageSize) {
        List<Combo> listCombo = this.comboRespository.getAllCombo((pageIndex - 1) * pageSize, pageSize);
        List<ComboResponse> listComboResposne = new ArrayList<>();
        if (!listCombo.isEmpty()) {
            listCombo.forEach(combo -> {
                ComboResponse comboResponse = this.comboMapper.toComboResponse(combo);
                listComboResposne.add(comboResponse);
            });
        }
        return listComboResposne;
    }

    @Override
    public ComboResponse getComboById(int comboId) {
        Optional<Combo> combo = this.comboRespository.findById(comboId);
        if (combo.isPresent()) {
            ComboResponse comboResponse = this.comboMapper.toComboResponse(combo.get());
            return comboResponse;
        }
        return null;
    }

    @Override
    public ComboResponse createCombo(CreateComboDTO createComboDTO) {
        Combo createCombo = this.comboMapper.toCombo(createComboDTO);

        var combo = this.comboRespository.save(createCombo);
        ComboResponse comboResponse = this.comboMapper.toComboResponse(combo);
        Optional<Combo> findParentCombo = this.comboRespository.findById(createComboDTO.getParentComboId());
        Optional<com.tan.java.hairhub.entities.Service> findService =
                this.serviceRepository.findById(createComboDTO.getServiceId());
        if (findParentCombo.isPresent()) {
            comboResponse.setParentCombo(findParentCombo.get());
        }
        if (findService.isPresent()) {
            comboResponse.setService(findService.get());
        }
        return comboResponse;
    }

    @Override
    public Combo updateCombo(UpdateComboDTO updateComboDTO) throws Exception {
        Optional<Combo> checkExistCombo = this.comboRespository.findById(updateComboDTO.getComboId());
        if (!checkExistCombo.isPresent()) {
            throw new Exception("Combo does not exist");
        }
        Combo updateCombo = this.comboMapper.updateCombo(updateComboDTO, checkExistCombo.get());
        return updateCombo;
    }

    @Override
    public void deleteCombo(int comboId) throws Exception {
        Optional<Combo> checkExistCombo = this.comboRespository.findById(comboId);
        if (!checkExistCombo.isPresent()) {
            throw new Exception("Combo does not exist");
        }
        this.comboRespository.delete(checkExistCombo.get());
    }
}
