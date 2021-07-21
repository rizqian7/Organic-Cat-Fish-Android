package ocf.organiccatfish.Model.RumahMakanModel;

import com.google.gson.annotations.SerializedName;

public class RegisterResponseRM {

    @SerializedName("error")
    private boolean err;

    @SerializedName("message")
    private String msg;

    public RegisterResponseRM(boolean err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    public boolean isErr() {
        return err;
    }

    public String getMsg() {
        return msg;
    }
}
