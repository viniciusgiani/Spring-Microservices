package com.back.end.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorDTO {
    private int status;
    private String message;
    private LocalDateTime timestamp;

}
