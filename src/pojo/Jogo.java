package pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jogo {
	public void ler_jogador (Jogador jogador) {	
		Scanner read = new Scanner(System.in);
		
		System.out.println("Digite o nome do jogador:");
		
		jogador.setNome(read.nextLine());
		
		jogador.setPontuacao(0);
		
		read.close();
	}
	
	public int[][] iniciar_jogo () {
		int pecas[][] = new int[4][4]; 
		int linha[] = new int[4];
		int coluna[] = new int[4]; 
		ArrayList<Integer> posicoes = new ArrayList<Integer>();
		ArrayList<Integer> valores = new ArrayList<Integer>();
		
        for (int i = 0; i < 4; i++) {
            posicoes.add(new Integer(i));
        }
        
        for (int i = 1; i < 9; i++) {
            valores.add(new Integer(i));
        }
        
        Collections.shuffle(posicoes);
        Collections.shuffle(valores);
        
        int a = -1;
        for (int i = 0; i < 4; i++) {
        	a++;
        	linha[a] = posicoes.get(i);
        }
		
        a = -1;
        for (int i = 0; i < 4; i++) {
        	a++;
        	coluna[a] = posicoes.get(i);
        }

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				pecas[linha[i]][coluna[j]] = valores.get(5);
			}
        }
		
		return pecas;
	}
	
	public void imprimir_pecas (int[][] pecas) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				//System.out.print("[" + i + "][" + j + "] = " + pecas[i][j] + "\t");
				System.out.print(pecas[i][j] + "\t");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}

   public void limpar_console () {
       for (int x = 0; x < 50; x++) {
               System.out.println();
       }
    }
	
	public void turno (int[][] pecas, Jogador jogador) {
		int linha_primeira, linha_segunda;
		int coluna_primeira, coluna_segunda;
		int pontuacao = jogador.getPontuacao();
		boolean turno = true;
		
		Scanner read = new Scanner(System.in);
		
		if (checar_tabuleiro(pecas)) {
			while (turno == true) {
				System.out.println("Informe a linha da primeira peça:");
				linha_primeira = read.nextInt();
				
				System.out.println("Informe a coluna da primeira peça:");
				coluna_primeira = read.nextInt();
				
				System.out.println("Informe a linha da segunda peça:");
				linha_segunda = read.nextInt();
				
				System.out.println("Informe a coluna da segunda peça:");
				coluna_segunda = read.nextInt();
				
				if (pecas[linha_primeira][coluna_primeira] != -1 && pecas[linha_segunda][coluna_segunda] != -1) {
					if (pecas[linha_primeira][coluna_primeira] == pecas[linha_segunda][coluna_segunda]) {
						jogador.setPontuacao(pontuacao++);
						pecas[linha_primeira][coluna_primeira] = -1;
						pecas[linha_segunda][coluna_segunda] = -1;
						turno = false;
						System.out.println("As peças são iguais, você pontuou! Vez do adversário.");
					}
					
					else {
						System.out.println("As peças são diferentes! Vez do adversário.");
						turno = false;
					}
				}
				
				else {
					if (pecas[linha_primeira][coluna_primeira] == -1) {
						System.out.println("A peça [" + linha_primeira + "][" + coluna_primeira + "] já foi desvirada, tente outra.");
					}
					
					else {
						System.out.println("A peça [" + linha_segunda + "][" + coluna_segunda + "] já foi desvirada, tente outra.");
					}
				}
			}
		}
				
		read.close();
	}
	
	public boolean checar_tabuleiro (int[][] pecas) {
		int contador = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (pecas[i][j] == -1) {
					contador++;
				}
			}
		}
		
		if (contador != 16) {
			return true;
		}
		
		else {
			return false;
		}
	}
	
	public void determinar_vencedor (Jogador jogador1, Jogador jogador2) {
		if (jogador1.getPontuacao() != jogador2.getPontuacao()) {
			if (jogador1.getPontuacao() != jogador2.getPontuacao()) {
				System.out.println("A partida acabou.\nO vencedor foi " + jogador1.getNome() + " com " + jogador1.getPontuacao() + " pontos.");
			}
			
			else {
				System.out.println("A partida acabou.\nO vencedor foi " + jogador2.getNome() + " com " + jogador2.getPontuacao() + " pontos.");
			}
		}
		
		else {
			System.out.println("A partida acabou.\nHouve um empate entre " + jogador1.getNome() + " e " + jogador2.getNome() + ".");
		}
	}
}
