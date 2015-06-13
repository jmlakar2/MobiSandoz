package mobisandoz.hr.com.sandozapp.core;

import org.json.JSONException;
import org.json.JSONObject;

import mobisandoz.hr.com.sandozapp.types.AutentReturnType;

/**
 * Created by Josip on 13.4.2015..
 */
public class PreuzmiBodove {
    public int getBodovi(String user, String kod) {
        int rezultat;

        String jsonPod = dohvatiJson (user, kod);
        rezultat = parsirajJson (jsonPod);

        return rezultat;
    }

    private String dohvatiJson (String user, String kod) {

        PreuzmiBodoveServiceAsyncTask asyncTask = new PreuzmiBodoveServiceAsyncTask();
        asyncTask.execute(new Object[] {user, kod});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    private int parsirajJson (String jsonPod){
        try {
            int rezultat;

            JSONObject jsonObj = new JSONObject(jsonPod);
            JSONObject jPod = jsonObj.getJSONObject("odgovor");
            rezultat = jPod.getInt("bodovi");

            return rezultat;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
