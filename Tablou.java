/** Clasa Tablou care mostemeste Produs */
public class Tablou extends Produs{
    private String numePictor;
    private Culori culori;

    /** Constructor */
    public Tablou(int id, String nume, double pretMinim, int an, String numePictor, Culori culori) {
        super(id, nume, pretMinim, an);
        this.numePictor = numePictor;
        this.culori = culori;
    }

    public static enum Culori {
        ulei, tempera, acrilic
    }
}
