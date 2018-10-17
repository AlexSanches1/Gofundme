package com.app.gofundme.services;

import org.springframework.stereotype.Service;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;

@Service
public class ConvertService {

    public String convertToBase64(Long id){
        String idString = id.toString();
        byte[] token = Base64.encodeBase64(idString.getBytes());
        return Arrays.toString(token);
    }

    public Long convertFromBase64(String token){
        byte[] bytes = Base64.decodeBase64(token.getBytes());
        String idString = Arrays.toString(bytes);
        Long id = Long.valueOf(idString);
        return id;
    }

}
