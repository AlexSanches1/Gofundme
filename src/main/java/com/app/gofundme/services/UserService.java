package com.app.gofundme.services;

import com.app.gofundme.models.User;
import com.app.gofundme.models.dto.UserDTO;
import com.app.gofundme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ConvertService convertService;

    public UserDTO getUserId(Long id){
        User user = userRepository.getOne(id);
        return convertService.userConvertToDTO(user);
    }

}
