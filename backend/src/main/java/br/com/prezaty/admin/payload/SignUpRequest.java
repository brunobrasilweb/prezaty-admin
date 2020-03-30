package br.com.prezaty.admin.payload;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter @Setter
public class SignUpRequest {
    private String name;
    @Email
    private String email;
    private String password;
}