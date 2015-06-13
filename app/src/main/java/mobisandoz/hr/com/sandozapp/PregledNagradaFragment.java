package mobisandoz.hr.com.sandozapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mobisandoz.hr.com.sandozapp.core.Login;
import mobisandoz.hr.com.sandozapp.core.OdabirNagradeServiceAsyncTask;
import mobisandoz.hr.com.sandozapp.core.PreuzmiBodove;
import mobisandoz.hr.com.sandozapp.core.PreuzmiNagrade;
import mobisandoz.hr.com.sandozapp.core.ZatvorenPoklonAdapter;
import mobisandoz.hr.com.sandozapp.types.NagradeReturnType;

/**
 * Created by Josip on 13.4.2015..
 */
public class PregledNagradaFragment extends Fragment{
    int bodovi;
    ArrayList<String> ispis;
    List<NagradeReturnType> listaNagrade;
    TextView textView;
    PreuzmiBodove preuzmiBodove;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nagrade_main_layout, container, false);

        preuzmiBodove = new PreuzmiBodove();
        Login login = new Login();
        String user = login.vratiUser(getActivity());
        String kod = login.vratiKod(getActivity());

        bodovi = preuzmiBodove.getBodovi(user,kod);

        textView = (TextView) v.findViewById(R.id.prikazBodoviTxt);
        textView.setText(String.valueOf(bodovi));

        PreuzmiNagrade preuzmiNagrade = new PreuzmiNagrade();
        listaNagrade = preuzmiNagrade.vratiNagrade();

        ListView listView = (ListView) v.findViewById(R.id.list);
        ispis = new ArrayList<String>();

        for (int i = 0; i < listaNagrade.size(); i++) {
            ispis.add(
                            "\nNAZIV PAKETA: "+listaNagrade.get(i).getNaziv_nagrade()
                            +"\nVRIJEDNOST: "+String.valueOf(listaNagrade.get(i).getCijena())+" BODOVA"
                            +"\nPROIZVODI U PAKETU:\n"+listaNagrade.get(i).getProizvodi()
            );
        }

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.custom_txt_view, ispis);
        ZatvorenPoklonAdapter adapter = new ZatvorenPoklonAdapter(this.getActivity(),ispis);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
                final int idNagrada = listaNagrade.get(position).getId_nagrade();
                final int cijena = listaNagrade.get(position).getCijena();

                if (bodovi >= cijena) {

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    Login login = new Login();
                                    String user = login.vratiUser(getActivity());
                                    String kod = login.vratiKod(getActivity());
                                    OdabirNagradeServiceAsyncTask onsat = new OdabirNagradeServiceAsyncTask();
                                    onsat.execute(new Object[] {user,kod,String.valueOf(idNagrada),String.valueOf(cijena)});
                                    Toast.makeText(getActivity(), "Vas odabir je pohranjen!", Toast.LENGTH_LONG).show();
                                    bodovi = preuzmiBodove.getBodovi(user,kod);
                                    textView.setText(String.valueOf(bodovi));
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Jeste li sigurni?").setPositiveButton("Da", dialogClickListener)
                            .setNegativeButton("Ne", dialogClickListener).show();
                }
                else {
                    AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                    ad.setCancelable(false);
                    ad.setMessage("Nemate dovoljno bodova!");
                    ad.setButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            }
        });


        return v;
    }

}
