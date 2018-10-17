package com.app.gofundme.services;

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

    public void registrationOfUser(String email, String firstName,
                                   String secondName, String firstPass, String secondPass) {
        if (firstPass.equals(secondPass)) {
            User user = new User();

            if (isValidEmailAddress(email)) {
                user.setEmail(email);
                user.setFirstName(firstName);
                user.setSecondName(secondName);
                user.setPassword(firstPass);
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

    public String login(String email, String password) {
        String token = "";
        if (!(userRepository.findUserByEmailAndPassword(email, password).isEmpty())) {
            List<User> users = userRepository.findUserByEmailAndPassword(email, password);
            User user = users.get(0);
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                Long userId = user.getId();
                token = convertService.convertToBase64(userId);
            } else {
                throw new BadRequestException("Email or password isn't validate");
            }
        }else {
            throw new BadRequestException("Email or password isn't validate");
        }
        return token;
    }
}
