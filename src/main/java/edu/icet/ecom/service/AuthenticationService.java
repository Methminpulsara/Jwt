package edu.icet.ecom.service;

import edu.icet.ecom.entity.UserEntity;
import edu.icet.ecom.model.AuthenticationResponese;
import edu.icet.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {


    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService service;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponese register(UserEntity request){
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(user.getRole());
        user = repository.save(user);

        String token = service.generateToken(user);

        return new AuthenticationResponese(token);

    }

    public AuthenticationResponese authenticate(UserEntity request){
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            UserEntity userEntity = repository.findByUsername(request.getUsername()).orElseThrow();
            String token = service.generateToken(userEntity);

            return new AuthenticationResponese(token);

    }



}

