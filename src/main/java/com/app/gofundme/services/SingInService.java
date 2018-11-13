package com.app.gofundme.services;

import com.app.gofundme.controllers.dto.TokenDTO;
import com.app.gofundme.controllers.request_dto.RequestLoginDTO;
import com.app.gofundme.controllers.request_dto.RequestRegistrDTO;
import com.app.gofundme.exceptions.BadRequestException;
import com.app.gofundme.models.User;
import com.app.gofundme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SingInService {

    private UserRepository userRepository;
    private ConvertService convertService;

    @Autowired
    public SingInService(UserRepository userRepository, ConvertService convertService) {
        this.userRepository = userRepository;
        this.convertService = convertService;
    }

    public void registrationOfUser(RequestRegistrDTO registerDTO) {
        if (registerDTO.getFirstPassword().equals(registerDTO.getSecondPassword())) {
            User user = new User();

            if (isValidEmailAddress(registerDTO.getEmail())) {
                user.setEmail(registerDTO.getEmail());
                user.setFirstName(registerDTO.getFirstName());
                user.setSecondName(registerDTO.getSecondName());
                user.setPassword(registerDTO.getFirstPassword());
            } else {
                throw new BadRequestException("Not validate email");
            }
            userRepository.save(user);
        }else {
            throw new BadRequestException("Passwords isn't similar");
        }
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\."
                + "[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public TokenDTO login(RequestLoginDTO loginDTO) {
        if (!(userRepository.findUserByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword()).isEmpty())) {
            List<User> users = userRepository.findUserByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
            User user = users.get(0);
            if (user.getEmail().equals(loginDTO.getEmail()) && user.getPassword().equals(loginDTO.getPassword())) {
                Long userId = user.getId();
                return convertService.convertToBase64ForToken(userId);
            } else {
                throw new BadRequestException("Email or password isn't validate");
            }
        }else {
            throw new BadRequestException("Email or password isn't validate");
        }
    }
}
