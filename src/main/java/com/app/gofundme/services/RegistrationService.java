package com.app.gofundme.services;

import com.app.gofundme.exceptions.BadRequestException;
import com.app.gofundme.models.User;
import com.app.gofundme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationService {

    private UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
