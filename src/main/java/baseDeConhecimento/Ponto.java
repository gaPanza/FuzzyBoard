package baseDeConhecimento;

public class Ponto {
	private Integer xaxis;
	private Integer yaxis;
	private Boolean blocked;
	
	public Integer getXaxis() {
		return xaxis;
	}
	public void setXaxis(Integer xaxis) {
		this.xaxis = xaxis;
	}
	public Integer getYaxis() {
		return yaxis;
	}
	public void setYaxis(Integer yaxis) {
		this.yaxis = yaxis;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	public Ponto(Integer xaxis, Integer yaxis, Boolean blocked) {
		this.xaxis = xaxis;
		this.yaxis = yaxis;
		this.blocked = blocked;
	}
	
	public Ponto() {
		
	}
	
	
}
