// Classe item que possui nome, tipo e efeito

public class Item {
    public String nome;
    public String tipo;
    public String efeito;
    public int dado;
    public Item(String nome, String tipo, String efeito , int dado) {
        this.nome = nome;
        this.tipo = tipo;
        this.efeito = efeito;
        this.dado = dado;
    }


    public int calcularCura(int nivelPersonagem) {
        return dado * nivelPersonagem; 
    } 
}
