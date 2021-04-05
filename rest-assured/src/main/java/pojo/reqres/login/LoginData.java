package pojo.reqres.login;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Pojo class representing the request body for the Login endpoint.
 */
public class LoginData {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("email", email).append("password", password).toString();
    }

}
