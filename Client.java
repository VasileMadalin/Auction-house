import java.util.LinkedHashMap;
import java.util.Map;

/** aceasta clasa implementeaza caracteristicile unui Client */
public class Client {
    private final int id;
    private final String nume;
    private final String adresa;
    private int nrParticipari;
    private int nrLicitatiiCastigate;

    /** Constructor */
    public Client(int id, String nume, String adresa, int nrParticipari, int nrLicitatiiCastigate) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.nrParticipari = nrParticipari;
        this.nrLicitatiiCastigate = nrLicitatiiCastigate;
    }

    private Map<Integer, Double> Stare_Pret;
    public CasaDeLicitatii casa_de_licitatii;

    /** metoda necesara Design Pattern-ului Observator */
    public void seteazaStare(int stare, double pret_maxim) {
        if (this.Stare_Pret == null) {
            this.Stare_Pret = new LinkedHashMap<>();
        }
        this.Stare_Pret.put(stare, pret_maxim);
        notificaCasa(stare);
    }

    /** metoda necesara Design Pattern-ului Observator */
    public double Stare(int id_produs_vanzare) {
        double pret_max = 0;
        for (Map.Entry<Integer, Double> entry : this.Stare_Pret.entrySet()) {
            if (entry.getKey() == id_produs_vanzare) {
                pret_max = entry.getValue();
            }
        }
        return pret_max;
    }

    /** ataseaza casei de licitatii clientul respectiv */
    public void ataseazaCasa(CasaDeLicitatii casa_de_licitatii) {
        this.casa_de_licitatii = casa_de_licitatii;
    }

    /** face parte din Design Pattern-ul Observator */
    public void notificaCasa(int stare) {
        casa_de_licitatii.actualizeaza(stare);
    }

    /** Getter */
    public int get_ID() {
        return this.id;
    }

    /** Getter */
    public int get_nrparticipari() {
        return this.nrParticipari;
    }

    /** Setter */
    public void set_nrparticipari(int x) {
        this.nrParticipari = x;
    }

    /** Getter */
    public int get_nrlicitatiicastigate() {
        return this.nrLicitatiiCastigate;
    }

    /** Setter */
    public void set_nrlicitatiicastigate(int x) {
        this.nrLicitatiiCastigate = x;
    }
}
