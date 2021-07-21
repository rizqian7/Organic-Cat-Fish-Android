package ocf.organiccatfish.Activity.RumahMakan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ocf.organiccatfish.Activity.PeternakLele.ProfilePLActivity;
import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.RumahMakanModel.RegisterResponseRM;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRMActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etKtp, etNamaLengkap, etNoHp, etNamaRM, etAlamat, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_register);

        etKtp           = findViewById(R.id.etNoKtpRm);
        etNamaLengkap   = findViewById(R.id.etNamaLengkapRm);
        etNoHp          = findViewById(R.id.etNoHpRm);
        etNamaRM        = findViewById(R.id.etNamaUsahaRm);
        etAlamat        = findViewById(R.id.etAlamatRm);
        etUsername      = findViewById(R.id.etUsernameRm);
        etPassword      = findViewById(R.id.etPasswordRm);

        findViewById(R.id.btnDaftarRM).setOnClickListener(this);
    }

    private void userSignUpRM(){
        String no_ktp           = etKtp.getText().toString().trim();
        String nama_lengkap     = etNamaLengkap.getText().toString().trim();
        String no_hp            = etNoHp.getText().toString().trim();
        String nama_rm          = etNamaRM.getText().toString().trim();
        String alamat           = etAlamat.getText().toString().trim();
        String username         = etUsername.getText().toString().trim();
        String password         = etPassword.getText().toString().trim();

        if (no_ktp.isEmpty()){
            etKtp.setError("Please Fill Out This Field");
            etKtp.requestFocus();
            return;
        }

        if(no_ktp.length() < 16) {
            etKtp.setError("Nomor KTP Harus 16 Digit");
            etKtp.requestFocus();
            return;
        }

        if (nama_lengkap.isEmpty()){
            etNamaLengkap.setError("Please Fill Out This Field");
            etNamaLengkap.requestFocus();
            return;
        }

        if (no_hp.isEmpty()){
            etNoHp.setError("Please Fill Out This Field");
            etNoHp.requestFocus();
            return;
        }

        if (nama_rm.isEmpty()){
            etNamaRM.setError("Please Fill Out This Field");
            etNamaRM.requestFocus();
            return;
        }

        if (alamat.isEmpty()){
            etAlamat.setError("Please Fill Out This Field");
            etAlamat.requestFocus();
            return;
        }

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

        Call<RegisterResponseRM> call = RetrofitClient
                .getInstance()
                .getApi()
                .DaftarRM(no_ktp, nama_lengkap, no_hp, nama_rm, alamat, username, password);

        call.enqueue(new Callback<RegisterResponseRM>() {
            @Override
            public void onResponse(Call<RegisterResponseRM> call, Response<RegisterResponseRM> response) {
                if (response.code() == 201) {
                    RegisterResponseRM registerResponseRM = response.body();
                    Toast.makeText(RegisterRMActivity.this, registerResponseRM.getMsg(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterRMActivity.this, ProfileRMActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (response.code() == 422) {
                    Toast.makeText(RegisterRMActivity.this, "User Already Exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponseRM> call, Throwable t) {
                Toast.makeText(RegisterRMActivity.this, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
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
        switch (v.getId()){
            case R.id.btnDaftarRM:
                userSignUpRM();
                break;
        }
    }
}
