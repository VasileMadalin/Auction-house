/** Clasa Mobila extinde Produs */
public class Mobila extends Produs {
    String tip;
    String material;

    public Mobila(int id, String nume, double pretMinim, int an, String tip, String material) {
        super(id, nume, pretMinim, an);
        this.tip = tip;
        this.material = material;
    }
}
