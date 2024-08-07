package proyecto_final_puzzle;
public class Celdas{
	private final int x, y;
	private Figuras Figura;
public Celdas(int x, int y, Figuras figure){
		this.x = x;
		this.y = y;
		this.Figura = figure;
		}
	public Celdas(int x, int y){
		this.x = x;
		this.y = y;
		Figura = null;
	}
	public Figuras getFigura(){
		return Figura;
	}
	public void setFigura(Figuras Figura){
		this.Figura = Figura;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
