package mobisandoz.hr.com.sandozapp.types;

/**
 * Created by Josip on 16.4.2015..
 */
public class UnesiKodReturnType {
    private String naziv_proizvoda;
    private String vrijednost_bodovi;
    private int status;

    public UnesiKodReturnType(String naziv_proizvoda, String vrijednost_bodovi, int status) {
        this.naziv_proizvoda = naziv_proizvoda;
        this.vrijednost_bodovi = vrijednost_bodovi;
        this.status = status;
    }

    public UnesiKodReturnType() {
    }

    public String getNaziv_proizvoda() {

        return naziv_proizvoda;

    }

    public void setNaziv_proizvoda(String naziv_proizvoda) {
        this.naziv_proizvoda = naziv_proizvoda;
    }

    public String getVrijednost_bodovi() {
        return vrijednost_bodovi;
    }

    public void setVrijednost_bodovi(String vrijednost_bodovi) {
        this.vrijednost_bodovi = vrijednost_bodovi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
