/** aceasta clasa implementeaza caracterisiticle unei Licitatii */
public class Licitatie {
    private final int id;
    private final int nrParticipanti;
    private final int idProdus;
    private final int nrPasiMaxim;

    /** Constructor */
    public Licitatie(int id, int nrParticipanti, int idProdus, int nrPasiMaxim) {
        this.id = id;
        this.nrParticipanti = nrParticipanti;
        this.idProdus = idProdus;
        this.nrPasiMaxim = nrPasiMaxim;
    }

    /** Getter */
    public int get_idProdus() {
        return this.idProdus;
    }

    /** Getter */
    public int get_ID() {
        return id;
    }

    /** Getter */
    public int get_nrParticipanti() {
        return this.nrParticipanti;
    }

    /** Getter */
    public int get_nrPasi() {
        return this.nrPasiMaxim;
    }
}