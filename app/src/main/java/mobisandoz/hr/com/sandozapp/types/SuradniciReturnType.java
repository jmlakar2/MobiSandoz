package mobisandoz.hr.com.sandozapp.types;

/**
 * Created by Josip on 16.4.2015..
 */
public class SuradniciReturnType {
    private String nazivSuradnik;
    private double x_kord;
    private double y_kord;

    public SuradniciReturnType(String nazivSuradnik, double x_kord, double y_kord) {
        this.nazivSuradnik = nazivSuradnik;
        this.x_kord = x_kord;
        this.y_kord = y_kord;
    }

    public String getNazivSuradnik() {
        return nazivSuradnik;
    }

    public void setNazivSuradnik(String nazivSuradnik) {
        this.nazivSuradnik = nazivSuradnik;
    }

    public double getX_kord() {
        return x_kord;
    }

    public void setX_kord(double x_kord) {
        this.x_kord = x_kord;
    }

    public double getY_kord() {
        return y_kord;
    }

    public void setY_kord(double y_kord) {
        this.y_kord = y_kord;
    }
}
