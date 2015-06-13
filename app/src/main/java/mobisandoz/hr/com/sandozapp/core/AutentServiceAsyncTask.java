package mobisandoz.hr.com.sandozapp.core;

import android.os.AsyncTask;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Josip on 12.4.2015..
 */
public class AutentServiceAsyncTask extends AsyncTask<Object, Void, Object> {
    @Override
    protected Object doInBackground(Object... params) {
        String user = (String) params[0];
        String pass = (String) params[1];

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(
                "http://178.62.195.200/estud/servisi/autent.php?user=" + user + "&pass=" + pass
        );
        ResponseHandler<String> handler = new BasicResponseHandler();
        String jsonResult = "[]";
        try {
            jsonResult = client.execute(request, handler);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.getConnectionManager().shutdown();

        Object[] rezultat = new Object [] {jsonResult};

        return rezultat;
    }
}
