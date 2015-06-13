package mobisandoz.hr.com.sandozapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import mobisandoz.hr.com.sandozapp.core.LoginLogic;

/**
 * Created by Josip on 30.3.2015..
 */
public class PocetnaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.pocetna_layout, container, false);

        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Početna");
        getActivity().invalidateOptionsMenu();



        return v;

    }

}
