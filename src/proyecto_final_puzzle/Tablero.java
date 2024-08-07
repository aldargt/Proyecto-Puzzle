package proyecto_final_puzzle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Tablero extends JPanel{
public static Celdas[][] MatrizTablero;
	private ArrayList<Celdas> CompletarTablero = new ArrayList<Celdas>(); 
	public final int dimension;
	private int x, y;
	private final int AnchoFigura, AltoFigura;
	private JLabel label;
	public Tablero(int dimension, BufferedImage puzzle){
		this.setPreferredSize(new Dimension(748,0));
		this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 5));
		this.setBackground(Color.BLACK);
		this.dimension = dimension;
		MatrizTablero = new Celdas[dimension][dimension];
		x = 0;
		y = 0;
		AnchoFigura = puzzle.getWidth()/dimension;
		AltoFigura = puzzle.getHeight()/dimension;
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));		
		for(int i=0; i<dimension; i++){
			for(int j=0; j<dimension; j++){
				if(i == dimension - 1 && j == dimension -1){			
					continue;
				}
				CompletarTablero.add(new Celdas(i, j, new Figuras(i, j, new ImageIcon(puzzle.getSubimage(x, y, AnchoFigura, AltoFigura)), dimension)));
				
				x += AnchoFigura;
			}
			x = 0;
			y += AltoFigura;
		}
		TableroMensajero();		
		remover();		
	}
	public void TableroMensajero(){		
		Random GeneradorRandom = new Random();
		ArrayList<Celdas> cellStore = new ArrayList<Celdas>(CompletarTablero);		
		for(int i = 0; i<dimension; i++){
			for(int j = 0; j<dimension; j++){
				if(i == dimension-1 && j == dimension-1){
					MatrizTablero[i][j] = new Celdas(i, j);
					continue;
				}
				int randomIndex = GeneradorRandom.nextInt(CompletarTablero.size());
				CompletarTablero.get(randomIndex).getFigura().setPos(i, j);
				MatrizTablero[i][j] = new Celdas(i, j, CompletarTablero.get(randomIndex).getFigura());
				CompletarTablero.remove(randomIndex);
			}
		}
		CompletarTablero = cellStore;
		remover();
	}
	public void ActualizarTablero(){		
		for(int i = 0; i<dimension; i++){
			for(int j = 0; j<dimension; j++){	
				if(MatrizTablero[i][j].getFigura() == null){
					label = new JLabel();
					label.setPreferredSize(new Dimension(AnchoFigura, AltoFigura));
					this.add(label);
					continue;
				}
				this.add(MatrizTablero[i][j].getFigura());
			}
		}
		Puzzle.getContenedor().validate();
	}
	public void remover(){
		this.removeAll();
		ActualizarTablero();
	}
}

