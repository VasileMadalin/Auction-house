/** Clasa care implementeaza o list */
public class MyList<E>{
    private E[] tablou;

    public void add(E x) {
        if (tablou == null) {
            tablou = (E[]) new Object[1];
            tablou[0] = x;
        }
        else {
            int length = tablou.length;
            E[] copie = (E[]) new Object[length + 1];
            System.arraycopy(tablou, 0, copie, 0, tablou.length);
            copie[length] = x;
            tablou = copie;
        }
    }
    public Object get(int i) {
        return tablou[i];
    }
    public void remove(int index) {
        int length = tablou.length;
        E[] copie = (E[]) new Object[length -1];
        int j = 0;
        for(int i = 0; i < tablou.length; i++) {
            if (i != index) {
                copie[j] = tablou[i];
                j = j + 1;
            }
        }
        tablou = copie;
    }
    public int size() {
        return tablou.length;
    }
}