package mobisandoz.hr.com.sandozapp.core;

import org.json.JSONException;
import org.json.JSONObject;

import mobisandoz.hr.com.sandozapp.types.AutentReturnType;
import mobisandoz.hr.com.sandozapp.types.UnesiKodReturnType;

/**
 * Created by Josip on 16.4.2015..
 */
public class UnesiKod {
    public UnesiKodReturnType unesi_kod(String user, String kod, String kod_proizvod) {
        UnesiKodReturnType rezultat;

        String jsonPod = dohvatiJson (user, kod, kod_proizvod);
        rezultat = parsirajJson (jsonPod);

        return rezultat;
    }

    private String dohvatiJson (String user, String pass, String kod_proizvod) {

        UnesiKodServiceAsyncTask asyncTask = new UnesiKodServiceAsyncTask();
        asyncTask.execute(new Object[] {user, pass, kod_proizvod});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    private UnesiKodReturnType parsirajJson (String jsonPod){
        try {
            UnesiKodReturnType rezultat = new UnesiKodReturnType();

            JSONObject jsonObj = new JSONObject(jsonPod);
            JSONObject jPod = jsonObj.getJSONObject("odgovor");
            rezultat.setNaziv_proizvoda(jPod.getString("naziv_proizvoda"));
            rezultat.setVrijednost_bodovi(jPod.getString("vrijednost_bodovi"));
            rezultat.setStatus(jPod.getInt("status"));

            return rezultat;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
