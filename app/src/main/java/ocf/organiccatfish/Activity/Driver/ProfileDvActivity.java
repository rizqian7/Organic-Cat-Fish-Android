package ocf.organiccatfish.Activity.Driver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import ocf.organiccatfish.Fragment.Driver.HomeDvFragment;
import ocf.organiccatfish.Fragment.Driver.UserDvFragment;
import ocf.organiccatfish.Fragment.PeternakLele.HomePLFragment;
import ocf.organiccatfish.R;
import ocf.organiccatfish.SplashScreenActivity;
import ocf.organiccatfish.Storage.SharedPrefManager;

public class ProfileDvActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dv_profile);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav_dv);
        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeDvFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.menu_homedv:
                fragment = new HomeDvFragment();
                break;

            case R.id.menu_userdv:
                fragment = new UserDvFragment();
                break;
        }

        if(fragment != null) {
            displayFragment(fragment);
        }

        return false;
    }

    public void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayoutDv, fragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLoggedInDv()){
            Intent intent = new Intent(this, SplashScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

}
