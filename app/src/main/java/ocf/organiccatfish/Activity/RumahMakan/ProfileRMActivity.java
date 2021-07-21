package ocf.organiccatfish.Activity.RumahMakan;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import ocf.organiccatfish.Fragment.PeternakLele.HistoryPLFragment;
import ocf.organiccatfish.Fragment.PeternakLele.HomePLFragment;
import ocf.organiccatfish.Fragment.PeternakLele.UserPLFragment;
import ocf.organiccatfish.Fragment.RumahMakan.HistoryRMFragment;
import ocf.organiccatfish.Fragment.RumahMakan.HomeRMFragment;
import ocf.organiccatfish.Fragment.RumahMakan.UserRMFragment;
import ocf.organiccatfish.R;
import ocf.organiccatfish.SplashScreenActivity;
import ocf.organiccatfish.Storage.SharedPrefManager;

public class ProfileRMActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_profile);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_rm);
        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeRMFragment());
    }

    public void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayoutrm, fragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLoggedInRm()){
            Intent intent = new Intent(this, SplashScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.menu_home:
                fragment = new HomeRMFragment();
                break;
            case R.id.menu_history:
                fragment = new HistoryRMFragment();
                break;
            case R.id.menu_user:
                fragment = new UserRMFragment();
                break;
        }

        if(fragment != null) {
            displayFragment(fragment);
        }

        return false;
    }


}
