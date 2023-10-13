package com.blogs.api.payload;

import lombok.Data;

@Data
public class LoginDto {

    private String usernameOrEmail;
    private String password;


}
