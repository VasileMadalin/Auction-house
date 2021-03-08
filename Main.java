/** Design Pattern Command */
public class Main {
    public static void main(String[] args) {
        MyList<Angajat> Lista_de_Angajati = new MyList<>();
        CasaDeLicitatii casa_de_licitatii = CasaDeLicitatii.Instanta();

        Adauga_Produse citeste_produse = new Adauga_Produse();
        citeste_produse.executa(casa_de_licitatii);

        Adauga_Clienti citeste_clienti = new Adauga_Clienti();
        citeste_clienti.executa(casa_de_licitatii);

        Adauga_Angajati citeste_angajati = new Adauga_Angajati();
        citeste_angajati.executa(casa_de_licitatii, Lista_de_Angajati);

        Adauga_Licitatii ruleaza_licitatii = new Adauga_Licitatii();
        ruleaza_licitatii.executa(casa_de_licitatii, Lista_de_Angajati);
    }
}