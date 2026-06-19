package model.response;

public class LoginDataJava {

    private String accessToken;
    private String refreshToken;
    private Integer expiresIn;
    private String tokenType;
    private UserDataJava user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public UserDataJava getUser() {
        return user;
    }

    public void setUser(UserDataJava user) {
        this.user = user;
    }
}