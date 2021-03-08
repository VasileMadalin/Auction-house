/** Clasa care extinde Client */
public class PersoanaFizica extends Client {
    String dataNastere;

    /** Constructor */
    public PersoanaFizica(int id, String nume, String adresa, int nrParticipari, int nrLicitatiiCastigate, String dataNastere) {
        super(id, nume, adresa, nrParticipari, nrLicitatiiCastigate);
        this.dataNastere = dataNastere;
    }
}
