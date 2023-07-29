package com.back.end.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String ssn;
    private String address;
    private String key;
    private String email;
    private String cellphone;
    private LocalDateTime registerDate;

}
