package pt.c40task.l05wumpus;

import java.util.Scanner;

public class AppWumpus {

   public static void main(String[] args) {
      AppWumpus.executaJogo(
            (args.length > 0) ? args[0] : null,
            (args.length > 1) ? args[1] : null,
            (args.length > 2) ? args[2] : null);
   }
   
   public static void executaJogo(String arquivoCaverna, String arquivoSaida,
                                  String arquivoMovimentos) {
      Toolkit tk = Toolkit.start(arquivoCaverna, arquivoSaida, arquivoMovimentos);
      
      // Criacao da caverna
      String cave[][] = tk.retrieveCave();
      MontadorCaverna montador = new MontadorCaverna();
      ControleJogo controle = new ControleJogo();
      Caverna caverna = montador.montarCaverna(cave,controle);
      
      // Controle do Jogo
      if (montador.getSucesso()) {
         String resposta;
         if (arquivoMovimentos != null) {  // Modo Arquivo
            String movements = tk.retrieveMovements();
            String[] movementsArray = movements.split("");

            tk.writeBoard(caverna.cavernaChar(), controle.getPontuacao(), controle.getStatus());  // guarda a caverna inicial

            for (int i = 0; i < movementsArray.length && !movementsArray[i].equals("q") && controle.getStatus() == 'P'; i++) {
               resposta = controle.executarAcao(movementsArray[i]);
               if (resposta.equals("Erro: Jogada invalida!") || resposta.equals("Erro: Nao ha ouro nessa sala!"))
                  tk.writeError(resposta);
               else if (!resposta.equals("O heroi preparou a flecha."))
                  tk.writeBoard(caverna.cavernaChar(), controle.getPontuacao(), controle.getStatus());
            }
  
        } else {  // Modo Interativo
            Scanner keyboard = new Scanner(System.in);
  
            System.out.print("Entre o nome do jogador: ");
            String nome = keyboard.nextLine();
            controle.setJogador(nome);
   
            System.out.println("=====");
            System.out.println(caverna);
            System.out.println(String.format("Player: %s\nScore: %d", controle.getJogador(), controle.getPontuacao()));
   
            while (controle.getStatus() == 'P') {
               System.out.print("Digite o seu comando: ");
               String command = keyboard.nextLine();
   
               if (command.equals("q")) {
                  System.out.println("Volte sempre!");
                  break;
               }
   
               resposta = controle.executarAcao(command);
               System.out.println(String.format("=====\n%s\nPlayer: %s\nScore: %d\n%s",
                                          caverna, controle.getJogador(), controle.getPontuacao(), resposta));

            }
            if (controle.getStatus() == 'W') 
               System.out.println("Voce ganhou =D !!!");
            else 
               System.out.println("Voce perdeu =( ...");
            
            keyboard.close();
        }
      } else {
         String erro = montador.getErrorMessage();
         System.out.println(erro);
         tk.writeError(erro);
      }
      
      tk.stop();
   }

}
