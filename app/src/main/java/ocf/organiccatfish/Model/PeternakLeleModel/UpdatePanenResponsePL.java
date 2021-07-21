package ocf.organiccatfish.Model.PeternakLeleModel;

import com.google.gson.annotations.SerializedName;

public class UpdatePanenResponsePL {

    @SerializedName("error")
    private boolean err;

    @SerializedName("message")
    private String msg;

    public UpdatePanenResponsePL(boolean err, String msg) {
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
