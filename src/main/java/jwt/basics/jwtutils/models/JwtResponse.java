package jwt.basics.jwtutils.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1123213L;

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    private String accessToken;
    private String refreshToken;
    private String expiresIn;
    private String refreshExpiresIn;

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
    public String getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
    public String getRefreshExpiresIn() {
        return refreshExpiresIn;
    }
    public void setRefreshExpiresIn(String refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

}
