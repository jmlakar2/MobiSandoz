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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URLEncoder;

import mobisandoz.hr.com.sandozapp.core.LoginLogic;
import mobisandoz.hr.com.sandozapp.core.Upute;


/**
 * Created by Josip on 21.4.2015..
 */
public class UputeFragment extends Fragment {
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.upute_layout, container, false);

        final Button uputeBtn = (Button) v.findViewById(R.id.unesiSerBtn);
        uputeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View x) {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                EditText unos = (EditText) getActivity().findViewById(R.id.unosSerKodEdit);
                String serijski_broj = unos.getText().toString();
                if (serijski_broj.isEmpty()) {
                    Toast.makeText(getActivity(), "Niste unjeli serijski broj", Toast.LENGTH_LONG).show();
                }
                else {
                    Upute upute = new Upute();
                    String link = upute.getUpute(serijski_broj);
                    if (link == "null") {
                        Toast.makeText(getActivity(), "Serijski broj nije ispravan", Toast.LENGTH_LONG).show();
                    }
                    else {
                        uputeBtn.setVisibility(View.GONE);
                        unos.setVisibility(View.GONE);
                        String url = "http://docs.google.com/gview?embedded=true&url=" + link;
                        WebView webView = (WebView) v.findViewById(R.id.upute);
                        webView.setVisibility(View.VISIBLE);
                        String doc="<iframe src='http://docs.google.com/viewer?url="+link+"&embedded=true' width='100%' height='100%' style='border: none;'></iframe>";
                        //String urlLink = URLEncoder.encode(link);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.getSettings().setAllowFileAccess(true);
                        webView.loadUrl(url);
                    }
                }

            }
        });

        return v;
    }
}
