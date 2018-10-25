package interface_;

import java.io.IOException;
import java.util.Scanner;

import baseDeConhecimento.Tabuleiro;
import motorDeInferencia.Service;

public class Labirinto {
	private static String chessboard[][]; // Cria um vetor de vetor chamado chessboard
	private static Integer x1;
	private static Integer y1;
	private static Integer size; // Declara o tamanho do tabuleiro como variável global
	private final static Integer SEE_SIZE = 3; // Define o tamanho do range de visão do boneco
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
		tabuleiro.setObstaculos(5);
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
				System.out.print("D│");
				
			} else {
				System.out.print("│");
				
			}
		}

		// Andar para direita sempre, quando possivel, analisar 3 campos na frente para
		// ver se há bloqueio. Quando não for possível andar para a direita
		// calcular uma rota aleatória.

		// Ver

		while (y1 != 39 && x1 != finalPosition) {
			see();
			Thread.sleep(300);
		}
		System.out.println();

		scanner.close();
	}

	public static void see() throws IOException {
		boolean blocked = false;
		if (y1 < 40) {
			for (int i = 1; i < SEE_SIZE; i++) {
				try {
					if (chessboard[x1][y1 + i] == "B") {
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
		} else if (blocked) {
			blocked = false;
			for (int i = 1; i < SEE_SIZE; i++) {
				if (chessboard[x1 + i][y1] == "B ") {
					blocked = true;
				}
			}
			if (!blocked) {
				x1++;
				walk(x1, y1);
			}
		}
	}

	public static void walk(Integer x, Integer y) throws IOException {
		chessboard[x][y] = "╳ ";

		for (int i = 0; i < chessboard.length; i++) {
			System.out.println();

			for (int j = 0; j < chessboard[i].length; j++) {

				System.out.print("│" + chessboard[i][j]);
			}
			if (i == finalPosition) {
				System.out.print("D│");
				
			} else {
				System.out.print("│");
				
			}
		}
	}

	private static double result(Integer x, Integer y, double result) {
		int h = 0;

		if (x < 0 || y < 0) {
			return result;
		}
		if (chessboard[x][y].equals("B")) {
			return result;
		}

		if (x < y) {
			h = (size - y) + (y - x);
		}
		if (y < x) {
			h = (size - x) + (x - y);
		}
		if (x == y) {
			h = (size - x) + (y - x);
		}
		Double compare = (Double) (14.0 - h) / 14.0;

		if (compare >= result) {
			x1 = x;
			y1 = y;
		}

		return result > compare ? result : compare;

	}

}
