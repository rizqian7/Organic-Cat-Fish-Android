package ocf.organiccatfish.Fragment.PeternakLele;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import ocf.organiccatfish.LoginActivity;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;

public class UserPLFragment extends Fragment implements View.OnClickListener{

    private static final String TAG ="UserPLFragment";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pl_user, container, false);

        view.findViewById(R.id.btn_logout).setOnClickListener(this);
        view.findViewById(R.id.pl_ubahPassword).setOnClickListener(this);
        view.findViewById(R.id.pl_ubahProfil).setOnClickListener(this);
        view.findViewById(R.id.pl_ubahAlamat).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void ubahProfil() {
        UbahProfilPLFragment ubahProfilPLFragment = new UbahProfilPLFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.relativeLayout, ubahProfilPLFragment, "ubahProfilPL")
                .addToBackStack(null)
                .commit();
    }

    public void ubahPassword() {
        UbahPasswordPLFragment ubahPasswordPLFragment = new UbahPasswordPLFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.relativeLayout, ubahPasswordPLFragment, "ubahPasswordPL")
                .addToBackStack(null)
                .commit();
    }

    public void ubahAlamat() {
        if(isServiceOK()){
            UbahAlamatPLFragment ubahAlamatPLFragment = new UbahAlamatPLFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.relativeLayout, ubahAlamatPLFragment, "ubahAlamatPL")
                    .addToBackStack(null)
                    .commit();
        }
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
            case R.id.pl_ubahProfil:
                ubahProfil();
                break;
            case R.id.pl_ubahPassword:
                ubahPassword();
                break;
            case R.id.pl_ubahAlamat:
                ubahAlamat();
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK : checking google service version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());

        if(available == ConnectionResult.SUCCESS) {
            //everything is fine and user can make map connection
            Log.d(TAG, "isServiceOK : Google Play Service is working");
            return true;
        } else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can fix it
            Log.d(TAG, "isServiceOK : an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(getActivity(), "You can't make map request", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}
