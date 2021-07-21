package ocf.organiccatfish.Activity.PeternakLele;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import ocf.organiccatfish.LoginActivity;
import ocf.organiccatfish.R;
import ocf.organiccatfish.SplashScreenActivity;
import ocf.organiccatfish.Storage.SharedPrefManager;

public class HomePLActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl_home);

        LinearLayout linearLayout1 = findViewById(R.id.btnUpdatePanen);
        LinearLayout linearLayout2 = findViewById(R.id.btnTandaiLokasi);
        LinearLayout linearLayout3 = findViewById(R.id.btnBiodata);
        LinearLayout linearLayout4 = findViewById(R.id.btnHistory);
        LinearLayout linearLayout5 = findViewById(R.id.btnLogout);

        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, SplashScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void logout(){
        SharedPrefManager.getInstance(this).clear();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogout:
                logout();
                break;
        }
    }
}
