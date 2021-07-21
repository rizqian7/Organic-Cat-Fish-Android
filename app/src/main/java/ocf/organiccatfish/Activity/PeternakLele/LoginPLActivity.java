package ocf.organiccatfish.Activity.PeternakLele;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.PeternakLeleModel.LoginResponsePL;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPLActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.btnDaftar).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, ProfilePLActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.btnDaftar:
                startActivity(new Intent(this, RegisterPLActivity.class));
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

        Call<LoginResponsePL> call = RetrofitClient.getInstance().getApi().LoginPL(username, password);

        call.enqueue(new Callback<LoginResponsePL>() {
            @Override
            public void onResponse(Call<LoginResponsePL> call, Response<LoginResponsePL> response) {
                LoginResponsePL loginResponsePL = response.body();

                if (!loginResponsePL.isError()){
                    SharedPrefManager.getInstance(LoginPLActivity.this).saveUserPL(loginResponsePL.getUser());

                    Intent intent = new Intent(LoginPLActivity.this, ProfilePLActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginPLActivity.this, loginResponsePL.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponsePL> call, Throwable t) {

            }
        });
    }
}
