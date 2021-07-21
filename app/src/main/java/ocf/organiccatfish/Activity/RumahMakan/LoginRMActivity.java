package ocf.organiccatfish.Activity.RumahMakan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.RumahMakanModel.LoginResponseRM;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRMActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUsername, etPassword;

    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_login);

        etUsername = findViewById(R.id.etUsernameRM);
        etPassword = findViewById(R.id.etPasswordRM);

        findViewById(R.id.btnLoginRM).setOnClickListener(this);
        findViewById(R.id.btnDaftarRM).setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedInRm()){
            Intent intent = new Intent(this, ProfileRMActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginRM:
                userLoginRM();
                break;
            case R.id.btnDaftarRM:
                startActivity(new Intent(this, RegisterRMActivity.class));
                break;
        }
    }

    private void userLoginRM() {
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

        Call<LoginResponseRM> call = RetrofitClient.getInstance().getApi().LoginRM(username, password);

        call.enqueue(new Callback<LoginResponseRM>() {
            @Override
            public void onResponse(Call<LoginResponseRM> call, Response<LoginResponseRM> response) {
                LoginResponseRM loginResponseRM = response.body();

                if (!loginResponseRM.isError()){
                    SharedPrefManager.getInstance(LoginRMActivity.this).saveUserRM(loginResponseRM.getUserRM());

                    Intent intent = new Intent(LoginRMActivity.this, ProfileRMActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginRMActivity.this, loginResponseRM.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseRM> call, Throwable t) {

            }
        });
    }
}
