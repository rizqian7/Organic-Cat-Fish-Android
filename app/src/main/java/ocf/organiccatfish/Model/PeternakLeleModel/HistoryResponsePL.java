package ocf.organiccatfish.Model.PeternakLeleModel;

import java.util.List;

public class HistoryResponsePL {

    private boolean error;
    private List<HistoryPL> list;

    public HistoryResponsePL(boolean error, List<HistoryPL> list) {
        this.error = error;
        this.list = list;
    }

    public boolean isError() {
        return error;
    }

    public List<HistoryPL> getList() {
        return list;
    }
}
