/** aceasta clasa implementeaza caracterisiticle unui Produs */
public class Produs {
    private int id;
    private String nume;
    private double pretVanzare;
    private double pretMinim;
    private int an;

    /** Constructor */
    public Produs(int id, String nume, double pretMinim, int an) {
        this.id = id;
        this.nume = nume;
        this.pretMinim = pretMinim;
        this.an = an;
    }

    /** Setter */
    public void set_pretVanzare(double max) {
        this.pretVanzare = max;
    }

    /** Getter */
    public int getId() {
        return id;
    }

    /** Getter */
    public String getNume() {
        return nume;
    }

    /** Getter */
    public double getPretVanzare() {
        return pretVanzare;
    }

    /** Getter */
    public double getPretMinim() {
        return pretMinim;
    }

    /** Getter */
    public int getAn() {
        return an;
    }
}
