package jwt.basics.jwtutils.models;

import java.io.Serializable;

public class JwtResponseModel implements Serializable {
    private static final long serialVersionUID = 1123213L;

    private final String accessToken;

    public JwtResponseModel(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getToken() {
        return accessToken;
    }
}
