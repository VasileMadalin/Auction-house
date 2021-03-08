/** Clasa care extinde Client */
public class PersoanaJuridica extends Client {
    Companie companie;

    /** Constructor */
    public PersoanaJuridica(int id, String nume, String adresa, int nrParticipari, int nrLicitatiiCastigate, Companie companie) {
        super(id, nume, adresa, nrParticipari, nrLicitatiiCastigate);
        this.companie = companie;
    }

    public enum Companie {
        SRL, SA
    }
}

