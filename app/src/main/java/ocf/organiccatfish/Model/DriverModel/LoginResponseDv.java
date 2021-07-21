package ocf.organiccatfish.Model.DriverModel;

public class LoginResponseDv {

    private boolean error;
    private String message;
    private UserDv user;

    public LoginResponseDv(boolean error, String message, UserDv user) {
        this.error = error;
        this.message = message;
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public UserDv getUserDv() {
        return user;
    }
}
