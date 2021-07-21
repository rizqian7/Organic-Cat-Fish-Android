package ocf.organiccatfish.Activity.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ocf.organiccatfish.Activity.PeternakLele.LoginPLActivity;
import ocf.organiccatfish.Activity.PeternakLele.ProfilePLActivity;
import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.DriverModel.LoginResponseDv;
import ocf.organiccatfish.Model.PeternakLeleModel.LoginResponsePL;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDvActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dv_login);

        etUsername = findViewById(R.id.etUsernameDV);
        etPassword = findViewById(R.id.etPasswordDV);

        findViewById(R.id.btnLoginDV).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedInDv()){
            Intent intent = new Intent(this, ProfileDvActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginDV:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()){
            etUsername.setError("Please Fill Out This Field");
            etUsername.requestFocus();
            return;
        }

        if (password.isEmpty()){
            etPassword.setError("Please Fill Out This Field");
            etPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            etPassword.setError("Password At Least 6 Character");
            etPassword.requestFocus();
            return;
        }

        Call<LoginResponseDv> call = RetrofitClient.getInstance().getApi().LoginDv(username, password);
        call.enqueue(new Callback<LoginResponseDv>() {
            @Override
            public void onResponse(Call<LoginResponseDv> call, Response<LoginResponseDv> response) {
                LoginResponseDv loginResponseDv = response.body();

                if (!loginResponseDv.isError()){
                    SharedPrefManager.getInstance(LoginDvActivity.this).saveUserDv(loginResponseDv.getUserDv());

                    Intent intent = new Intent(LoginDvActivity.this, ProfileDvActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginDvActivity.this, loginResponseDv.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseDv> call, Throwable t) {

            }
        });
    }
}
