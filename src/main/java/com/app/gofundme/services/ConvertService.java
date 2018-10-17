package com.app.gofundme.services;

import com.app.gofundme.models.User;
import com.app.gofundme.models.dto.UserDTO;
import org.springframework.stereotype.Service;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;

@Service
public class ConvertService {

    public String convertToBase64(Long id) {
        String idString = id.toString();
        byte[] token = Base64.encodeBase64(idString.getBytes());
        return Arrays.toString(token);
    }

    public Long convertFromBase64(String token) {
        byte[] bytes = Base64.decodeBase64(token.getBytes());
        String idString = Arrays.toString(bytes);
        Long id = Long.valueOf(idString);
        return id;
    }

    public UserDTO userConvertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setId(user.getId());
        dto.setSecondName(user.getSecondName());
        return dto;
    }

}
