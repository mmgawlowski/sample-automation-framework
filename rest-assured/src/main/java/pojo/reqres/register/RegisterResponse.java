package pojo.reqres.register;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Pojo class representing the response body for the Register endpoint.
 */
public class RegisterResponse {
    private Integer id;
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("token", token).toString();
    }

}
