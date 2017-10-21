package baseDeConhecimento;
import java.util.List;

public class Tabuleiro {
	private Integer size;
	private Integer obstaculos;
	private String chessboard [][];
	
	private List<Ponto> pontos;

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}

	public Tabuleiro(List<Ponto> pontos) {
		this.pontos = pontos;
	}
	
	public Tabuleiro() {
		
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
		this.chessboard = new String [size] [size];
	}

	public Integer getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(Integer obstaculos) {
		this.obstaculos = obstaculos;
	}
	
	public String[][] getChessboard() {
		return chessboard;
	}

}
