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
import ocf.organiccatfish.Model.DefaultResponse;
import ocf.organiccatfish.Model.PeternakLeleModel.UserPL;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahPasswordPLFragment extends Fragment implements View.OnClickListener{

    private EditText etCurrentPassword, etNewPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pl_user_password, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etCurrentPassword = view.findViewById(R.id.et_crpassword);
        etNewPassword     = view.findViewById(R.id.et_nwpassword);

        view.findViewById(R.id.btn_savePassword).setOnClickListener(this);
    }

    private void updatePassword() {
        String currentPassword  = etCurrentPassword.getText().toString().trim();
        String newPassword      = etNewPassword.getText().toString().trim();

        if(currentPassword.isEmpty()){
            etCurrentPassword.setError("Password required");
            etCurrentPassword.requestFocus();
            return;
        }

        if(newPassword.isEmpty()) {
            etNewPassword.setError("Password required");
            etNewPassword.requestFocus();
            return;
        }

        UserPL userPL = SharedPrefManager.getInstance(getActivity()).getUser();

        Call<DefaultResponse> call = RetrofitClient.getInstance().getApi()
                .updatePasswordSetting(currentPassword, newPassword, userPL.getUsername());
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_savePassword:
                updatePassword();
                break;
        }
    }


}
