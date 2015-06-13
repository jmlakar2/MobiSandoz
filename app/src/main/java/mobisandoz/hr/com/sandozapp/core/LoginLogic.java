package mobisandoz.hr.com.sandozapp.core;

import android.content.Context;

import java.util.ArrayList;

import mobisandoz.hr.com.sandozapp.types.AutentReturnType;

/**
 * Created by Josip on 12.4.2015..
 */
public class LoginLogic {
    private Login login;

    /**
     * ovisno o vrijednosti varijable odabir instancira se objekt željenog plugina za prijavu (implementacija sučelja Ilogin)
     */
    public LoginLogic () {
        login = new Login();
    }

    /**
     * provjerava jesu li točni korisnički podaci, ako jesu sprema generirani kod i korisničko ime
     * u SharedPreferences
     *
     * @param user
     * @param pass
     * @param ctx
     * @return true ako je autentikacija uspjela, false u suprotnom
     */
    public boolean provjeraPodaci (String user, String pass, Context ctx) {
        AutentReturnType podaci;

        podaci = login.autent(user, pass);
        if (podaci.getStatus() == 1) {
            login.spremiKod(podaci.getUser(), podaci.getKod(), ctx);
            return true;
        }

        return false;
    }

    /**
     * Provjerava da li je korisnik logiran
     *
     * @param ctx
     * @return true ako je logiran, false ako nije
     */

    public boolean provjeraLogiran (Context ctx) {
        try {
            if (login.vratiUser(ctx) == null || login.vratiKod(ctx) == null)
                return false;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Briše korisnikove podatke spremljene u SharedPreferences
     *
     * @param ctx
     */

    public void obrisiKorisnik (Context ctx){
        login.logout(ctx);
    }

    /**
     * metoda vraća polje sa stringovima korisničko ime, korisničko ime je spremljeno u prvi element polja,
     * kod je spremljen u drugi element polja
     * @param ctx
     * @return
     */

    public ArrayList<String> vratiLokalnePodatke (Context ctx) {
        ArrayList<String> rezultat = new ArrayList<String>();

        rezultat.add(login.vratiUser(ctx));
        rezultat.add(login.vratiKod(ctx));

        return rezultat;
    }
}
