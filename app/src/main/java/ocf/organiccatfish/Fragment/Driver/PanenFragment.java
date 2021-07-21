package ocf.organiccatfish.Fragment.Driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ocf.organiccatfish.Adapter.Driver.PanenAdapter;
import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.DriverModel.PanenDv;
import ocf.organiccatfish.Model.DriverModel.PanenResponse;
import ocf.organiccatfish.Model.DriverModel.UserDv;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanenFragment extends Fragment {

    private RecyclerView recyclerView;
    private PanenAdapter panenAdapter;
    private List<PanenDv> panenDv;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dv_panen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_panen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        UserDv userDv = SharedPrefManager.getInstance(getActivity()).getUserDv();
        Call<PanenResponse> call = RetrofitClient.getInstance().getApi().getPanen(userDv.getId_driver());
        call.enqueue(new Callback<PanenResponse>() {
            @Override
            public void onResponse(Call<PanenResponse> call, Response<PanenResponse> response) {
                panenDv = response.body().getPanenDv();
                panenAdapter = new PanenAdapter(getActivity(), panenDv);
                recyclerView.setAdapter(panenAdapter);
            }

            @Override
            public void onFailure(Call<PanenResponse> call, Throwable t) {

            }
        });
    }
}
