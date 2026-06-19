package model.response;

import lombok.Data;

@Data
public class LoginResponseLombok {
    private LoginDataLombok data;
    private String message;
    private String timestamp;
}
