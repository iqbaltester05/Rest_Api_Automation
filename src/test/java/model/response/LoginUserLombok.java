package model.response;

import lombok.Data;

@Data
public class LoginUserLombok {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String department;
}
