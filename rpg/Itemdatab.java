import java.util.ArrayList;
import java.util.Random;
public class Itemdatab {
    private ArrayList<Item> lista;
    private Random random;


    public Itemdatab(){
        this.lista = new ArrayList<>();
        this.random = new Random();
        createdb();
    }
    
    private void createdb() {
        lista.add(new Item("pocao de vida", "cura", "cura por 5 * NV", 5));
        lista.add(new Item("bomba", "dano", "efeito: de dano igual ao seu nivel * 5", 5));
        lista.add(new Item("granada de fumaça", "especial", "efeito: inimigo perde turno", 0));
        lista.add(new Item("elixir", "cura", "cura completamente", 100));
       
    }

    public ArrayList<Item> getItens() {
        return lista;
    }


    public Item itemrandom(){
        int index = random.nextInt(lista.size());
        return lista.get(index);
    }
}
