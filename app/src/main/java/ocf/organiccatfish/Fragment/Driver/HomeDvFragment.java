package ocf.organiccatfish.Fragment.Driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import ocf.organiccatfish.R;

public class HomeDvFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dv_home, container, false);

        view.findViewById(R.id.iv_panen).setOnClickListener(this);
        view.findViewById(R.id.iv_pesan).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void panen(){
        PanenFragment panenFragment = new PanenFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.relativeLayoutDv, panenFragment, "panenDv")
                .addToBackStack(null)
                .commit();
    }

    public void pesan(){
        PesanFragment pesanFragment = new PesanFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.relativeLayoutDv, pesanFragment, "pesanDv")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_panen:
                panen();
                break;
            case R.id.iv_pesan:
                pesan();
                break;
        }
    }
}
