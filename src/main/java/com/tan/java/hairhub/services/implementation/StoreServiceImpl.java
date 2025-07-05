package com.tan.java.hairhub.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tan.java.hairhub.dto.request.CreateStoreDTO;
import com.tan.java.hairhub.dto.request.UpdateStoreDTO;
import com.tan.java.hairhub.dto.response.StoreResponse;
import com.tan.java.hairhub.entities.Store;
import com.tan.java.hairhub.entities.User;
import com.tan.java.hairhub.mapper.StoreMapper;
import com.tan.java.hairhub.repositories.StoreRepository;
import com.tan.java.hairhub.repositories.UserRepository;
import com.tan.java.hairhub.services.interfaces.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

    private StoreRepository storeRepository;
    private UserRepository userRepository;
    private StoreMapper storeMapper;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, StoreMapper storeMapper, UserRepository userRepository) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<StoreResponse> getAllStore(int pageIndex, int pageSize) {
        List<StoreResponse> listStoreResponse = new ArrayList<>();
        this.storeRepository.getAllStore((pageIndex - 1) * pageSize, pageSize).forEach(store -> {
            StoreResponse storeResponse = storeMapper.toStoreResponse(store);
            storeResponse.setEmailOfOwner(store.getUser().getEmail());
            listStoreResponse.add(storeResponse);
        });
        return listStoreResponse;
    }

    @Override
    public StoreResponse getStoreById(int storeId) {
        Optional<Store> checkStore = this.storeRepository.findById(storeId);
        if (checkStore.isPresent()) {
            StoreResponse storeResponse = this.storeMapper.toStoreResponse(checkStore.get());
            storeResponse.setEmailOfOwner(checkStore.get().getUser().getEmail());
            return storeResponse;
        }
        return null;
    }

    @Transactional
    @Override
    public StoreResponse createStore(CreateStoreDTO createStoreDTO) {
        Store store = this.storeMapper.toStore(createStoreDTO);
        Optional<User> findUser = this.userRepository.findById(createStoreDTO.getUserId());
        if (findUser.isPresent()) {
            store.setUser(findUser.get());
        }
        Store newStore = this.storeRepository.save(store);

        StoreResponse storeResponse = this.storeMapper.toStoreResponse(newStore);
        storeResponse.setEmailOfOwner(newStore.getUser().getEmail());
        return storeResponse;
    }

    @Override
    public Store updateStore(UpdateStoreDTO updateStoreDTO) throws Exception {
        Optional<Store> checkStoreExist = this.storeRepository.findById(updateStoreDTO.getStoreId());
        if (checkStoreExist.isPresent()) {
            Store updateStore = checkStoreExist.get();
            Optional<User> checkUser = this.userRepository.findById(updateStoreDTO.getUserId());
            if (!checkUser.isPresent()) {
                throw new Exception("User does not exist");
            }
            User user = new User()
                    .builder()
                    .userId(checkUser.get().getUserId())
                    .email(checkUser.get().getEmail())
                    .phoneNumber(checkUser.get().getPhoneNumber())
                    .profile(null)
                    .build();
            updateStore.setUser(user);
            this.storeMapper.updateStore(updateStoreDTO, checkStoreExist.get());
            this.storeRepository.save(updateStore);
            return updateStore;
        }
        return null;
    }

    @Override
    public void deleteStore(int storeId) throws Exception {
        Optional<Store> checkStoreExist = this.storeRepository.findById(storeId);
        if (!checkStoreExist.isPresent()) {
            throw new Exception("Delete store failed");
        }
        this.storeRepository.delete(checkStoreExist.get());
    }
}
