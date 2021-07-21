package ocf.organiccatfish.Adapter.RumahMakan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ocf.organiccatfish.Adapter.PeternakLele.HistoryAdapterPL;
import ocf.organiccatfish.Model.PeternakLeleModel.HistoryPL;
import ocf.organiccatfish.Model.RumahMakanModel.HistoryRM;
import ocf.organiccatfish.R;

public class HistoryAdapterRM extends RecyclerView.Adapter<HistoryAdapterRM.HistoryViewHolder>{

    private Context mContext;
    private List<HistoryRM> historylist = new ArrayList<>();

    public HistoryAdapterRM(Context mContext, List<HistoryRM> historylist) {
        this.mContext = mContext;
        this.historylist = historylist;
    }

    @NonNull
    @Override
    public HistoryAdapterRM.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.design_rm_history, viewGroup, false);
        return new HistoryAdapterRM.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapterRM.HistoryViewHolder holder, int i) {
        HistoryRM historyRM = historylist.get(i);

        holder.kode_pemesanan.setText(historyRM.getIdPemesanan());
        holder.tanggal_pesan.setText(historyRM.getWaktuPesan());
        holder.status.setText(historyRM.getStatus());
    }

    @Override
    public int getItemCount() {
        return historylist.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView kode_pemesanan, tanggal_pesan, status;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            kode_pemesanan = itemView.findViewById(R.id.kode_pemesanan);
            tanggal_pesan = itemView.findViewById(R.id.tanggal_pesan);
            status = itemView.findViewById(R.id.status);
        }
    }
}
