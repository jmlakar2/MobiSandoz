package mobisandoz.hr.com.sandozapp.core;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josip on 21.4.2015..
 */
public class Upute {
    public String getUpute(String serijski_broj) {
        String rezultat;

        String jsonPod = dohvatiJson (serijski_broj);
        rezultat = parsirajJson (jsonPod);

        return rezultat;
    }

    private String dohvatiJson (String serijski_broj) {

        UputeServiceAsyncTask asyncTask = new UputeServiceAsyncTask();
        asyncTask.execute(new Object[] {serijski_broj});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    private String parsirajJson (String jsonPod){
        try {
            String rezultat;

            JSONObject jsonObj = new JSONObject(jsonPod);
            JSONObject jPod = jsonObj.getJSONObject("odgovor");
            rezultat = jPod.getString("upute");

            return rezultat;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
