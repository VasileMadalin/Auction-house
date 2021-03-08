/** Clasa Bijuterie care mosteneste Clasa Produs */
public class Bijuterie extends Produs {
    String material;
    boolean piatraPretioasa;

    /** Constructor */
    public Bijuterie(int id, String nume, double pretMinim, int an, String material, boolean piatraPretioasa) {
        super(id, nume, pretMinim, an);
        this.material = material;
        this.piatraPretioasa = piatraPretioasa;
    }
}
