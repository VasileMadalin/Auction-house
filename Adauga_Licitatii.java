import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** aceasta clasa face parte din design pattern-ul Command
 * si are rolul de a introduce in memorie mai multe licitatii si de a le rula */

public class Adauga_Licitatii {

    public void executa(CasaDeLicitatii casa_de_licitatii, MyList<Angajat> Lista_de_Angajati) {
        try {
            System.out.println("Introduceti numele fisierului care contine licitatiile:");
            Scanner myObj = new Scanner(System.in);
            String path = myObj.nextLine();
            File file = new File(path);
            Scanner in = new Scanner(file);
            String scan;
            int k = 1;
            while(in.hasNextLine()) {
                scan = in.nextLine();
                String[] parts = scan.split(" ");
                int nrParticipanti = Integer.parseInt(parts[0]);
                int IdProdus = Integer.parseInt(parts[1]);
                int NrPasiMaxim = Integer.parseInt(parts[2]);
                Licitatie L = new Licitatie(k, nrParticipanti, IdProdus, NrPasiMaxim);
                casa_de_licitatii.set_Licitatii(L);
                Ruleaza_Licitatie sarcina = new Ruleaza_Licitatie(L, casa_de_licitatii, Lista_de_Angajati);
                Thread t = new Thread(sarcina);
                t.start();
                k = k + 1;
            }
        }
        catch(IOException ex) {
            System.out.println("Exceptie gasita");
        }
    }
}
