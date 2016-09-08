package pojo;

import javax.swing.Timer;

public class Main {

	public static void main(String[] args) {
		
		Jogador jogador[] = new Jogador[2];
		Jogo jogo = new Jogo();
//		Inserir contador
//		Timer contador = new Timer();
		
		jogador[0] = new Jogador();
		jogador[1] = new Jogador();
		
		jogo.ler_jogador(jogador[0]);
		jogo.ler_jogador(jogador[1]);
		
		int[][] memoria = jogo.iniciar_jogo();
//-----------------------------     AQUI     -----------------------------//
//		contador.start();
//		contador.wait(1.5);
		jogo.imprimir_pecas(memoria);
		
		while (jogo.checar_tabuleiro(memoria)) {
			jogo.turno(memoria, jogador[0]);
			jogo.turno(memoria, jogador[1]);
		}
		
		jogo.determinar_vencedor(jogador[0], jogador[1]);
		
	}

}
