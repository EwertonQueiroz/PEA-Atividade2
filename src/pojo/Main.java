package pojo;

public class Main {

	public static void main(String[] args) {
		
		Jogador jogador[] = new Jogador[2];
		Jogo jogo = new Jogo ();
		
		jogador[0] = new Jogador ();
		jogador[1] = new Jogador ();
		
//		jogo.ler_jogador (jogador[0]);
//		jogo.ler_jogador (jogador[1]);
		
		int[][] memoria = jogo.iniciar_jogo ();
		
//		jogo.imprimir_pecas (memoria);
		
		while (jogo.checar_tabuleiro (memoria)) {
			jogo.turno (memoria, jogador[0]);
//			jogo.turno (memoria, jogador[1]);
		}
		
		jogo.determinar_vencedor (jogador[0], jogador[1]);
		
	}

}
