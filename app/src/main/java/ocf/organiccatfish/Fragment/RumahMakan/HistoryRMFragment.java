package ocf.organiccatfish.Fragment.RumahMakan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ocf.organiccatfish.Adapter.PeternakLele.HistoryAdapterPL;
import ocf.organiccatfish.Adapter.RumahMakan.HistoryAdapterRM;
import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.PeternakLeleModel.HistoryPL;
import ocf.organiccatfish.Model.PeternakLeleModel.HistoryResponsePL;
import ocf.organiccatfish.Model.RumahMakanModel.HistoryRM;
import ocf.organiccatfish.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryRMFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistoryAdapterRM adapterRM;
    private List<HistoryRM> historyList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rm_history, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_historypl);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        UserPL userPL = SharedPrefManager.getInstance(getActivity()).getUser();
//        Integer id_peternak = userPL.getId_peternaklele();

        Call<HistoryResponsePL> call = RetrofitClient.getInstance().getApi().getHistory();
        call.enqueue(new Callback<HistoryResponsePL>() {
            @Override
            public void onResponse(Call<HistoryResponsePL> call, Response<HistoryResponsePL> response) {
//                HistoryResponsePL historyResponsePL = response.body();
//                List<HistoryAdapterPL> list= historyResponsePL.getList();
//                historyList = response.body().getList();
//                adapterRM = new HistoryAdapterPL(getActivity(), historyList);
//                recyclerView.setAdapter(adapterRM);
            }

            @Override
            public void onFailure(Call<HistoryResponsePL> call, Throwable t) {

            }
        });
    }
}
