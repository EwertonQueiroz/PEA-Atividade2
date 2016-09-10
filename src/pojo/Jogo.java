package pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jogo {
	// Esta função lê o nome do jogador e inicializa o placar do mesmo em 0.
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
	
	/* 
	 * Esta função declara, inicializa e devolve a matriz que corresponderá às peças e cria duas listas e dois vetores utilizados
	 * na inicialização da matriz.
	 * 
	 * Funcionamento:
	 * 		Uma lista com valores de 0 à 3 que será utilizada para determinar os índices da matriz, um vetor de 0 à 3 que corresponderá
	 * 		a linha da matriz e receberá os elementos da lista (note que os elementos da lista estão permutados aleatoriamente) e outro
	 * 		vetor de 0 à 3 corresponderá à coluna e funciona de forma semelhante ao primeiro vetor. A segunda lista contém números
	 * 		de 1 a 8 que serão utilizados como as cartas do jogo.
	 * 
	 * 		Após os valores gerados aleatoriamente saírem da lista para os vetores, a matriz é percorrida recebendo como índices os
	 * 		vetores correspondentes a linha e a coluna e atribuindo a cada posição desta matriz um elemento da lista que contém as "cartas".
	 */
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
	
	/* 
	 * Esta é uma função de testes para visualizar a matriz gerada.                    /
	 */
	public void imprimir_pecas (int pecas[][]) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(pecas[i][j] + "\t");
			}
			System.out.print("\n");
		}
		System.out.println("\n");
	}
	//*/
	
	/*
	 *  Esta função faz a leitura de um número inteiro.
	 *  O nextInt() não foi utilizado diretamente nos locais que precisavam de leitura pois estava dando NoSuchElementException.
	 *  A anotação Suppress Warnings é para impedir que a função lance avisos devido ao não fechamento do fluxo de entrada.
	 */
	@SuppressWarnings("resource")
	private int ler_inteiro () {
		Scanner read = new Scanner(System.in);
		int numero = 0;
		
		while (read.hasNext()){
		    if (read.hasNextInt()){
		        numero = read.nextInt();
		        break;
		    }
		    
		    else {
		        read.next();
		    }
		}
		
		return numero;
	}
	
	/*
	 * Esta função recebe uma matriz correspondente as peças do jogo e a pontuação atual do jogador.
	 * 
	 * Funcionamento:
	 * 		Primeiro verifica-se se as cartas são válida, ou seja, se todas as peças da partida estão diferente de -1.
	 * 		Caso as cartas sejam válidas é solicitada a posição da primeira carta e é realizada uma checagem para
	 * 		saber se àquela posição é válida ou não, se não for, é exibida uma mensagem informando que foi inserida
	 * 		uma posição inválida e solicita-se uma nova posição, se for válida, é solicitada a posição da segunda
	 * 		carta e a mesma verificação anterior é realizada.
	 * 		Os valores informados pelo jogador são decrementados em uma unidade para que possam ser passados para o vetor
	 * 		para que seja realizada a comparação entre a primeira posição e a segunda.
	 * 		
	 * 		Após o decremento em uma unidade, há uma comparação entre os valores informados para a carta, esta verificação
	 * 		serve para o jogador não indicar a mesma carta duas vezes e, consequentemente, pontuar.
	 * 		
	 * 		Após isso, verifica-se se àquelas posições informadas são diferentes de -1, se não forem, a execução retorna
	 * 		para a etapa de leitura e informa que as cartas informadas já foram desviradas, se forem, verifica-se se os
	 * 		valores são iguais, caso não sejam, é informado que as cartas são diferentes e é passada a vez, se forem,
	 * 		a pontuação é incrementada em uma unidade, as peças referentes a posição informada pelo jogador são atualizadas
	 * 		para -1 e é informado que as cartas são iguais e que é a vez do adversário.
	 */
	private int jogada (int pecas[][], int pontuacao) {
		int linha_primeira = 0, linha_segunda = 0;
		int coluna_primeira = 0, coluna_segunda = 0;
		
		boolean turno = true;
		boolean valor_valido = false;
		boolean validar_opcao = true;
		
		if (checar_tabuleiro(pecas)) {
			while (turno == true) {
				while (valor_valido == false) {
					System.out.println("Informe a linha da primeira peça:");
					linha_primeira = ler_inteiro();
					
					System.out.println("Informe a coluna da primeira peça:");
					coluna_primeira = ler_inteiro();
					
					valor_valido = validar_jogada(linha_primeira, coluna_primeira);
					
					if (valor_valido == true) {
						linha_primeira -= 1;
						coluna_primeira -= 1;
					}
				}
				
				valor_valido = false;
				
				while (valor_valido == false) {
					System.out.println("Informe a linha da segunda peça:");
					linha_segunda = ler_inteiro();
					
					System.out.println("Informe a coluna da segunda peça:");
					coluna_segunda = ler_inteiro();
					
					valor_valido = validar_jogada(linha_segunda, coluna_segunda);
					
					if (valor_valido == true) {
						linha_segunda -= 1;
						coluna_segunda -= 1;
					}
				}
				
				valor_valido = false;
				
				if (linha_primeira == linha_segunda) {
						if (coluna_primeira == coluna_segunda) {
							validar_opcao = false;
						}
				}
				
				if (validar_opcao) {
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
							System.out.println("A peça [" + (linha_primeira + 1) + "][" + (coluna_primeira + 1) + "] já foi desvirada, tente outra.\n");
						}
						
						else {
							System.out.println("A peça [" + (linha_segunda + 1) + "][" + (coluna_segunda + 1) + "] já foi desvirada, tente outra.\n");
						}
					}
				}
				
				else {
					System.out.println("Não é permitido informar a mesma peça duas vezes.");
					validar_opcao = true;
				}
			}
		}
		
		else {
			System.out.println("Cartas inválidas!");
		}
		
		return pontuacao;
	}
	
	/*
	 * Esta função verifica se a posição informada pelo jogador é uma posição válida na matriz.
	 */
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
	
	/*
	 * Esta função recebe a matriz correspondente às cartas e o objeto do jogador que está jogando no momento e sua pontuação é atualizada.
	 * 
	 * Funcionamento:
	 * 		A partir do objeto do jogador, é obtida a pontuação do mesmo e é chamada a função setPontuacao para
	 * 		atualizar seus pontos. A função setPontuacao recebe como parâmetro a função jogada().
	 */
	public void turno (int pecas[][], Jogador jogador) {
		int pontuacao = jogador.getPontuacao();
		
		jogador.setPontuacao(jogada(pecas, pontuacao));
	}
	
	/*
	 * Esta função recebe a matriz correspondente às cartas e devolve True se a contagem de elementos {-1}
	 * é diferente do tamanho da matriz e False se for igual.
	 * O contador só é incrementado até atingir 16, que é o tamanho da matriz. 
	 */
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
	
	/*
	 * Esta função recebe o objeto dos dois jogadores, obtém suas respectivas pontuações, compara-as e informa quem venceu ou se houve um empate.
	 * Junto com o nome do vencedor, ela exibe também a pontuação obtida pelo mesmo. 
	 */
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
