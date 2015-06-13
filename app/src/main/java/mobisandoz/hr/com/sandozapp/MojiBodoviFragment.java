package mobisandoz.hr.com.sandozapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobisandoz.hr.com.sandozapp.core.Login;
import mobisandoz.hr.com.sandozapp.core.PreuzmiBodove;

/**
 * Created by Josip on 29.3.2015..
 */
public class MojiBodoviFragment extends Fragment{
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.moji_bodovi_layout, container, false);

        PreuzmiBodove preuzmiBodove = new PreuzmiBodove();
        Login login = new Login();
        String user = login.vratiUser(getActivity());
        String kod = login.vratiKod(getActivity());

        int bodovi = preuzmiBodove.getBodovi(user,kod);

        TextView textView = (TextView) v.findViewById(R.id.bodoviTxt);
        textView.setText(String.valueOf(bodovi));

        return v;
    }
}
