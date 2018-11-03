package interface_;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
	private static int right = 85;
	private static int left = 75;
	private static int up = 75;
	private static int down = 75;

	public static void main(String[] args) throws IOException, InterruptedException {
		// Definição de Variáveis

		// Definição de Gráficos Nebulosos

		// Fuzzyficação

		// Defuzzyficação

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
		int nextRight = right;
		int nextLeft = left;
		int nextUp = up;
		int nextDown = down;
		
		//Reset if it's too low
		
		if (right < 20) {
			right = 50;
		} if (left < 20) {
			left = 50;
		} if (up < 20) {
			up = 50;
		} if (down < 20) {
			down = 50;
		}
		chessboard[x1][y1] = "  ";

		// Direita
		if (y1 < 40) {
			for (int i = 1; i < SEE_SIZE; i++) {
				try {
					if (chessboard[x1][y1 + i] == "■ ") {
						nextRight = 20;
						right -= 5;
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					nextRight = 20;
					right -= 5;
					break;
				}
			}
		} else {
			nextRight = 20;
			right -= 5;
		} // Baixo
		if (y1 < 40) {
			for (int i = 1; i < SEE_SIZE; i++) {
				try {
					if (chessboard[x1 + i][y1] == "■ ") {
						nextDown = 20;
						down -= 5;
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					nextDown = 20;
					down -= 5;
					break;
				}
			}
		} else {
			nextDown = 20;
			down -= 5;
		} // Esquerda
		if (y1 > 0) {
			for (int i = 1; i < SEE_SIZE; i++) {
				try {
					if (chessboard[x1][y1 - i] == "■ ") {
						nextLeft = 20;
						left -= 5;
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					nextLeft = 20;
					break;
				}
			}
		} else {
			nextLeft = 20;
			left -= 5;
		} // Cima
		if (y1 < 40 && x1 > 0) {
			for (int i = 1; i < SEE_SIZE; i++) {
				try {
					if (chessboard[x1 - i][y1] == "■ ") {
						nextUp = 20;
						up -= 5;
						break;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					nextUp = 20;
					break;
				}
			}
		} else {
			nextUp = 20;
			up -= 5;
		}
		int[] array = new int[] {nextRight, nextLeft, nextUp, nextDown};
		Arrays.sort(array);
		ArrayList <String> selection = new ArrayList<String>();
		
		if (nextRight == array[3]) {
			selection.add("right");
		} if (nextLeft == array[3]) {
			selection.add("left");
		} if (nextUp == array[3]) {
			selection.add("up");
		} if (nextDown == array[3]) {
			selection.add("down");
		}
		int arraySelection = 0;
		if (selection.size() > 1) {
			Random ran = new Random();
			arraySelection = ran.nextInt(selection.size());
		}
		switch (selection.get(arraySelection)) {
			case "right":
				y1++;
				break;
			case "left":
				y1--;
				break;
			case "up":
				x1--;
				break;
			case "down":
				x1++;
				break;
		}
		System.out.println("Right" + nextRight + "Left" + nextLeft + "Up" + nextUp + "Down" + nextDown);
		walk(x1,y1);
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
