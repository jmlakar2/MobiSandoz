package mobisandoz.hr.com.sandozapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mobisandoz.hr.com.sandozapp.core.Login;
import mobisandoz.hr.com.sandozapp.core.OtvorenPoklonAdapter;
import mobisandoz.hr.com.sandozapp.core.PreuzmiNagrade;
import mobisandoz.hr.com.sandozapp.core.ZatvorenPoklonAdapter;
import mobisandoz.hr.com.sandozapp.types.NagradeReturnType;

/**
 * Created by Josip on 15.4.2015..
 */
public class KupljeniPaketiFragment extends Fragment {
    ArrayList<String> ispis;
    List<NagradeReturnType> listaNagrade;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kupljeni_paketi_layout, container, false);

        Login login = new Login();
        String user = login.vratiUser(getActivity());
        String kod = login.vratiKod(getActivity());

        PreuzmiNagrade preuzmiNagrade = new PreuzmiNagrade();
        listaNagrade = preuzmiNagrade.vratiNagrade(user, kod);

        ListView listView = (ListView) v.findViewById(R.id.list);
        ispis = new ArrayList<String>();

        for (int i = 0; i < listaNagrade.size(); i++) {
            ispis.add(
                            "\nNAZIV PAKETA: "+listaNagrade.get(i).getNaziv_nagrade()
                            +"\nVRIJEDNOST: "+String.valueOf(listaNagrade.get(i).getCijena())+" BODOVA"
                            +"\nPROIZVODI U PAKETU:\n"+listaNagrade.get(i).getProizvodi()
            );
        }

        if (ispis.isEmpty()) {
            RelativeLayout rl = (RelativeLayout) v.findViewById(R.id.nematePaket);
            rl.setVisibility(View.VISIBLE);
        }

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
         //       R.layout.custom_txt_view, ispis);

        OtvorenPoklonAdapter adapter = new OtvorenPoklonAdapter(this.getActivity(),ispis);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
                final int idNagrada = listaNagrade.get(position).getId_nagrade();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                PotvrdaNagradeFragment mbf = PotvrdaNagradeFragment.newInstance(idNagrada);
                fragmentManager.beginTransaction()
                        .replace(R.id.container, mbf)
                        .commit();

            }
        });


        return v;
    }
}
