package model.response;

import lombok.Data;

@Data
public class LoginDataLombok {
    private String accessToken;
    private String refreshToken;
    private Integer expiresIn;
    private String tokenType;
    private LoginUserLombok user;
}
