package ocf.organiccatfish.Model.RumahMakanModel;

public class LoginResponseRM {

    private boolean error;
    private String message;
    private UserRM user;

    public LoginResponseRM(boolean error, String message, UserRM user) {
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

    public UserRM getUserRM() {
        return user;
    }
}
