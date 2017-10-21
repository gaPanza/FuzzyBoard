package interface_;

import java.io.IOException;
import java.util.Scanner;

import baseDeConhecimento.Tabuleiro;
import motorDeInferencia.Service;

public class Labirinto {
	private static String chessboard[][];
	private static Integer x1;
	private static Integer y1;
	public static void main(String[] args) throws IOException {
		Tabuleiro tabuleiro = new Tabuleiro();
		Scanner scanner = new Scanner(System.in);
		Service service = new Service();
		System.out.println("Digite tamanho do tabuleiro numero de linhas = numero de colunas");
		tabuleiro.setSize(15);

		System.out.println("Digite quantidade de obstáculos");
		tabuleiro.setObstaculos(scanner.nextInt());

		/*
		 * #############################################################################
		 * #######################################################################
		 */

		// Inicializando o Chessboard
		chessboard = tabuleiro.getChessboard();
		for (int i = 0; i < chessboard.length; i++) {
			for (int j = 0; j < chessboard[i].length; j++) {
				chessboard[i][j] = " ";
			}
		}

		// Plotar o boneco no Chessboard
		chessboard[0][0] = "X";

		for (int obst = 0; obst < tabuleiro.getObstaculos(); obst++) {
			int theNumberX = service.randomize();
			int theNumberY = service.randomize();

			if (((theNumberX == 0 && theNumberY == 0)
					|| (theNumberX == tabuleiro.getSize() - 1 && theNumberY == tabuleiro.getSize() - 1))) {
				obst = obst - 1;
			} else {
				chessboard[theNumberX][theNumberY] = 	"B";

			}

		}

		// Plotar o Chessboard Inicial
		for (int i = 0; i < chessboard.length; i++) {
			System.out.println();

			for (int j = 0; j < chessboard[i].length; j++) {

				System.out.print(" | " + chessboard[i][j]);
			}

			System.out.print(" |");
			System.out.println();
		}

		// Fazer o boneco andar
		bestWay(0, 0);
		// Chegar no ultimo ponto

		scanner.close();
	}

	public static void walk(Integer x, Integer y) throws IOException {
		try {
			Thread.sleep(1000);
			for (int i = 0; i < 50; ++i)
				System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		chessboard[x][y] = "X";
		
		for (int i = 0; i < chessboard.length; i++) {
			System.out.println();

			for (int j = 0; j < chessboard[i].length; j++) {

				System.out.print(" | " + chessboard[i][j]);
			}
			System.out.print(" |");
			System.out.println();
		}
		bestWay(x,y);
	}

	private static void bestWay(Integer x, Integer y) throws IOException {
		double result = 0.0;
		
		result = result (x+1,y+1, result);
		result = result (x+1,y+0, result);
		result = result (x+0,y+1, result);
		result = result (x+1,y-1, result);
		result = result (x-1,y+1, result);
		
		
		walk(x1,y1);
	}
	
	private static double result(Integer x, Integer y, double result) {
		int h = 0;
		
		if(x < 0 || y<0) {
			return 0.0;
		}
		if(chessboard[x][y].equals("B")) {
			return 0.0;
		}
		
		if(x<y) {
			h = x + (y-x);
		}
		if(y<x) {
			h = y + (x-y);
		}
		if(x == y) {
			h = x - y;
		}
		double compare =  (14 - h)  / 14;
		
		if(compare > result) {
			x1 = x;
			y1 = y;
		}
		
		return result > compare ? result : compare;
		
		
	}
	
	

}
