package ocf.organiccatfish.Adapter.PeternakLele;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ocf.organiccatfish.Model.PeternakLeleModel.HistoryPL;
import ocf.organiccatfish.R;

public class HistoryAdapterPL extends RecyclerView.Adapter<HistoryAdapterPL.HistoryViewHolder> {

    private Context mContext;
    private List<HistoryPL> historylist = new ArrayList<>();

    public HistoryAdapterPL(Context mContext, List<HistoryPL> historylist) {
        this.mContext = mContext;
        this.historylist = historylist;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.design_pl_history, viewGroup, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int i) {
        HistoryPL historyPL = historylist.get(i);

        holder.kode_penjemputan.setText(historyPL.getKodePenjemputan());
        holder.tanggal_panen.setText(historyPL.getTanggalPanenPL());
        holder.status.setText(historyPL.getStatusPL());
    }

    @Override
    public int getItemCount() {
        return historylist.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView kode_penjemputan, tanggal_panen, status;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            kode_penjemputan = itemView.findViewById(R.id.kode_penjemputan);
            tanggal_panen = itemView.findViewById(R.id.tanggal_panen);
            status = itemView.findViewById(R.id.status);
        }
    }
}
