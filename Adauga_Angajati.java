import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** aceasta clasa face parte din design pattern-ul Command
 * si are rolul de a introduce in memorie angajatii */
public class Adauga_Angajati {
    public void executa(CasaDeLicitatii casa_de_licitatii, MyList<Angajat> Lista_de_Angajati) {
        try {
            System.out.println("Introduceti numele fisierului care contine angajatii:");
            Scanner myObj = new Scanner(System.in);
            String path = myObj.nextLine();
            File file = new File(path);
            Scanner in = new Scanner(file);
            String scan;
            while(in.hasNextLine()) {
                scan = in.nextLine();
                String[] parts = scan.split(" ");
                String functie = parts[0];
                Angajat x = casa_de_licitatii.creeazaAngajat(functie);
                Lista_de_Angajati.add(x);
            }
        }
        catch(IOException ex) {
            System.out.println("Exceptie gasita");
        }
    }
}
