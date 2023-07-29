package com.back.end.converter;

import com.back.end.dto.UserDTO;
import com.back.end.model.User;

public class DTOConverter {

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setSsn(user.getSsn());
        userDTO.setKey(user.getKey());
        userDTO.setEmail(user.getEmail());
        userDTO.setCellphone(user.getCellphone());
        userDTO.setRegisterDate(user.getDateRegister());
        return userDTO;
    }

}
