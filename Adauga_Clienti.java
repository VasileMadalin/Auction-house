import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/** aceasta clasa face parte din design pattern-ul Command
 * si are rolul de a introduce in memorie clientii */
public class Adauga_Clienti {
    public void executa(CasaDeLicitatii casa_de_licitatii) {
        try {
            System.out.println("Introduceti numele fisierului care contine clientii:");
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
                String data_nasterii;
                String companie;
                String nume = parts[1];
                String adresa = parts[2];
                Client a = null;
                if (tip.equals("Persoana_Fizica")) {
                    data_nasterii = parts[3];
                    a = new PersoanaFizica(k, nume, adresa,0,0, data_nasterii);
                }
                if (tip.equals("Persoana_Juridica")) {
                    companie = parts[3];
                    if (companie.equals("SA"))
                        a = new PersoanaJuridica(k, nume, adresa,0,0,PersoanaJuridica.Companie.SA);
                    else
                        a = new PersoanaJuridica(k, nume, adresa,0,0,PersoanaJuridica.Companie.SRL);
                }
                casa_de_licitatii.set_Clienti(a);
                if (a != null) {
                    for (int i = 4; i < parts.length; i = i + 2) {
                        int stare = Integer.parseInt(parts[i]);
                        int pret_maxim = Integer.parseInt(parts[i + 1]);
                        a.seteazaStare(stare, pret_maxim);
                    }
                }
                k = k + 1;
            }
        }
        catch(IOException ex) {
            System.out.println("Exceptie gasita");
        }
    }
}