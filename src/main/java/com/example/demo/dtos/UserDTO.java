package com.example.demo.dtos;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String userName;
    private String password;
}
