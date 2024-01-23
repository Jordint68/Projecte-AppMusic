package org.milaifontanals.spotifypaco2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.milaifontanals.spotifypaco2.db.AppDatabase;
import org.milaifontanals.spotifypaco2.view.AlbumListFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    /*
        Pàgina per fer el drawer layout:
        https://danielme.com/2018/12/19/diseno-android-menu-lateral-con-navigation-drawer/
     */

    private AlbumListFragment albumListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // En principi es podría fer el següent amb un binding...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TOOLBAR:
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(findViewById(R.id.toolbar));

        // DRAWER LAYOUT:
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // NAVIGATION VIEW:
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // HEADER:


        /*
        Room.databaseBuilder(this, AppDatabase.class, "Sample.db")
                .createFromAsset("database/myapp.db")
                .build();
        */


            albumListFragment = new AlbumListFragment();
            showFragment(R.string.nav_mymusic);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int titleId = myGetTitle(item);
        showFragment(titleId);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(int titleId) {
        Fragment fragToShow = null;
        switch (titleId) {
            case R.string.nav_mymusic:
                fragToShow = albumListFragment;
                break;
            // Més casos si cal...

            default:
                throw new IllegalArgumentException("Opció del menú no implementada...");
        }

        if(fragToShow != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragToShow)
                    .commit();
        }
    }

    private int myGetTitle(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_mymusic:
                return R.string.nav_mymusic;
            default:
                throw new IllegalArgumentException("Opció del menú no implementada...");
        }
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public interface OnNavigationItemSelectedListener {

        public boolean onNavigationItemSelected(@NonNull MenuItem item);
    }


}