package com.acidulat.iproblem;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;


    private MapFragment mapFragment;
    private AddFragment addFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav= (BottomNavigationView)  findViewById(R.id.main_nav);

        mapFragment = new MapFragment();
        addFragment = new AddFragment();
        profileFragment = new ProfileFragment();

        setFragment(mapFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.nav_map :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(mapFragment);
                        return true;
                    case R.id.nav_add :
                        mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(addFragment);
                        return true;
                    case R.id.nav_profile :
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(profileFragment);
                        return true;
                    default :
                        return false;
                }

            }


        });
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
}
