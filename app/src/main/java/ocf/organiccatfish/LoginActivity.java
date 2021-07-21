package ocf.organiccatfish;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ocf.organiccatfish.Activity.Driver.LoginDvActivity;
import ocf.organiccatfish.Activity.PeternakLele.LoginPLActivity;
import ocf.organiccatfish.Activity.PeternakLele.ProfilePLActivity;
import ocf.organiccatfish.Activity.RumahMakan.LoginRMActivity;
import ocf.organiccatfish.Storage.SharedPrefManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button1 = findViewById(R.id.btn_peternaklele);
        Button button2 = findViewById(R.id.btn_rumahmakan);
        Button button3 = findViewById(R.id.btn_driver);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_peternaklele:
                intent = new Intent(this, LoginPLActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_rumahmakan:
                intent = new Intent(this, LoginRMActivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_driver:
                intent = new Intent(this, LoginDvActivity.class);
                this.startActivity(intent);
                break;
        }
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
}
