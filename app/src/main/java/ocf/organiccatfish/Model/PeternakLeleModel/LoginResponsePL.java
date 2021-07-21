package ocf.organiccatfish.Model.PeternakLeleModel;

public class LoginResponsePL {

    private boolean error;
    private String message;
    private UserPL user;

    public LoginResponsePL(boolean error, String message, UserPL user) {
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

    public UserPL getUser() {
        return user;
    }
}