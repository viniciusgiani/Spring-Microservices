package com.back.end.model;

import com.back.end.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ssn;

    private String address;

    private String key;

    private String email;

    private String cellphone;

    private LocalDateTime dateRegister;


    public static User convert(UserDTO userDTO) {
            User user = new User();
            user.setName(userDTO.getName());
            user.setAddress(userDTO.getAddress());
            user.setSsn(userDTO.getSsn());
            user.setKey(userDTO.getKey());
            user.setEmail(userDTO.getEmail());
            user.setCellphone(userDTO.getCellphone());
            user.setDateRegister(userDTO.getRegisterDate());
            return user;
    }
}
