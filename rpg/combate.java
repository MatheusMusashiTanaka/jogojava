// Corrigir nomes dos "atributos"

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class combate {
    public static void iniciarcombate(Scanner scanner, classe player) {
        Itemdatab itemdatab = new Itemdatab();
        monstro inimigo;
        boolean check = false;
        int stun = 0;
        int desviar = 0;
        int atk = 0;
        boolean alive = true;

        while (!check) {

            if (player.info.NV < 20) {
                inimigo = new monstro(player.info.NV);
                System.out.println("\no bixo apareceu");
            } else {
                inimigo = new monstro(player.info.NV);
                System.out.println("\nCARALHO O KIRBY");
            }

            System.out.println("Combate Iniciado! \n");

            atk = 0;

            while (player.info.PVatual > 0 && inimigo.PV > 0) {
                turno resultado = turnoaliado(scanner, player, inimigo, stun, desviar, atk);
                atk = resultado.atk;
                stun = resultado.stun;
                desviar = resultado.desviar;
                player.info.PVatual = resultado.PVatualp;
                inimigo.PV = resultado.pvmonstro;

                if (inimigo.PV <= 0) {
                    System.out.println("Inimigo derrotado!\n");
                    player.info.NV++;
                    player.habilidades = melhorar(player);
                    if(player.inventario.size() < player.maxinv){
                        Item itemgerado = itemdatab.itemrandom();
                        System.out.println("parabens voce recebeu " + itemgerado.nome + " descricao: " + itemgerado.efeito);
                        player.inventario.add(itemgerado);
                    } else {
                        System.out.println("inventario cheio");
                    }
                    break;
                }

                resultado = turnoinimigo(player, inimigo, stun, desviar, atk);
                ;
                atk = resultado.atk;
                stun = resultado.stun;
                desviar = resultado.desviar;
                player.info.PVatual = resultado.PVatualp;
                inimigo.PV = resultado.pvmonstro;

                if (player.info.PV <= 0) {
                    System.out.println("GAME OVER");
                    alive = false;
                    break;
                }

                System.out.println("\nStatus do Jogador: " + player.info.PVatual + " PVs restantes");
                System.out.println("Status do Inimigo: " + inimigo.PV + " PVs restantes\n");
            }
            if (!alive) {
                break;
            }

            
        }
    }

    private static turno turnoaliado(Scanner scanner, classe player, monstro inimigo, int stun, int desviar, int atk) {
        String escolha = "";


        while (true) {
            escolha = rpg.Escolha(scanner, "\noque deseja fazer 1 para habilidade e 2 para item", new String[] { "1", "2" });
            if (escolha.equals("1") || escolha.equals("2")) {
                break; 
            } else {
                System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    
        switch (escolha) {
            case "1":
                return usarHabilidade(scanner, player, inimigo, stun, desviar, atk);
            case "2":
                return usarItem(scanner, player, inimigo, stun, desviar, atk);
            default:
                return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
        

        }

    }

    private static turno turnoinimigo(classe player, monstro inimigo, int stun, int desviar, int atk) {
        if (stun > 0) {
            System.out.println("o inimigo esta stunado");
            stun--;
        } else {
            if (desviar > 0) {
                System.out.println("voce desvia");
                desviar--;
            } else {
                if (atk != 3) {
                    player.info.PVatual -= inimigo.Dmg;
                    System.out.println("o inimigo lhe causou " + inimigo.Dmg + " de dano");
                    System.out.println("faltam " + (3 - atk) + " turnos para o inimigo usar o super");
                    atk++;
                } else {

                    player.info.PVatual -= inimigo.Dmg * 2;
                    System.out.println("o inimigo usou o especial e lhe causou " + inimigo.Dmg*2 + " de dano ");
                    atk = 0;

                }
            }
        }
        return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);

    }

    private static turno usarHabilidade(Scanner scanner, classe player, monstro inimigo, int stun, int desviar,int atk) {
        Random random = new Random();
        System.out.println("Escolha uma habilidade:");
        for (int i = 0; i < player.habilidades.size(); i++) {
            System.out.println(
                    (i + 1) + ". " + player.habilidades.get(i).nome + " (" + player.habilidades.get(i).tipo + ")" + " descricao: " + player.habilidades.get(i).efeito);
        }

        int escolhaHabilidade = scanner.nextInt() - 1;
        scanner.nextLine();
        if (escolhaHabilidade >= 0 && escolhaHabilidade < player.habilidades.size()) {
            habilidade habilidadeEscolhida = player.habilidades.get(escolhaHabilidade);





            switch (habilidadeEscolhida.tipo.toLowerCase()) {
                case "dano":
                    inimigo.PV -= habilidadeEscolhida.numero;
                    System.out.println("Você usou " + habilidadeEscolhida.nome + " e causou " + habilidadeEscolhida.numero + " de dano. \n");
                    return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);

                case "cura":

                    player.info.PVatual += habilidadeEscolhida.numero;
                    if (player.info.PVatual > player.info.PV) {
                        player.info.PVatual = player.info.PV;
                    }
                    System.out.println("\nVocê usou " + habilidadeEscolhida.nome + " e curou " + habilidadeEscolhida.numero + " PV \n");
                    return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);

                case "defesa":

                    int defesa = habilidadeEscolhida.numero;
                    System.out.println("\nVocê usou " + habilidadeEscolhida.nome + " ira reduzir " + defesa + " do dano do proximo ataque recebido.\n");

                    player.info.PVatual += defesa;
                    return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);

                case "especial":

                    if (habilidadeEscolhida.nome.equalsIgnoreCase("choque")) {
                        int chance = random.nextInt(4);
                            if (chance == 0){
                                System.out.println("\ninimigo atordoado\n");
                                stun += 2;
                                return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
                            } else {
                                System.out.println("\nvoce errou\n");
                                return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
                            }
                    } else if (habilidadeEscolhida.nome.equalsIgnoreCase("\nEsquiva\n")) {
                        int chance = random.nextInt(2);

                        if(chance==0){
                            System.out.println("\nVocê ira se esquivar do proximo ataque\n");
                            desviar++;
                            return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
                        } else {
                            System.out.println("\nVocê errou\n");
                            return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
                        }
                        

                    } else {
                        return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
                    }
            }
        }
        return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
    }

    private static turno usarItem(Scanner scanner, classe player, monstro inimigo , int stun, int desviar, int atk) {

    
        System.out.println("escolha algum item de seu inventario");
        for (int i = 0; i < player.inventario.size(); i++) {
            System.out.println((i + 1) + ". " + player.inventario.get(i).nome + " (" + player.inventario.get(i).tipo + ")" + " descricao: " + player.inventario.get(i).efeito);
        }
        int escolhaItem = scanner.nextInt() - 1;
        scanner.nextLine();
         if (escolhaItem >= 0 && escolhaItem < player.inventario.size()) {
            Item itemEscolhido = player.inventario.get(escolhaItem);

            switch (itemEscolhido.tipo.toLowerCase()) {
                case "cura":
                    player.info.PVatual += itemEscolhido.dado * player.info.NV;
                    if (player.info.PVatual > player.info.PV) {
                        player.info.PVatual = player.info.PV;
                    }
                    break;
                case "dano":
                    inimigo.PV -= itemEscolhido.dado * player.info.NV;
                    break;

                case "especial":
                    stun+= 2;
                    break;

    }
        player.inventario.remove(escolhaItem);
        return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
} else {
    return new turno(atk, stun, desviar, player.info.PVatual, inimigo.PV);
}
}

    private static ArrayList<habilidade> melhorar(classe player){

        ArrayList<habilidade> newhabilidades = new ArrayList<>();
        if (player.tipo.equalsIgnoreCase("guerreiro")){
            newhabilidades.add(new habilidade("Soco", "Da um total de (5*Nivel) de dano no inimigo", 5* player.info.NV,"dano"));
            newhabilidades.add(new habilidade("Bloqueio", "Reduz um total de (3*Nivel) do dano que você iria receber de um inimigo", 3*player.info.NV, "defesa"));
            return newhabilidades;
        } else if (player.tipo.equalsIgnoreCase("mago")){
            newhabilidades.add(new habilidade("Bola de fogo", "Da um total de (5*Nivel) de dano no inimigo", 5 * player.info.NV, "dano"));
            newhabilidades.add(new habilidade("Escudo magico", "Voce recebe (3*Nivel) de Escudo Magico que reduz o dano recebido", 3 * player.info.NV, "defesa"));
            newhabilidades.add(new habilidade("Choque", "25% de chance de stunnar o inimigo por 2 turno",1, "especial"));
            newhabilidades.add(new habilidade("Curar", "Voce recebe (2*Nivel) PVs", 2 * player.info.NV, "cura"));
            return newhabilidades;
        } else {
            newhabilidades.add(new habilidade("Atirar", "Da um total de (5*Nivel) de dano no inimigo", 5 * player.info.NV, "dano"));
            newhabilidades.add(new habilidade("Esquivar", "Você tem 50% de chance de esquivar do próximo ataque", 1, "especial"));
            newhabilidades.add(new habilidade("Parar e respirar", "receba (2*Nivel) de cura", 2*player.info.NV, "cura"));
            return newhabilidades;
        }
       
        
        

    }
 
    
}
