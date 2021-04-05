package pojo.reqres.login;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Pojo class representing the response body for the Login endpoint.
 */
public class LoginResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("token", token).toString();
    }

}
