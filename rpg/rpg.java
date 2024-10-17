
import java.util.Scanner; // Utilizado para fazer operações de entrada, como as escolhas que o jogador pode ter

/*

Código sobre um Jogo de RPG

Desenvolvido por Mateus Musashi Tanaka

 */

public class rpg {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner é inicializado com o nome 'scanner' com a função 'System.in' atribuída
        ComeçarAventura(scanner); // Chama a função Começar Aventura
        scanner.close();
    }

    private static void ComeçarAventura(Scanner scanner){ // Possui um texto introdutorio
        String escolha = Escolha(scanner, "--- Vamos iniciar sua aventura? ---\nDigite 1 para começar do começo ou 2 para escolher um nivel de dificuldade\n(AVISO: O nivel de dificuldade aumenta a vida que os inimigos tem e quanto dano eles dão)", new String[]{"1","2"});
        if(escolha.equals("1")){
            Criarpersonagem(scanner);
            
        }
    }


    private static void Criarpersonagem(Scanner scanner){
        String classescolhida = Escolha(scanner, "escolha sua classe, guerreiro, mago ou arqueiro", new String[]{"Guerreiro","Mago","Arqueiro"});
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        personagem player = new personagem(nome,1);

        classe CLplayer = new classe(classescolhida , player);

        System.out.println("voce criou " + player.nome + " que é um(a) " + CLplayer.tipo +"\n");

       
        combate.iniciarcombate(scanner,CLplayer);
    }

    public static String Escolha (Scanner scanner, String msg, String[] opcoes){
        String escolha;
        Boolean check = false;

        do {
            System.out.println(msg);
            escolha = scanner.nextLine();

            for(String opcao : opcoes){
                if(escolha.equalsIgnoreCase(opcao)){
                    check = true;
                    break;
                }
            }
            if(!check){
                System.out.println("Nao escolheu certo");
            }

        } while (check!=true);
        return escolha;
    }

    }

