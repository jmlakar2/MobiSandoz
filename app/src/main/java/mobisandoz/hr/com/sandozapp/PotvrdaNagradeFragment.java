package mobisandoz.hr.com.sandozapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobisandoz.hr.com.sandozapp.core.IskoristiPaketServiceAsyncTask;
import mobisandoz.hr.com.sandozapp.core.LoginLogic;
import mobisandoz.hr.com.sandozapp.core.OdabraniPaketServiceAsyncTask;
import mobisandoz.hr.com.sandozapp.core.PreuzmiNagrade;
import mobisandoz.hr.com.sandozapp.types.NagradeReturnType;

/**
 * Created by Josip on 15.4.2015..
 */
public class PotvrdaNagradeFragment extends Fragment{
    String ispis;
    List<NagradeReturnType> listaNagrade;
    int id_nagrade;
    Button potvrdiBtn;
    Button odustaniBtn;
    Button povratakBtn;
    EditText idPaketa;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.iskoristi_paket_layout, container, false);

        Bundle args = getArguments();
       id_nagrade  = args.getInt("id_nagrade", 404);

        PreuzmiNagrade preuzmiNagrade = new PreuzmiNagrade();
        listaNagrade = preuzmiNagrade.vratiNagrade(String.valueOf(id_nagrade));

        for (int i = 0; i < listaNagrade.size(); i++) {
            ispis =
                            "\nNaziv paketa: "+listaNagrade.get(i).getNaziv_nagrade()
                            +"\nVrijednost: "+String.valueOf(listaNagrade.get(i).getCijena())+" bodova"
                            +"\nProizvodi u paketu:\n"+listaNagrade.get(i).getProizvodi();

        }

        TextView textView = (TextView) v.findViewById(R.id.odabraniPakettxt);
        textView.setText(ispis);

        potvrdiBtn = (Button) v.findViewById(R.id.potvrdiButton);
        odustaniBtn = (Button) v.findViewById(R.id.odustaniiButton);
        povratakBtn = (Button) v.findViewById(R.id.povratakButton);
        idPaketa = (EditText) v.findViewById(R.id.idPaketEdit);

        potvrdiBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                String idpaketa = idPaketa.getText().toString();
                if (idpaketa.isEmpty() || idpaketa==null){
                    Toast.makeText(getActivity(), "Morate unesti id paketa", Toast.LENGTH_LONG).show();
                }
                else {
                    IskoristiPaketServiceAsyncTask asyncTask = new IskoristiPaketServiceAsyncTask();
                    asyncTask.execute(new Object[]{String.valueOf(id_nagrade), idpaketa});
                    potvrdiBtn.setVisibility(View.GONE);
                    odustaniBtn.setVisibility(View.GONE);
                    povratakBtn.setVisibility(View.VISIBLE);
                    idPaketa.setVisibility(View.GONE);
                }
            }
        });

        odustaniBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               KupljeniPaketiFragment mbf = new KupljeniPaketiFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        });

        povratakBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                KupljeniPaketiFragment mbf = new KupljeniPaketiFragment();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();
            }
        });

        return v;

    }

    public static PotvrdaNagradeFragment newInstance(int id_nagrade) {
        PotvrdaNagradeFragment f = new PotvrdaNagradeFragment();

        Bundle args = new Bundle();
        args.putInt("id_nagrade", id_nagrade);
        f.setArguments(args);
        return f;
    }
}
