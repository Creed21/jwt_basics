package jwt.basics.jwtutils.models;

import java.io.Serializable;

public class JwtResponseModel implements Serializable {
    private static final long serialVersionUID = 1123213L;

    private final String token;

    public JwtResponseModel(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
