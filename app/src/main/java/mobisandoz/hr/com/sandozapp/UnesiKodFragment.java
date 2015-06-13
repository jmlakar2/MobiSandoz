package mobisandoz.hr.com.sandozapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import mobisandoz.hr.com.sandozapp.core.Login;
import mobisandoz.hr.com.sandozapp.core.LoginLogic;
import mobisandoz.hr.com.sandozapp.core.UnesiKod;
import mobisandoz.hr.com.sandozapp.types.UnesiKodReturnType;

/**
 * Created by Josip on 16.4.2015..
 */
public class UnesiKodFragment  extends Fragment{
    UnesiKodReturnType rezultat;
    EditText tekst;
    Button unesiBtn;
    ImageButton podijeli;
    ImageButton twitter;
    Button zavrsi;
    TextView podaciPro;
    String kod;
    String user;
    String kod_proizvod;
    View v;
    TextView tv;
    TextView fbTxt;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.unos_koda_layout, container, false);

        Login lo = new Login();
        user = lo.vratiUser(getActivity());
        kod = lo.vratiKod(getActivity());
        tekst = (EditText) v.findViewById(R.id.unosKodEdit);
        kod_proizvod= tekst.getText().toString();
        UnesiKod uk = new UnesiKod();
        if (!kod_proizvod.isEmpty()) {
            rezultat = uk.unesi_kod(user, kod, kod_proizvod);
        }
        else {
            rezultat = new UnesiKodReturnType();
            rezultat.setStatus(0);
        }
        podijeli = (ImageButton) v.findViewById(R.id.podijeliButton);
        twitter = (ImageButton) v.findViewById(R.id.twitterButton);
        zavrsi = (Button) v.findViewById(R.id.zavrsiButton);
        fbTxt = (TextView)v.findViewById(R.id.fbPorukaTxt);
        unesiBtn = (Button) v.findViewById(R.id.unesiBtn);
        tv = (TextView) v.findViewById(R.id.pogreskaTxt);
        podaciPro = (TextView) v.findViewById(R.id.podaciTxt);
        unesiBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                kod_proizvod= tekst.getText().toString();
                UnesiKod uk = new UnesiKod();
                if (!kod_proizvod.isEmpty()) {
                    rezultat = uk.unesi_kod(user, kod, kod_proizvod);
                }
                else {
                    rezultat = new UnesiKodReturnType();
                    rezultat.setStatus(0);
                }
                if (rezultat.getStatus() == 0) {
                    tv.setVisibility(View.VISIBLE);
                } else if (rezultat.getStatus() == 1) {
                    tekst.setVisibility(View.GONE);
                    unesiBtn.setVisibility(View.GONE);
                    tv.setVisibility(View.GONE);
                    podijeli.setVisibility(View.VISIBLE);
                    twitter.setVisibility(View.VISIBLE);
                    zavrsi.setVisibility(View.VISIBLE);
                    String tekst = "Unesli ste kod za proizvod: " + rezultat.getNaziv_proizvoda()
                            + "\nVrijednost bodova: " + rezultat.getVrijednost_bodovi();
                    podaciPro.setText(tekst);
                    podaciPro.setVisibility(View.VISIBLE);
                    fbTxt.setVisibility(View.VISIBLE);
                }

            }
        });
        podijeli.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FacebookFragment mbf = new FacebookFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                Intent intent = new Intent(getActivity(), TwitterActivity.class);
                getActivity().startActivity(intent);
            }
        });

        zavrsi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                PocetnaFragment mbf = new PocetnaFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        });


        return v;

    }
}
