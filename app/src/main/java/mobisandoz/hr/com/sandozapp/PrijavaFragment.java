package mobisandoz.hr.com.sandozapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mobisandoz.hr.com.sandozapp.core.LoginLogic;

/**
 * Created by Josip on 3.4.2015..
 */
public class PrijavaFragment extends Fragment{
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.login_layout, container, false);
        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Prijava");
        Button button = (Button) v.findViewById(R.id.prijavaBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                try {
                    LoginLogic rezultat = new LoginLogic();
                    EditText tekst = (EditText) v.findViewById(R.id.korImePrijava);
                    String user = tekst.getText().toString();
                    tekst = (EditText) v.findViewById(R.id.lozinkaPrijava);
                    String pass = tekst.getText().toString();
                    if (rezultat.provjeraPodaci(user, pass, getActivity())) {
                        Toast.makeText(getActivity(), "Uspjesna prijava", Toast.LENGTH_LONG).show();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        PocetnaFragment mbf = new PocetnaFragment();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, mbf)
                                .commit();
                        //DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                        //drawer.openDrawer(Gravity.LEFT);
                    }
                    else {
                        Toast.makeText(getActivity(), "Neuspješna prijava", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Neuspješna prijava", Toast.LENGTH_LONG).show();
                }

            }
        });

        TextView regTxt = (TextView) v.findViewById(R.id.RegTxt);
        regTxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("Registracija");
                getActivity().invalidateOptionsMenu();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                RegistracijaFragment mbf = new RegistracijaFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();

            }
        });

        return v;
    }


}
