package jwt.basics.jwtutils.models;

import java.io.Serializable;

public class JwtRefresRequest implements Serializable {
    private static final long serialVersionUID = 2636936156391265892L;

    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
