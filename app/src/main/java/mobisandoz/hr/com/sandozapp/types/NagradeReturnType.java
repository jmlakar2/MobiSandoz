package mobisandoz.hr.com.sandozapp.types;

/**
 * Created by Josip on 13.4.2015..
 */
public class NagradeReturnType {
    private int id_nagrade;
    private String naziv_nagrade;
    private int cijena;
    private String proizvodi;

    public int getId_nagrade() {
        return id_nagrade;
    }

    public void setId_nagrade(int id_nagrade) {
        this.id_nagrade = id_nagrade;
    }

    public String getNaziv_nagrade() {
        return naziv_nagrade;
    }

    public void setNaziv_nagrade(String naziv_nagrade) {
        this.naziv_nagrade = naziv_nagrade;
    }

    public int getCijena() {
        return cijena;
    }

    public NagradeReturnType() {
    }

    public NagradeReturnType(int id_nagrade, String naziv_nagrade, int cijena, String proizvodi) {
        this.id_nagrade = id_nagrade;
        this.naziv_nagrade = naziv_nagrade;
        this.cijena = cijena;
        this.proizvodi = proizvodi;

    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public String getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(String proizvodi) {
        this.proizvodi = proizvodi;
    }
}
