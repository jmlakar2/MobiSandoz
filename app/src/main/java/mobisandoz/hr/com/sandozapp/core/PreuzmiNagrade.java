package mobisandoz.hr.com.sandozapp.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mobisandoz.hr.com.sandozapp.types.NagradeReturnType;

/**
 * Created by Josip on 13.4.2015..
 */
public class PreuzmiNagrade {
    public List<NagradeReturnType> vratiNagrade() {
        String rezultat = dohvatiJson ();
        List<NagradeReturnType> parsiraniRezultat = new ArrayList<NagradeReturnType>();
        parsiraniRezultat = parsirajJson (rezultat);
        return parsiraniRezultat;
    }

    public List<NagradeReturnType> vratiNagrade(String user, String kod) {
        String rezultat = dohvatiJson (user, kod);
        List<NagradeReturnType> parsiraniRezultat = new ArrayList<NagradeReturnType>();
        parsiraniRezultat = parsirajJson (rezultat);
        return parsiraniRezultat;
    }

    public List<NagradeReturnType> vratiNagrade(String korisnik_nagrada) {
        String rezultat = dohvatiJson (korisnik_nagrada);
        List<NagradeReturnType> parsiraniRezultat = new ArrayList<NagradeReturnType>();
        parsiraniRezultat = parsirajJson (rezultat);
        return parsiraniRezultat;
    }

    public String dohvatiJson () {

        PreuzmiNagradeServiceAsyncTask asyncTask = new PreuzmiNagradeServiceAsyncTask();
        asyncTask.execute(new Object[] {});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    public String dohvatiJson (String korisnik_nagrada) {

        OdabraniPaketServiceAsyncTask asyncTask = new OdabraniPaketServiceAsyncTask();
        asyncTask.execute(new Object[] {korisnik_nagrada});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    public String dohvatiJson (String user, String kod) {

        KupljeniPaketiServiceAsyncTask asyncTask = new KupljeniPaketiServiceAsyncTask();
        asyncTask.execute(new Object[] {user, kod});
        String jsonResult = "[]";
        try {
            jsonResult = (String) ((Object[])asyncTask.get())[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    private List<NagradeReturnType> parsirajJson (String jsonPod){
        List<NagradeReturnType> rezultat = new ArrayList<NagradeReturnType>();
        try {
            JSONArray nagrade = new JSONArray(jsonPod);

            for (int i = 0; i < nagrade.length(); i++){
                JSONObject nagrada = nagrade.getJSONObject(i);
                int id_nagrade = nagrada.getInt("id_nagrade");
                String naziv_nagrade = nagrada.getString("naziv_nagrade");
                int cijena = nagrada.getInt("cijena");
                String proizvodi = nagrada.getString("proizvodi");

                NagradeReturnType nagradaReturn = new NagradeReturnType(id_nagrade,naziv_nagrade,cijena,proizvodi);
                rezultat.add(nagradaReturn);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rezultat;
    }
}
