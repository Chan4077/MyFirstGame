package com.edricchan.myfirstgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static java.lang.System.out;

/**
 * Created by edricchan on 24/5/17.
 */

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dice Roller");
        toolbar.setSubtitle("Roll the dice to begin!");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainActivityFragment()).commit();
        configureNav();
        NotificationCompat.Builder mBuilder1 = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_numeric_1_box_black_24dp)
                .setContentTitle("Dice Roller: Get more achievements")
                .setContentText("Click this notification to get a bonus achievement! :)");
    }

    /**
     * Configuration of the sidenav
     *
     * @return void
     * @since 1.2.1
     */
    protected void configureNav() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                Fragment f = null;
                int itemId = item.getItemId();

                switch (itemId) {
                    case R.id.one_dice_select:
                        // TODO: Complete this
                        f = new MainActivityFragment();
                        break;
                    case R.id.two_dice_select:
                        // TODO: Complete this
                        f = new MainActivityFragment();
                        break;
                    case R.id.three_dice_select:
                        // TODO: Complete this
                        f = new MainActivityFragment();
                        break;
                }

                if (f != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new MainActivityFragment());
                    transaction.commit();
                    drawerLayout.closeDrawers();
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent prefsIntent = new Intent(this, AppPreferenceActivity.class);
                // TODO: Remove this line
                out.println("Starting new prefsIntent");
                startActivity(prefsIntent);
                return true;
            case R.id.action_share_app:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this open-source app by Edric Chan! Source code available at https://github.com/Chan4077/MyFirstGame");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_intent_value)));
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                super.onOptionsItemSelected(item);
                return true;
        }
    }
}
