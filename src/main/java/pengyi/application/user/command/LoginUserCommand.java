package pengyi.application.user.command;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by YJH on 2016/3/14.
 */
public class LoginUserCommand {

    @NotEmpty(message = "{login.username.NotEmpty.message}")
    private String username;

    @NotEmpty(message = "{login.password.NotEmpty.message}")
    private String password;

    private String verificationCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
