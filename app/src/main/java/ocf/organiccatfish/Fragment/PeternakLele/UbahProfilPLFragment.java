package ocf.organiccatfish.Fragment.PeternakLele;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.PeternakLeleModel.LoginResponsePL;
import ocf.organiccatfish.Model.PeternakLeleModel.UserPL;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfilPLFragment extends Fragment implements View.OnClickListener{

    private EditText noKtp, namaLengkap, noHp, namaUsaha, username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pl_user_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noKtp       = view.findViewById(R.id.et_noktp);
        namaLengkap = view.findViewById(R.id.et_nama);
        noHp        = view.findViewById(R.id.et_nohp);
        namaUsaha   = view.findViewById(R.id.et_namaUsaha);
        username    = view.findViewById(R.id.et_username);

        noKtp.setText(SharedPrefManager.getInstance(getActivity()).getUser().getNo_ktp());
        namaLengkap.setText(SharedPrefManager.getInstance(getActivity()).getUser().getNama_lengkap());
        noHp.setText(SharedPrefManager.getInstance(getActivity()).getUser().getNo_hp());
        namaUsaha.setText(SharedPrefManager.getInstance(getActivity()).getUser().getNama_usaha());
        username.setText(SharedPrefManager.getInstance(getActivity()).getUser().getUsername());

        view.findViewById(R.id.btn_saveUser).setOnClickListener(this);
    }

    public void saveUserSetting(){
        String no_ktp           = noKtp.getText().toString().trim();
        String nama_lengkap     = namaLengkap.getText().toString().trim();
        String no_hp            = noHp.getText().toString().trim();
        String nama_usaha       = namaUsaha.getText().toString().trim();
        String username_pl      = username.getText().toString().trim();

        if (no_ktp.isEmpty()){
            noKtp.setError("Please Fill Out This Field");
            noKtp.requestFocus();
            return;
        }

        if (nama_lengkap.isEmpty()){
            namaLengkap.setError("Please Fill Out This Field");
            namaLengkap.requestFocus();
            return;
        }

        if (no_hp.isEmpty()){
            noHp.setError("Please Fill Out This Field");
            noHp.requestFocus();
            return;
        }

        if (nama_usaha.isEmpty()){
            namaUsaha.setError("Please Fill Out This Field");
            namaUsaha.requestFocus();
            return;
        }

        if (username_pl.isEmpty()){
            username.setError("Please Fill Out This Field");
            username.requestFocus();
            return;
        }

        UserPL userPL = SharedPrefManager.getInstance(getActivity()).getUser();
        Call<LoginResponsePL> call = RetrofitClient.getInstance()
                .getApi().updateUserSetting(userPL.getId_peternaklele(), no_ktp, nama_lengkap, no_hp, nama_usaha, username_pl);

        call.enqueue(new Callback<LoginResponsePL>() {
            @Override
            public void onResponse(Call<LoginResponsePL> call, Response<LoginResponsePL> response) {
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                if(!response.body().isError()) {
                    SharedPrefManager.getInstance(getActivity()).saveUserPL(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<LoginResponsePL> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_saveUser:
                saveUserSetting();
                break;
        }
    }
}
