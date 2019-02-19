package id.co.maminfaruq.moviecatalogueapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import id.co.maminfaruq.moviecatalogueapi.utils.Constans;
import id.co.maminfaruq.moviecatalogueapi.view.NowPlayingFragment;
import id.co.maminfaruq.moviecatalogueapi.view.UpcomingFragment;

public class MainActivity extends AppCompatActivity {

    NowPlayingFragment mHome = new NowPlayingFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, mHome)
                .commit();



        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment mFragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        mFragment = new NowPlayingFragment();
                        break;
                    case R.id.navigation_dashboard:
                        mFragment = new UpcomingFragment();
                        break;

                }
                assert mFragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, mFragment).commit();

                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_setting) {
            startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}
