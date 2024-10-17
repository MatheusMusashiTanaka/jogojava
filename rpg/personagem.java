// Personagem que possui nome, Pontos de Vida (PV) e Nível (NV)

public class personagem {
    public String nome;
    public int PV = 0;
    public int NV;
    public int PVatual = 0;

    public personagem(String nome, int NV) {
        this.nome = nome;
        this.NV = NV;
    }
}