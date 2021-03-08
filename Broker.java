public class Broker extends Angajat {
    private MyList<Client> Lista_de_Clienti;

    public Broker() {
    }

    public void set_Clienti(Client Client) {
        if (this.Lista_de_Clienti == null) {
            this.Lista_de_Clienti = new MyList<Client>();
        }
        this.Lista_de_Clienti.add(Client);
    }

    public MyList<Client> get_Clienti() {
        return Lista_de_Clienti;
    }
}
