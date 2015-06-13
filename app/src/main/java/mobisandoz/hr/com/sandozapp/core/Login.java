package mobisandoz.hr.com.sandozapp.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import mobisandoz.hr.com.sandozapp.types.AutentReturnType;

/**
 * Created by Josip on 12.4.2015..
 */
public class Login {
    public AutentReturnType autent(String user, String pass) {
        AutentReturnType rezultat;

        String jsonPod = dohvatiJson (user, pass);
        rezultat = parsirajJson (jsonPod);

        return rezultat;
    }

    /**
     * metoda za primanje podataka o uspješnosti autentifikacije sa servisa
     *
     * @param user
     * @param pass
     * @return json string dobiven sa servisa
     */
    private String dohvatiJson (String user, String pass) {

        AutentServiceAsyncTask asyncTask = new AutentServiceAsyncTask();
        asyncTask.execute(new Object[] {user, pass});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    /**
     *
     * @param jsonPod json string
     * @return rezultat parsirani podaci iz json stringa
     */
    private AutentReturnType parsirajJson (String jsonPod){
        try {
            AutentReturnType rezultat = new AutentReturnType();

            JSONObject jsonObj = new JSONObject(jsonPod);
            JSONObject jPod = jsonObj.getJSONObject("odgovor");
            rezultat.setKod(jPod.getString("kod"));
            rezultat.setUser(jPod.getString("user"));
            rezultat.setStatus(jPod.getInt("status"));

            return rezultat;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void spremiKod(String user, String kod, Context ctx) {
        SharedPref (user, kod, ctx);
    }

    /**
     * Sprema korisničko ime i kod u SharedPreferences
     *
     * @param user
     * @param kod
     * @param ctx
     */

    private void SharedPref (String user, String kod, Context ctx) {
        SharedPreferences korPod = ctx.getSharedPreferences ("korPod", 0);
        SharedPreferences.Editor editor = korPod.edit();
        editor.putString("user", user);
        editor.commit();
        editor.putString("kod", kod);
        editor.commit();
    }

    public String vratiKod(Context ctx) {
        SharedPreferences myPref = ctx.getSharedPreferences("korPod", 0);
        String kod = myPref.getString("kod", null);
        return kod;
    }

    public String vratiUser(Context ctx) {
        SharedPreferences myPref = ctx.getSharedPreferences("korPod", 0);
        String user = myPref.getString("user", null);
        return user;
    }

    public void logout(Context ctx) {
        SharedPreferences korPod = ctx.getSharedPreferences ("korPod", 0);
        SharedPreferences.Editor editor = korPod.edit();
        editor.putString("user", null);
        editor.commit();
        editor.putString("kod", null);
        editor.commit();
        editor.putInt("status", 0);
        editor.commit();
    }
}
