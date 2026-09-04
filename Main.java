// nota: comandos já feito = lc, vdp, rda, ndp, lt, sdp, jdt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);
    public static final ArrayList<String> comandosExistentes = new ArrayList<> ();
    public static final Random random = new Random();
    public static final String nomeDoProjeto = "Terminal Comandos";
    public static final double versaoDoProjeto = 1.8;
    public static final ArrayList<String> registroDeAtualizacoesDoProjeto = new ArrayList<> ();
    public static final ArrayList<String> jogosDeTerminalNome = new ArrayList<> ();

    public static void main(String[] args) {
        comandosExistentes.addAll(Arrays.asList("lc - listar comandos", "lt - limpar terminal", "vdp - versão do projeto", "rda - registro de atualizações", "ndp - nome do projeto", "sdp - sair do projeto", "jdt - jogos de terminal", "tdbv - tela de boas vindas"));
        comandosExistentes.addAll(Arrays.asList());
        registroDeAtualizacoesDoProjeto.addAll(Arrays.asList("1.0 - criação do projeto", "1.2.5 - adição de comandos (lc, vdp, rda e ndp)", "1.5 - finalização do comando lt e adição do comando sdp", "1.6 - adição dos jogos de terminal comando jdt"));
        registroDeAtualizacoesDoProjeto.addAll(Arrays.asList("1.8 - Melhoria e conserto do comando jdt", "1.8.1 - adição do comando tdbv"));
        jogosDeTerminalNome.addAll(Arrays.asList("1 - Adivinhe o número"));
        telaDeBoasVindas();
        codigoPrincipal();
    }

    public static void codigoPrincipal() {
        while (true) {
            String comandoDoTerminal = scanner.nextLine().toLowerCase();
            switch (comandoDoTerminal) {
                case "lc":
                    listarComandos();
                    break;
                case "vdp":
                    print(3, "");
                    System.out.println(versaoDoProjeto);
                    break;
                case "rda":
                    print(3, "");
                    for (int i = 0; i < registroDeAtualizacoesDoProjeto.size(); i++) {
                        System.out.println(registroDeAtualizacoesDoProjeto.get(i));
                    }
                    break;
                case "ndp":
                    print(3, "");
                    System.out.println(nomeDoProjeto);
                    break;
                case "lt":
                    print(3, "");
                    limparTerminal();
                    break;
                case "sdp":
                    limparTerminal();
                    System.exit(0);
                    break;
                case "jdt":
                    jogosDeTerminal();
                    break;
                case "tdbv":
                    telaDeBoasVindas();
                    break;
                case "debugmode":
                    debugMode();
                    break;
                default:
                    if (comandoDoTerminal == "") {
                        print(3, "");
                        print(1, "Digite algo no campo!");
                        break;
                    } else {
                        print(3, "");
                        print(1, "Comando inválido! Tente novamente");
                        break;
                    }
            }
        }
    }

    public static void print(int tipo, String texto) {
        if (tipo == 1) {
            System.out.println(texto);
        } else if (tipo == 2) {
            System.err.println(texto);
        } else if (tipo == 3) {
            System.out.println("");
            System.out.println("=========================================");
            System.out.println("");
        } else {
            print(2, "Erro: A variável tipo não possue um valor válido");
        }
    }

    public static void limparTerminal() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void listarComandos() {
        print(3, "");
        for (int i = 0; i < comandosExistentes.size(); i++) {
            System.out.println(comandosExistentes.get(i));
        }
    }

    public static void jogosDeTerminal() {
        limparTerminal();
        print(3, "");
        for (int i = 0; i < jogosDeTerminalNome.size(); i++) {
            System.out.println(jogosDeTerminalNome.get(i));
        }
        print(3, "");
        print(1, "Para sair do jogo é preciso precionar CTRL + C, isso fechará o programa");
        print(1, "Para sair desta tela, digite algo que não seja um número");
        print(1, "Escolha um jogo digitando o seu número e apertando ENTER:");
        String qualJogoQuer = scanner.nextLine().toLowerCase();
        switch (qualJogoQuer) {
            case "1":
                adivinheONumero();
            default:
                print(3, "");
                print(1, "Jogo não encontrado");
                limparTerminal();
                listarComandos();
                codigoPrincipal();
                break;  
            }
        }

    public static void adivinheONumero() {
        int tentativas = 5;
        limparTerminal();
        print(3, "");
        print(1, "Bem-vindo à adivinhe o número");
        System.out.println("Você tem " + tentativas + " tentativas restantes");
        while (tentativas > 0) {
            int numeroAleatorio = random.nextInt(100) + 1;
            print(1, "Escolha um número aleátorio entre 1 e 100");
            int numeroEscolhido = scanner.nextInt();
            if (numeroEscolhido >= 1 && numeroEscolhido <= 100) {
                if (numeroEscolhido == numeroAleatorio) {
                    print(3, "");
                    print(1, "Você venceu!");
                    codigoPrincipal();
                } else {
                    tentativas -= 1;
                    print(3, "");
                    if (numeroAleatorio > numeroEscolhido) {
                        System.out.println("Você tem " + tentativas + " tentativas restantes");
                        System.out.println("Dica! O número é maior do que você escolheu");
                    } else {
                        System.out.println("Você tem " + tentativas + " restantes");
                        System.out.println("Dica! O número é menor do que você escolheu");
                    }
                }
            } else if (numeroEscolhido > 100) {
                print(3, "");
                print(1, "Número muito grande!");
            } else {
                print(3, "");
                print(1, "Você digitou um número inválido!");
            }
        }
        print(3, "");
        print(1, "Você perdeu!");
        scanner.nextLine();
        codigoPrincipal();
    }

    public static void telaDeBoasVindas() {
        limparTerminal();
        print(3, "");
        System.out.println("Bem-vindo ao " + nomeDoProjeto + " Aproveitem o terminal");
        print(1, "Digite lc para listar os comandos");
        print(3, "");
    }
    
    public static void debugMode() {
        limparTerminal();
        System.out.println(nomeDoProjeto);
        System.out.println(versaoDoProjeto);
        System.out.println(comandosExistentes);
        System.out.println(registroDeAtualizacoesDoProjeto);
        System.out.println(jogosDeTerminalNome);
        System.exit(0);
    }

}