package ocf.organiccatfish.Activity.PeternakLele;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ocf.organiccatfish.Api.RetrofitClient;
import ocf.organiccatfish.Model.PeternakLeleModel.RegisterResponsePL;
import ocf.organiccatfish.R;
import ocf.organiccatfish.Storage.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPLActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etKtp, etNamaLengkap, etHp, etNamaUsaha, etJumlahKolam, etJumlahProduksi, etAlamat, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl_register);

        etKtp               = findViewById(R.id.etNoKtp);
        etNamaLengkap       = findViewById(R.id.etNamaLengkap);
        etHp                = findViewById(R.id.etNoHp);
        etNamaUsaha         = findViewById(R.id.etNamaUsaha);
        etJumlahKolam       = findViewById(R.id.etJumlahKolam);
        etJumlahProduksi    = findViewById(R.id.etJumlahProduksi);
        etAlamat            = findViewById(R.id.etAlamat);
        etUsername          = findViewById(R.id.etUsername);
        etPassword          = findViewById(R.id.etPassword);

        findViewById(R.id.btnDaftarPL).setOnClickListener(this);
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

    private void userSignUp(){
        String no_ktp           = etKtp.getText().toString().trim();
        String nama_lengkap     = etNamaLengkap.getText().toString().trim();
        String no_hp            = etHp.getText().toString().trim();
        String nama_usaha       = etNamaUsaha.getText().toString().trim();
        String jumlahKolam      = etJumlahKolam.getText().toString();
        int jumlah_kolam        = Integer.parseInt(jumlahKolam);
        String jumlahProduksi   = etJumlahProduksi.getText().toString();
        int jumlah_produksi     = Integer.parseInt(jumlahProduksi);
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
            etHp.setError("Please Fill Out This Field");
            etHp.requestFocus();
            return;
        }

        if (nama_usaha.isEmpty()){
            etNamaUsaha.setError("Please Fill Out This Field");
            etNamaUsaha.requestFocus();
            return;
        }

        if (jumlah_kolam < 0){
            etJumlahKolam.setError("Please Fill Out This Field");
            etJumlahKolam.requestFocus();
            return;
        }

        if (jumlah_produksi < 0){
            etJumlahProduksi.setError("Please Fill Out This Field");
            etJumlahProduksi.requestFocus();
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

        Call <RegisterResponsePL> call = RetrofitClient
                .getInstance()
                .getApi()
                .DaftarPL(no_ktp, nama_lengkap, no_hp, nama_usaha, jumlah_kolam, jumlah_produksi, alamat, username, password);

        call.enqueue(new Callback<RegisterResponsePL>() {
            @Override
            public void onResponse(Call<RegisterResponsePL> call, Response<RegisterResponsePL> response) {
                if (response.code() == 201) {
                    RegisterResponsePL registerResponsePL = response.body();
                    Toast.makeText(RegisterPLActivity.this, registerResponsePL.getMsg(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(RegisterPLActivity.this, ProfilePLActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else if (response.code() == 422) {
                    Toast.makeText(RegisterPLActivity.this, "User Already Exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponsePL> call, Throwable t) {
                Toast.makeText(RegisterPLActivity.this, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDaftarPL:
                userSignUp();
                break;
        }
    }
}
