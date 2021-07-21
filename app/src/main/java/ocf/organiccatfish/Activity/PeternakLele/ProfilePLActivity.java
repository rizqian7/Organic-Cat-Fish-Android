package ocf.organiccatfish.Activity.PeternakLele;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ocf.organiccatfish.Fragment.PeternakLele.HistoryPLFragment;
import ocf.organiccatfish.Fragment.PeternakLele.HomePLFragment;
import ocf.organiccatfish.Fragment.PeternakLele.UserPLFragment;
import ocf.organiccatfish.R;
import ocf.organiccatfish.SplashScreenActivity;
import ocf.organiccatfish.Storage.SharedPrefManager;

public class ProfilePLActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomePLFragment.OnFragmentInteractionListener{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl_profile);

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomePLFragment());
    }

    public void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.menu_home:
                fragment = new HomePLFragment();
                break;
            case R.id.menu_history:
                fragment = new HistoryPLFragment();
                break;
            case R.id.menu_user:
                fragment = new UserPLFragment();
                break;
        }

        if(fragment != null) {
            displayFragment(fragment);
        }

        return false;
    }

    @Override
    public void onFragmentListener(Uri uri) {

    }
}
