package edu.icet.ecom.controller;

import edu.icet.ecom.entity.UserEntity;
import edu.icet.ecom.model.AuthenticationResponese;
import edu.icet.ecom.model.User;
import edu.icet.ecom.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {


    private final AuthenticationService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponese> register(
            @RequestBody UserEntity request
            ){
        return ResponseEntity.ok(authService.register(request));

    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponese> login(
            @RequestBody UserEntity request
    ){
        return ResponseEntity.ok(authService.authenticate(request));

    }




}
