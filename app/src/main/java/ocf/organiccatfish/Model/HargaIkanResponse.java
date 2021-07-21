package ocf.organiccatfish.Model;

import java.util.List;

import ocf.organiccatfish.Model.PeternakLeleModel.HargaBeliPL;

public class HargaIkanResponse {

    private boolean error;
    private Integer hargaBeli;

    public HargaIkanResponse(boolean error, Integer hargaBeli) {
        this.error = error;
        this.hargaBeli = hargaBeli;
    }

    public boolean isError() {
        return error;
    }

    public Integer getHargaBeli() {
        return hargaBeli;
    }
}
