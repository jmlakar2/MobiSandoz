package mobisandoz.hr.com.sandozapp.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mobisandoz.hr.com.sandozapp.types.NagradeReturnType;
import mobisandoz.hr.com.sandozapp.types.SuradniciReturnType;

/**
 * Created by Josip on 16.4.2015..
 */
public class PreuzmiSuradnike {
    public List<SuradniciReturnType> vratiSuradnike() {
        String rezultat = dohvatiJson ();
        List<SuradniciReturnType> parsiraniRezultat = new ArrayList<SuradniciReturnType>();
        parsiraniRezultat = parsirajJson (rezultat);
        return parsiraniRezultat;
    }

    public String dohvatiJson () {

        SuradniciServiceAsyncTask asyncTask = new SuradniciServiceAsyncTask();
        asyncTask.execute(new Object[] {});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    private List<SuradniciReturnType> parsirajJson (String jsonPod){
        List<SuradniciReturnType> rezultat = new ArrayList<SuradniciReturnType>();
        try {
            JSONArray suradnici = new JSONArray(jsonPod);

            for (int i = 0; i < suradnici.length(); i++){
                JSONObject suradnik = suradnici.getJSONObject(i);
                String naziv_suradnik = suradnik.getString("naziv_suradnik");
                Double x_kord = suradnik.getDouble("x_kord");
                Double y_kord = suradnik.getDouble("y_kord");

                SuradniciReturnType suradnikReturn = new SuradniciReturnType(naziv_suradnik,x_kord,y_kord);
                rezultat.add(suradnikReturn);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rezultat;
    }
}
