package com.app.gofundme.services;

import com.app.gofundme.controllers.dto.TokenDTO;
import com.app.gofundme.models.User;
import com.app.gofundme.controllers.dto.UserInfoDTO;
import org.springframework.stereotype.Service;

import org.apache.commons.codec.binary.Base64;

@Service
public class ConvertService {

    public TokenDTO convertToBase64(Long id) {
        TokenDTO dto = new TokenDTO();
        String idString = id.toString();
        byte[] token = Base64.encodeBase64(idString.getBytes());
        dto.setToken(new String(token));
        return dto;
    }

    public Long convertFromBase64(String token) {
        byte[] bytes = Base64.decodeBase64(token.getBytes());
        String idString = new String(bytes);
        Long id = Long.valueOf(idString);
        return id;
    }

    public UserInfoDTO userConvertToDTO(User user) {
        UserInfoDTO dto = new UserInfoDTO();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setId(user.getId());
        dto.setSecondName(user.getSecondName());
        return dto;
    }

}
