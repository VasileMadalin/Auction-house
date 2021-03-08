//scot clientul din lista
//poate adaug altii
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/** aceasta clasa implementeaza Runnable, astfel ruland in paralel mai multe licitatii */
public class Ruleaza_Licitatie implements Runnable {
    Licitatie L;
    CasaDeLicitatii casa_de_licitatii;
    MyList<Angajat> Lista_de_Angajati;

    /** Constructor */
    public Ruleaza_Licitatie(Licitatie l, CasaDeLicitatii casa_de_licitatii, MyList<Angajat> lista_de_Angajati) {
        this.L = l;
        this.casa_de_licitatii = casa_de_licitatii;
        this.Lista_de_Angajati = lista_de_Angajati;
    }

    /** aceasta metoda ruleaza fiecare licitatie */
    public void run() {
        try {
            try (FileWriter fw = new FileWriter("iesire.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                Thread.sleep((L.get_ID() - 1) * 1000);
                DecimalFormat df2 = new DecimalFormat("#.##");
                int index_produs_cautat = L.get_idProdus();
                //se cauta in casa clientii care vor produsul corespunzator
                // licitatiei si cei care vor sunt repartizati automat unui broker
                int nr = 0;
                for (int i = 0; i < casa_de_licitatii.get_Clienti().size(); i++) {
                    Client a = (Client) casa_de_licitatii.get_Clienti().get(i);
                    if (((Client) casa_de_licitatii.get_Clienti().get(i)).Stare(index_produs_cautat) != 0) {
                        casa_de_licitatii.actualizeaza_client_participare(i);
                        nr = nr + 1;
                        Angajat w = casa_de_licitatii.random(Lista_de_Angajati);
                        ((Broker) w).set_Clienti(a);
                        if (nr == L.get_nrParticipanti())
                            break;
                    }
                }
                //se ruleaza fiecare pas al licitatiei
                Map<Integer, Double> Sume_oferite_de_clienti = new LinkedHashMap<>();
                double max = (casa_de_licitatii.get_by_id(index_produs_cautat)).getPretMinim();
                int[] index = {-1};
                for (int j = 1; j <= L.get_nrPasi(); j++) {
                    for (int i = 0; i < Lista_de_Angajati.size(); i++) {
                        if (Lista_de_Angajati.get(i) instanceof Broker) {
                            MyList<Client> Lista_de_Clienti_de_intrebat = ((Broker) Lista_de_Angajati.get(i)).get_Clienti();
                            if (Lista_de_Clienti_de_intrebat != null) {
                                for (int k = 0; k < Lista_de_Clienti_de_intrebat.size(); k++) {
                                    Client bb = (Client) Lista_de_Clienti_de_intrebat.get(k);
                                    double pret_maxim = bb.Stare(index_produs_cautat);
                                    if (pret_maxim != 0) {
                                        Random rand1 = new Random(55);
                                        double rangeMin = (casa_de_licitatii.get_by_id(index_produs_cautat)).getPretMinim();
                                        Random rand = new Random(55);
                                        int rand_int1 = rand.nextInt(2);
                                        double suma_oferita_de_client;
                                        if (rand_int1 == 1) {
                                            suma_oferita_de_client = rangeMin + (pret_maxim - max) * rand1.nextDouble();
                                        } else {
                                            suma_oferita_de_client = -1;
                                        }
                                        Sume_oferite_de_clienti.put(bb.get_ID(), suma_oferita_de_client);
                                    }
                                }
                            }
                        }
                    }
                    max = casa_de_licitatii.oferta_maxima(Sume_oferite_de_clienti, index);
                }
                if (max != -1) {
                    double comision_broker = casa_de_licitatii.get_comision(Lista_de_Angajati, index[0], max);
                    casa_de_licitatii.actualizeaza_castig(L.get_idProdus(), max);
                    casa_de_licitatii.elimina_produs(L.get_idProdus());
                    casa_de_licitatii.actualizeaza_client_castig(index[0] - 1);
                    out.println("Clientul cu indexul " + index[0] + " a cumparat cu " + df2.format(max) + " lei produsul cu index-ul " + L.get_idProdus() + " Brokerul a obtiut " + df2.format(comision_broker));
                    casa_de_licitatii.elimina_licitatie(L.get_ID());
                }
                else {
                    out.println("Produsul nu a putut fi vandut");
                }
            } catch(IOException ex) {
                System.out.println("Exceptie gasita");
            }
        } catch (InterruptedException ex) {
            System.out.println("Exceptie gasita");
        }
    }
}
