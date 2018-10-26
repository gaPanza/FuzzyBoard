package interface_;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import baseDeConhecimento.Tabuleiro;
import motorDeInferencia.Service;

public class Labirinto {
	private static String chessboard[][]; // Cria um vetor de vetor chamado chessboard
	private static Integer x1;
	private static Integer y1;
	private static Integer size; // Declara o tamanho do tabuleiro como variável global
	private final static Integer SEE_SIZE = 4; // Define o tamanho do range de visão do boneco
	private static Integer finalPosition; // Instancia a posição onde o labirinto terminará

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Service service = new Service();

		// Printar o tabuleiro com posições 0 - 39
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.setSize(40);
		size = tabuleiro.getSize() - 1;
		chessboard = tabuleiro.getChessboard();
		for (int i = 0; i < chessboard.length; i++) {
			for (int j = 0; j < chessboard[i].length; j++) {
				chessboard[i][j] = "  ";
			}
		}

		int p = 0;
		for (int i = 0; i < chessboard.length; i++) {
			System.out.println();
			for (int j = 0; j < chessboard[i].length; j++) {

				System.out.print("│" + chessboard[i][j]);

			}
			System.out.print(String.format("%02d", p) + "│");
			p++;
		}

		// Ler o input do usuário
		System.out.println();
		System.out.println("Digite a posição que deseja finalizar o labirinto:");
		finalPosition = scanner.nextInt();

		// Gerar o tabuleiro
		chessboard[0][0] = "╳ ";
		x1 = 0;
		y1 = 0;
		tabuleiro.setObstaculos(50);
		for (int obst = 0; obst < tabuleiro.getObstaculos(); obst++) {
			int theNumberX = service.randomize();
			int theNumberY = service.randomize();
			if (((theNumberX == 0 && theNumberY == 0)
					|| (theNumberX == tabuleiro.getSize() - 1 && theNumberY == tabuleiro.getSize() - 1)
					|| chessboard[theNumberX][theNumberY].equals("B"))) {
				obst = obst - 1;
			} else {
				chessboard[theNumberX][theNumberY] = "■ ";
				chessboard[theNumberX + 1][theNumberY + 1] = "■ ";
				chessboard[theNumberX + 1][theNumberY] = "■ ";
			}
		}

		// Plotar o Chessboard Inicial
		for (int i = 0; i < chessboard.length; i++) {
			System.out.println();
			for (int j = 0; j < chessboard[i].length; j++) {
				System.out.print("│" + chessboard[i][j]);
			}
			if (i == finalPosition) {
				System.out.print("D");

			} else {
				System.out.print("│");

			}
		}


		while (y1 != 39 && x1 != finalPosition) {
			see();
			Thread.sleep(300);
		}
		System.out.println();

		scanner.close();
	}

	public static void see() throws IOException {
		chessboard[x1][y1] = "  ";
		boolean blocked = false;
		// Verifica se para direita está blockeado
		if (y1 < 40) {
			for (int i = 1; i < SEE_SIZE; i++) {
				try {
					if (chessboard[x1][y1 + i] == "■ ") {
						blocked = true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					blocked = true;
					break;
				}
			}
		} else {
			blocked = true;
		}
		if (!blocked) {
			y1++;
			walk(x1, y1);
		}

		else if (blocked) {
			blocked = false;
			Random ran = new Random();
			int r = ran.nextInt(3);
			switch (r) {
			case 0: // Andar para baixo
				if (y1 < 40) {
					for (int i = 1; i < SEE_SIZE; i++) {
						try {
							if (chessboard[x1 + i][y1] == "■ ") {
								blocked = true;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							blocked = true;
							break;
						}
					}
					if (blocked) {
						see();
						break;
					}
					x1++;
				} else {
					see();
				}
				break;
			case 1: // Andar para esquerda
				if (y1 > 0) {
					for (int i = 1; i < SEE_SIZE; i++) {
						try {
							if (chessboard[x1][y1 - i] == "■ ") {
								blocked = true;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							blocked = true;
							see();
							break;
						}
					}
					if (blocked) {
						see();
						break;
					}
					y1--;
				} else {
					see();
				}
				break;
			case 2: // Andar para cima
				if (y1 < 40 && x1 > 0) {
					for (int i = 1; i < SEE_SIZE; i++) {
						try {
							if (chessboard[x1 - i][y1] == "■ ") {
								blocked = true;
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							blocked = true;
							break;
						}
					}
					if (blocked) {
						see();
						break;
					}
					x1--;
				} else {
					see();
				}
			}
			walk(x1, y1);
		}
	}

	public static void walk(Integer x, Integer y) throws IOException {
		System.out.println(x1);
		System.out.println(y1);
		chessboard[x][y] = "╳ ";
		System.out.println();
		for (int i = 0; i < chessboard.length; i++) {
			System.out.println();

			for (int j = 0; j < chessboard[i].length; j++) {

				System.out.print("│" + chessboard[i][j]);
			}
			if (i == finalPosition) {
				System.out.print("D");

			} else {
				System.out.print("│");

			}
		}
	}

}
