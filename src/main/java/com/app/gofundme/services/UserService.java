package com.app.gofundme.services;

import com.app.gofundme.models.User;
import com.app.gofundme.controllers.dto.UserInfoDTO;
import com.app.gofundme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private ConvertService convertService;

    @Autowired
    public UserService(UserRepository userRepository, ConvertService convertService) {
        this.userRepository = userRepository;
        this.convertService = convertService;
    }

    public UserInfoDTO getFullUserInfo(Long id) {
        User user = userRepository.getOne(id);
        return convertService.userConvertToDTO(user);
    }

}
