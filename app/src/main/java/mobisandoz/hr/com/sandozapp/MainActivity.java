package mobisandoz.hr.com.sandozapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.inputmethod.InputMethodManager;

import com.facebook.appevents.AppEventsLogger;

import mobisandoz.hr.com.sandozapp.core.LoginLogic;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private LoginLogic loginLogic = new LoginLogic();

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        if (position == 0) {
            PocetnaFragment mbf = new PocetnaFragment();
            mTitle = "Početna";
            fragmentManager.beginTransaction()
                    .replace(R.id.container, mbf)
                    .commit();
        }
        else if (position == 1) {
            MapFragment mbf = new MapFragment();
            mTitle = "Mapa ljekarni";
            fragmentManager.beginTransaction()
                    .replace(R.id.container, mbf)
                    .commit();
        }
        else if (position == 2) {
            if (loginLogic.provjeraLogiran(this)) {
                MojiBodoviFragment mbf = new MojiBodoviFragment();
                mTitle = "Moji bodovi";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
            else {
                NijeLogiranFragment mbf = new NijeLogiranFragment();
                mTitle = "Niste prijavljeni";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        }
        else if (position == 3) {
            if (loginLogic.provjeraLogiran(this)) {
                PregledNagradaFragment mbf = new PregledNagradaFragment();
                mTitle = "Popis paketa";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
            else {
                NijeLogiranFragment mbf = new NijeLogiranFragment();
                mTitle = "Niste prijavljeni";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        }
        else if (position == 4) {
            if (loginLogic.provjeraLogiran(this)) {
                KupljeniPaketiFragment mbf = new KupljeniPaketiFragment();
                mTitle = "Moji paketi";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
            else {
                NijeLogiranFragment mbf = new NijeLogiranFragment();
                mTitle = "Niste prijavljeni";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        }
        else if (position == 5) {
            if (loginLogic.provjeraLogiran(this)) {
                UnesiKodFragment mbf = new UnesiKodFragment();
                mTitle = "Unesi kod";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
            else {
                NijeLogiranFragment mbf = new NijeLogiranFragment();
                mTitle = "Niste prijavljeni";
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        }
        else if (position == 6) {
            UputeFragment mbf = new UputeFragment();
            mTitle = "Upute o lijeku";
            fragmentManager.beginTransaction()
                    .replace(R.id.container, mbf)
                    .commit();
        }
        else if (position == 7) {
            RegistracijaFragment mbf = new RegistracijaFragment();
            mTitle = "Registracija";
            fragmentManager.beginTransaction()
                    .replace(R.id.container, mbf)
                    .commit();
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        //actionBar.setIcon(R.drawable.logo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            if(!loginLogic.provjeraLogiran(this)) {
                getMenuInflater().inflate(R.menu.main, menu);
                MenuItem item = menu.findItem(R.id.logout);
                item.setVisible(false);
                item = menu.findItem(R.id.login);
                item.setVisible(true);
                restoreActionBar();
            }
            else {
                getMenuInflater().inflate(R.menu.main, menu);
                MenuItem item = menu.findItem(R.id.login);
                item.setVisible(false);
                item = menu.findItem(R.id.logout);
                item.setVisible(true);
                restoreActionBar();
            }
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
