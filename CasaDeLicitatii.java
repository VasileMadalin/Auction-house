import java.util.Map;
import java.util.Random;

/** aceasta clasa implementeaza caracterisiticile unei case de licitatii,
 * se foloseste Design Pattern-ul Singleton */
public class CasaDeLicitatii {
    MyList<Produs> Lista_de_Produse;
    MyList<Client> Lista_de_Clienti;
    MyList<Licitatie> Licitatii_active;
    private static CasaDeLicitatii instantaUnica;

    /** metoda care este necesara Design Pattern-ului Singleton */
    public static CasaDeLicitatii Instanta() {
        if (instantaUnica == null)
            instantaUnica = new CasaDeLicitatii();
        return instantaUnica;
    }
    //Observator
    /** metoda care este necesara Design Pattern-ului Observator */
    public void set_Clienti(Client Client) {
        if (this.Lista_de_Clienti == null) {
            this.Lista_de_Clienti = new MyList<>();
        }
        //fiecarui client i se ataseaza aceasta casa de licitatii
        Client.ataseazaCasa(this);
        this.Lista_de_Clienti.add(Client);
    }

    public void set_Licitatii(Licitatie Licitatie) {
        if (this.Licitatii_active == null) {
            this.Licitatii_active = new MyList<>();
        }
        //fiecarui client i se ataseaza aceasta casa de licitatii
        this.Licitatii_active.add(Licitatie);
    }

    /** Notifica cand un client solicita un produs */
    public void actualizeaza(int stare) {
        System.out.println("Un client doreste sa cumpere produsul " + stare);
    }

    /** Aceasta metoda creeaza un angajat al casei de licitatii */
    public Angajat creeazaAngajat(String type) {
        switch (type) {
            case "Administrator":
                return new Administrator();
            case "Broker":
                return new Broker();
            default:
                return null;
        }
    }

    /** Setter */
    public void set_Produse(Produs Produs) {
        if (this.Lista_de_Produse == null) {
            this.Lista_de_Produse = new MyList<>();
        }
        this.Lista_de_Produse.add(Produs);
    }

    /** Getter */
    public MyList<Client> get_Clienti() {
        return this.Lista_de_Clienti;
    }

    /** metoda care distribuie in mod aleator un broker unui client */
    public Angajat random(MyList<Angajat> Lista_de_Angajati) {
        Random rand = new Random(55);
        int length = Lista_de_Angajati.size();
        int index = rand.nextInt(length);
        while (Lista_de_Angajati.get(index) instanceof Administrator) {
            index = rand.nextInt(length);
        }
        return (Angajat) Lista_de_Angajati.get(index);
    }

    /** aceasta metoda calculeaza pretul maxim oferit la un pas al licitatie */
    public double oferta_maxima(Map<Integer, Double> Sume_oferite_de_clienti, int[] index) {
        double max = -1;
        for (Map.Entry<Integer, Double> entry : Sume_oferite_de_clienti.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                index[0] = entry.getKey();
            }
        }
        return max;
    }

    /** aceasta metoda actualizeaza numarul de participarii ale unui client */
    public void actualizeaza_client_participare(int id) {
        int x = ((Client) Lista_de_Clienti.get(id)).get_nrparticipari() + 1;
        ((Client) Lista_de_Clienti.get(id)).set_nrparticipari(x);
    }

    /** aceasta metoda actualizeaza de cate ori un client a castigat */
    public void actualizeaza_client_castig(int index) {
        int x = ((Client) Lista_de_Clienti.get(index)).get_nrlicitatiicastigate() + 1 ;
        ((Client) Lista_de_Clienti.get(index)).set_nrlicitatiicastigate(x);
    }

    /** aceasta metoda elimina un produs din lista dupa ce a fost cumparat de un client */
    public void elimina_produs(int id) {
        for(int i = 0; i < Lista_de_Produse.size(); i++) {
            if (  ((Produs) Lista_de_Produse.get(i)).getId() == id) {
                Lista_de_Produse.remove(i);
                break;
            }
        }
    }

    /** aceasta metoda actualizeaza pretul de vanzare al unui produs dupa ce a fost cumparat de un client */
    public void actualizeaza_castig(int id, double max) {
        for(int i = 0; i < Lista_de_Produse.size(); i++) {
            if (  ((Produs) Lista_de_Produse.get(i)).getId() == id) {
                ((Produs) Lista_de_Produse.get(i)).set_pretVanzare(max);
                break;
            }
        }
    }

    /** metoda care returneaza un produs dupa index */
    public Produs get_by_id(int id) {
        int i;
        for(i = 0; i < Lista_de_Produse.size(); i++) {
            if ( ((Produs) Lista_de_Produse.get(i)).getId() == id) {
                break;
            }
        }
        return (Produs) Lista_de_Produse.get(i);
    }

    /** aceasta metoda elimina o licitatie din lista dupa ce a fost declarat un castigator */
    public void elimina_licitatie(int id) {
        for(int i = 0; i < Licitatii_active.size(); i++) {
            if (  ((Licitatie) Licitatii_active.get(i)).get_ID() == id) {
                Licitatii_active.remove(i);
                break;
            }
        }
    }

    /** aceasta metoda calculeaza comisionul rezultat unui broker dintr-o licitatie */
    public double get_comision(MyList<Angajat> Lista_de_Angajati, int index, double castig) {
        double comision = 0;
        for (int i = 0; i < Lista_de_Angajati.size(); i++) {
            if (Lista_de_Angajati.get(i) instanceof Broker) {
                MyList<Client> Lista_de_Clienti_de_intrebat = ((Broker) Lista_de_Angajati.get(i)).get_Clienti();
                if (Lista_de_Clienti_de_intrebat != null) {
                    for (int k = 0; k < Lista_de_Clienti_de_intrebat.size(); k++) {
                        Client bb = (Client) Lista_de_Clienti_de_intrebat.get(k);
                        if (bb.get_ID() == index) {
                            if (bb instanceof PersoanaFizica) {
                                if (bb.get_nrparticipari() < 5) comision = 0.2 * castig;
                                if (bb.get_nrparticipari() > 5) comision = 0.15 * castig;
                            }
                            if (bb instanceof PersoanaJuridica) {
                                if (bb.get_nrparticipari() < 5) comision = 0.25 * castig;
                                if (bb.get_nrparticipari() > 5) comision = 0.1 * castig;
                            }
                        }
                    }
                }
            }
        }
        return comision;
    }
}