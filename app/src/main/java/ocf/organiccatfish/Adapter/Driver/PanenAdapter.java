package ocf.organiccatfish.Adapter.Driver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ocf.organiccatfish.Model.DriverModel.PanenDv;
import ocf.organiccatfish.Model.DriverModel.UserDv;
import ocf.organiccatfish.R;

public class PanenAdapter extends RecyclerView.Adapter<PanenAdapter.PanenViewHolder> {

    private Context mCtx;
    private List<PanenDv> panenList;

    public PanenAdapter(Context mCtx, List<PanenDv> panenList) {
        this.mCtx = mCtx;
        this.panenList = panenList;
    }

    @NonNull
    @NotNull
    @Override
    public PanenViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.rv_dv_panen, parent, false);
        return new PanenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PanenViewHolder holder, int position) {
        PanenDv panenDv = panenList.get(position);

        holder.tvNama.setText(panenDv.getNama());
        holder.tvTanggal.setText(panenDv.getWaktu_panen());
        holder.tvNohp.setText(panenDv.getNo_hp());
    }

    @Override
    public int getItemCount() {
        return panenList.size();
    }

    class PanenViewHolder extends RecyclerView.ViewHolder{

        TextView tvNama, tvTanggal, tvNohp;

        public PanenViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvNama      = itemView.findViewById(R.id.tv_nama);
            tvTanggal   = itemView.findViewById(R.id.tv_tanggal);
            tvNohp      = itemView.findViewById(R.id.tv_nohp);
        }
    }
}
