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
import android.widget.Toast;

import mobisandoz.hr.com.sandozapp.core.RegServiceAsyncTask;

/**
 * Created by Josip on 12.4.2015..
 */
public class RegistracijaFragment extends Fragment {
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.registracija_layout, container, false);
        Button button = (Button) v.findViewById(R.id.regBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                EditText tekst = (EditText) v.findViewById(R.id.imeReg);
                String ime = tekst.getText().toString();
                tekst = (EditText) v.findViewById(R.id.prezimeReg);
                String prezime = tekst.getText().toString();
                tekst = (EditText) v.findViewById(R.id.emailReg);
                String email = tekst.getText().toString();
                tekst = (EditText) v.findViewById(R.id.korImeReg);
                String user = tekst.getText().toString();
                tekst = (EditText) v.findViewById(R.id.lozinkaReg);
                String pass = tekst.getText().toString();

                if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(getActivity(), "Potrebno je unesti sve podatke", Toast.LENGTH_LONG).show();
                }
                else {
                    RegServiceAsyncTask asyncTask = new RegServiceAsyncTask();
                    asyncTask.execute(new Object[] {ime, prezime, email,user,pass});
                    PocetnaFragment mbf = new PocetnaFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.container, mbf)
                            .commit();
                    Toast.makeText(getActivity(), "Zahvaljujemo na uspješnoj registraciji", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }
}
