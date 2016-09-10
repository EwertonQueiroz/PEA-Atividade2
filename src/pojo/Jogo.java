package pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Jogo {
	// Esta fun��o l� o nome do jogador e inicializa o placar do mesmo em 0.
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
	 * Esta fun��o declara, inicializa e devolve a matriz que corresponder� �s pe�as e cria duas listas e dois vetores utilizados
	 * na inicializa��o da matriz.
	 * 
	 * Funcionamento:
	 * 		Uma lista com valores de 0 � 3 que ser� utilizada para determinar os �ndices da matriz, um vetor de 0 � 3 que corresponder�
	 * 		a linha da matriz e receber� os elementos da lista (note que os elementos da lista est�o permutados aleatoriamente) e outro
	 * 		vetor de 0 � 3 corresponder� � coluna e funciona de forma semelhante ao primeiro vetor. A segunda lista cont�m n�meros
	 * 		de 1 a 8 que ser�o utilizados como as cartas do jogo.
	 * 
	 * 		Ap�s os valores gerados aleatoriamente sa�rem da lista para os vetores, a matriz � percorrida recebendo como �ndices os
	 * 		vetores correspondentes a linha e a coluna e atribuindo a cada posi��o desta matriz um elemento da lista que cont�m as "cartas".
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
	 * Esta � uma fun��o de testes para visualizar a matriz gerada.                    /
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
	 *  Esta fun��o faz a leitura de um n�mero inteiro.
	 *  O nextInt() n�o foi utilizado diretamente nos locais que precisavam de leitura pois estava dando NoSuchElementException.
	 *  A anota��o Suppress Warnings � para impedir que a fun��o lance avisos devido ao n�o fechamento do fluxo de entrada.
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
	 * Esta fun��o recebe uma matriz correspondente as pe�as do jogo e a pontua��o atual do jogador.
	 * 
	 * Funcionamento:
	 * 		Primeiro verifica-se se as cartas s�o v�lida, ou seja, se todas as pe�as da partida est�o diferente de -1.
	 * 		Caso as cartas sejam v�lidas � solicitada a posi��o da primeira carta e � realizada uma checagem para
	 * 		saber se �quela posi��o � v�lida ou n�o, se n�o for, � exibida uma mensagem informando que foi inserida
	 * 		uma posi��o inv�lida e solicita-se uma nova posi��o, se for v�lida, � solicitada a posi��o da segunda
	 * 		carta e a mesma verifica��o anterior � realizada.
	 * 		Os valores informados pelo jogador s�o decrementados em uma unidade para que possam ser passados para o vetor
	 * 		para que seja realizada a compara��o entre a primeira posi��o e a segunda.
	 * 		
	 * 		Ap�s o decremento em uma unidade, h� uma compara��o entre os valores informados para a carta, esta verifica��o
	 * 		serve para o jogador n�o indicar a mesma carta duas vezes e, consequentemente, pontuar.
	 * 		
	 * 		Ap�s isso, verifica-se se �quelas posi��es informadas s�o diferentes de -1, se n�o forem, a execu��o retorna
	 * 		para a etapa de leitura e informa que as cartas informadas j� foram desviradas, se forem, verifica-se se os
	 * 		valores s�o iguais, caso n�o sejam, � informado que as cartas s�o diferentes e � passada a vez, se forem,
	 * 		a pontua��o � incrementada em uma unidade, as pe�as referentes a posi��o informada pelo jogador s�o atualizadas
	 * 		para -1 e � informado que as cartas s�o iguais e que � a vez do advers�rio.
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
					System.out.println("Informe a linha da primeira pe�a:");
					linha_primeira = ler_inteiro();
					
					System.out.println("Informe a coluna da primeira pe�a:");
					coluna_primeira = ler_inteiro();
					
					valor_valido = validar_jogada(linha_primeira, coluna_primeira);
					
					if (valor_valido == true) {
						linha_primeira -= 1;
						coluna_primeira -= 1;
					}
				}
				
				valor_valido = false;
				
				while (valor_valido == false) {
					System.out.println("Informe a linha da segunda pe�a:");
					linha_segunda = ler_inteiro();
					
					System.out.println("Informe a coluna da segunda pe�a:");
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
							System.out.println("As pe�as s�o iguais, voc� pontuou! Vez do advers�rio.\n");
						}
						
						else {
							System.out.println("As pe�as s�o diferentes! Vez do advers�rio.\n");
							turno = false;
						}
					}
					
					else {
						if (pecas[linha_primeira][coluna_primeira] == -1) {
							System.out.println("A pe�a [" + (linha_primeira + 1) + "][" + (coluna_primeira + 1) + "] j� foi desvirada, tente outra.\n");
						}
						
						else {
							System.out.println("A pe�a [" + (linha_segunda + 1) + "][" + (coluna_segunda + 1) + "] j� foi desvirada, tente outra.\n");
						}
					}
				}
				
				else {
					System.out.println("N�o � permitido informar a mesma pe�a duas vezes.");
					validar_opcao = true;
				}
			}
		}
		
		else {
			System.out.println("Cartas inv�lidas!");
		}
		
		return pontuacao;
	}
	
	/*
	 * Esta fun��o verifica se a posi��o informada pelo jogador � uma posi��o v�lida na matriz.
	 */
	public boolean validar_jogada(int linha, int coluna) {
		if (linha == 0 || linha > 4) {
			System.out.println("Valor inv�lido para linha.\nUtilize valores entre 1 e 4.\n");
			return false;
		}
		
		if (coluna == 0 || coluna > 4) {
			System.out.println("Valor inv�lido para coluna.\nUtilize valores entre 1 e 4.\n");
			return false;
		}
		
		return true;
	}
	
	/*
	 * Esta fun��o recebe a matriz correspondente �s cartas e o objeto do jogador que est� jogando no momento e sua pontua��o � atualizada.
	 * 
	 * Funcionamento:
	 * 		A partir do objeto do jogador, � obtida a pontua��o do mesmo e � chamada a fun��o setPontuacao para
	 * 		atualizar seus pontos. A fun��o setPontuacao recebe como par�metro a fun��o jogada().
	 */
	public void turno (int pecas[][], Jogador jogador) {
		int pontuacao = jogador.getPontuacao();
		
		jogador.setPontuacao(jogada(pecas, pontuacao));
	}
	
	/*
	 * Esta fun��o recebe a matriz correspondente �s cartas e devolve True se a contagem de elementos {-1}
	 * � diferente do tamanho da matriz e False se for igual.
	 * O contador s� � incrementado at� atingir 16, que � o tamanho da matriz. 
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
	 * Esta fun��o recebe o objeto dos dois jogadores, obt�m suas respectivas pontua��es, compara-as e informa quem venceu ou se houve um empate.
	 * Junto com o nome do vencedor, ela exibe tamb�m a pontua��o obtida pelo mesmo. 
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
