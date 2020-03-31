package br.com.prezaty.admin.controller;

import br.com.prezaty.admin.payload.JwtAuthenticationResponse;
import br.com.prezaty.admin.payload.LoginRequest;
import br.com.prezaty.admin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public JwtAuthenticationResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.signin(loginRequest);
    }

}