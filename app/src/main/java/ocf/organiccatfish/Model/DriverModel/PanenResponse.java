package ocf.organiccatfish.Model.DriverModel;

import java.util.List;

public class PanenResponse {

    private boolean error;
    private List<PanenDv> panenDv;

    public PanenResponse(boolean error, List<PanenDv> panenDv){
        this.error = error;
        this.panenDv = panenDv;
    }

    public boolean isError() {
        return error;
    }

    public List<PanenDv> getPanenDv() {
        return panenDv;
    }
}
