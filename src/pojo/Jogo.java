package pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jogo {	
	public void ler_jogador (Jogador jogador) {	
		String nome = null;
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Digite o nome do jogador:");
		
		try {
			nome = read.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		jogador.setNome(nome);
		
		jogador.setPontuacao(0);
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
        
        a = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 2; j++) {
				pecas[linha[i]][coluna[j]] = valores.get(a);
				a++;
			}
        }
		
		Collections.shuffle(valores);
		
		a = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 4; j++) {
				pecas[linha[i]][coluna[j]] = valores.get(a);
				a++;
			}
        }
		
		return pecas;
	}
	
	public void imprimir_pecas (int pecas[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(pecas[i][j] + "\t");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}
	
	private int jogada (int pecas[][], int pontuacao) {
		int linha_primeira = 0, linha_segunda = 0;
		int coluna_primeira = 0, coluna_segunda = 0;
		
		boolean turno = true;
		boolean valor_valido = false;
		
		Scanner read = new Scanner(System.in);
		
		if (checar_tabuleiro(pecas)) {
			while (turno == true) {
				while (valor_valido == false) {
					System.out.println("Informe a linha da primeira peça:");
					linha_primeira = read.nextInt();
					
					System.out.println("Informe a coluna da primeira peça:");
					coluna_primeira = read.nextInt();
					
					valor_valido = validar_jogada(linha_primeira, coluna_primeira);
				}
				
				valor_valido = false;
				
				while (valor_valido == false) {
					System.out.println("Informe a linha da segunda peça:");
					linha_segunda = read.nextInt();
					
					System.out.println("Informe a coluna da segunda peça:");
					coluna_segunda = read.nextInt();
					
					valor_valido = validar_jogada(linha_segunda, coluna_segunda);
				}
				
				linha_primeira -= 1;
				coluna_primeira -= 1;
				
				linha_segunda -= 1;
				coluna_segunda -= 1;
		
				if (pecas[linha_primeira][coluna_primeira] != -1 && pecas[linha_segunda][coluna_segunda] != -1) {
					if (pecas[linha_primeira][coluna_primeira] == pecas[linha_segunda][coluna_segunda]) {
						pontuacao += 1;
						pecas[linha_primeira][coluna_primeira] = -1;
						pecas[linha_segunda][coluna_segunda] = -1;
						turno = false;
						System.out.println("As peças são iguais, você pontuou! Vez do adversário.\n");
					}
					
					else {
						System.out.println("As peças são diferentes! Vez do adversário.\n");
						turno = false;
					}
				}
				
				else {
					if (pecas[linha_primeira][coluna_primeira] == -1) {
						System.out.println("A peça [" + linha_primeira + "][" + coluna_primeira + "] já foi desvirada, tente outra.\n");
					}
					
					else {
						System.out.println("A peça [" + linha_segunda + "][" + coluna_segunda + "] já foi desvirada, tente outra.\n");
					}
				}
			}
		}
				
		read.close();
		
		return pontuacao;
	}
	
	public boolean validar_jogada(int linha, int coluna) {
		if (linha == 0 || linha > 4) {
			System.out.println("Valor inválido para linha.\nUtilize valores entre 1 e 4.\n");
			return false;
		}
		
		if (coluna == 0 || coluna > 4) {
			System.out.println("Valor inválido para coluna.\nUtilize valores entre 1 e 4.\n");
			return false;
		}
		
		return true;
	}
	
	public void turno (int pecas[][], Jogador jogador) {
		int pontuacao = jogador.getPontuacao();
		
		jogador.setPontuacao(jogada(pecas, pontuacao));
	}
	
	public boolean checar_tabuleiro (int pecas[][]) {
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
