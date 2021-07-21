package ocf.organiccatfish.Fragment.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import ocf.organiccatfish.LoginActivity;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;

public class UserDvFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dv_user, container, false);

        view.findViewById(R.id.btn_logoutDv).setOnClickListener(this);
        view.findViewById(R.id.pl_ubahPasswordDv).setOnClickListener(this);
        view.findViewById(R.id.pl_ubahProfilDv).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void ubahProfil() {
        UbahProfilDvFragment ubahProfilDvFragment = new UbahProfilDvFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.relativeLayoutDv, ubahProfilDvFragment, "ubahProfilDv")
                .addToBackStack(null)
                .commit();
    }

    public void ubahPassword() {
        UbahPasswordDvFragment ubahPasswordDvFragment = new UbahPasswordDvFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.relativeLayoutDv, ubahPasswordDvFragment, "ubahPasswordDv")
                .addToBackStack(null)
                .commit();
    }

    private void logout(){
        SharedPrefManager.getInstance(getActivity()).clear();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pl_ubahProfilDv:
                ubahProfil();
                break;
            case R.id.pl_ubahPasswordDv:
                ubahPassword();
                break;
            case R.id.btn_logoutDv:
                logout();
                break;
        }
    }
}
