// Classe Classe (no sentido de tipo de personagem) que possui os principais personagens e suas respectivas características

import java.util.ArrayList; // Utilizado para fazer uma lista de habilidades e a lista de inventário

public class classe {
    // Possui as habilidades, os itens (que são armazenados no inventário), o tipo de personagem e o maximo de itens que podem ter em seu inventário (maxinv)
    public personagem info;
    public ArrayList<habilidade> habilidades;
    public ArrayList<Item> inventario;
    public String tipo;
    public int maxinv;

    public classe(String nome , personagem information) {
        this.habilidades = new ArrayList<>();
        this.inventario = new ArrayList<>();
        this.info = information;
        if (nome.equalsIgnoreCase("Guerreiro")) { // Classe Guerreiro, possui 2 habilidades, 2 de inventário máximo e possui 4x mais vida
            this.tipo = nome;
            this.habilidades.add(new habilidade("Soco", "Da um total de (5*Nivel) de dano no inimigo", 5*info.NV,"dano"));
            this.habilidades.add(new habilidade("Bloqueio", "Reduz um total de (3*Nivel) do dano que você iria receber de um inimigo", 3*info.NV, "defesa"));
            this.maxinv = 2;
            this.info.PV = (10 * 4);
            this.info.PVatual = (10 * 4);
        } else if (nome.equalsIgnoreCase("Mago")) { // Classe Mago, possui 4 habilidades, 4 de inventário máximo e possui 2x mais vida
            this.tipo = nome;
            this.habilidades.add(new habilidade("Bola de fogo", "Da um total de (5*Nivel) de dano no inimigo", 5*info.NV, "dano"));
            this.habilidades.add(new habilidade("Escudo magico", "Voce recebe (3*Nivel) de Escudo Magico que reduz o dano recebido", 3*info.NV, "defesa"));
            this.habilidades.add(new habilidade("Choque", "25% de chance de stunnar o inimigo por 2 turno",1, "especial"));
            this.habilidades.add(new habilidade("Curar", "Voce recebe (2*Nivel) PVs", 2*info.NV, "cura"));
            this.maxinv = 4;
            this.info.PV = (10 * 2);
            this.info.PVatual = (10* 2);
        } else if (nome.equalsIgnoreCase("Arqueiro")) { // Classe Arqueiro, possui 3 habilidades, 4 de inventário máximo e possui 3x mais vida
            this.tipo = nome;
            this.habilidades.add(new habilidade("Atirar", "Da um total de (5*Nivel) de dano no inimigo", 5*info.NV, "dano"));
            this.habilidades.add(new habilidade("Esquivar", "Você tem 50% de chance de esquivar do proximo ataque",0, "especial"));
            this.habilidades.add(new habilidade("Parar e respirar", "receba (2*Nivel) de cura", 2*info.NV, "cura"));
            this.maxinv = 4;
            this.info.PVatual = (10 * 3);;
        } else {
            System.out.println("A Classe selecionada não existe");
        }
    }
}