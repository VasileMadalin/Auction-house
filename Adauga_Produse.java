import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** aceasta clasa face parte din design pattern-ul Command
 * si are rolul de a introduce in memorie produse */
public class Adauga_Produse {
    public void executa(CasaDeLicitatii casa_de_licitatii) {
        try {
            System.out.println("Introduceti numele fisierului care contine produsele:");
            Scanner myObj = new Scanner(System.in);
            String path = myObj.nextLine();
            File file = new File(path);
            Scanner in = new Scanner(file);
            String scan;
            int k = 1;
            while(in.hasNextLine()) {
                scan = in.nextLine();
                String[] parts = scan.split(" ");
                String tip = parts[0];
                String nume = parts[1];
                int pret = Integer.parseInt(parts[2]);
                int an = Integer.parseInt(parts[3]);
                Produs b = null;
                if (tip.equals("Bijuterie")) {
                    String material = parts[4];
                    String piatra_pretioasa = parts[5];
                    boolean piatra = false;
                    if (piatra_pretioasa.equals("Pretioasa"))
                        piatra = true;
                    b = new Bijuterie(k, nume,pret, an, material, piatra);
                }
                if (tip.equals("Tablou")) {
                    String pictor = parts[4];
                    String culori = parts[5];
                    b = new Tablou(k, nume,pret, an, pictor, Tablou.Culori.valueOf(culori));
                }
                if (tip.equals("Mobila")) {
                    String tip_mobila = parts[4];
                    String material = parts[5];
                    b = new Mobila(k, nume,pret, an, tip_mobila, material);
                }
                casa_de_licitatii.set_Produse(b);
                k = k + 1;
            }
        }
        catch(IOException ex) {
            System.out.println("Exceptie gasita");
        }
    }
}