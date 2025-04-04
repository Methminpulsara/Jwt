package edu.icet.ecom.model;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;
import edu.icet.ecom.util.Role;

@Setter
@Generated
@Transactional
@NoArgsConstructor
@AllArgsConstructor

public class User {

    private Long UserId;
    private String username;
    private String password;
    private Role role;

}
