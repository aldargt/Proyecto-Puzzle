package proyecto_final_puzzle;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Figuras extends JButton implements ActionListener{
	private int posX;
	private int posY;
	private final int SolucionPosX;
	private final int SolucionPosY;
	private int dimension;
        public String nick;
        public int LosMov;
        public int LaHora;
        public int Elminuto;
        public int ElSegundo;
        Jugadores ContieneValores;
	public Figuras(int solPosX, int solPosY, ImageIcon figure, int dimension){
		this.dimension = dimension;
		this.SolucionPosX = solPosX;
		this.SolucionPosY = solPosY;
		this.posX = solPosX;
		this.posY = solPosY;
		this.setIcon(figure);
		this.setPreferredSize(new Dimension(figure.getIconWidth(), figure.getIconHeight()));
		this.addActionListener(this);
		}
        
        public int getPosX() {
		return posX;
	}
	public void setPos(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	public int getPosY() {
		return posY;
	}
	public int getSolucionPosX() {
		return SolucionPosX;
	}
	public int getSolucionPosY() {
		return SolucionPosY;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Movimiento();
	}
	private void Movimiento(){
		Celdas[][] board = Tablero.MatrizTablero;
		try{
		if(board[posX][posY +1].getFigura() == null){ 
			Tablero.MatrizTablero[posX][posY +1].setFigura(this);
			Tablero.MatrizTablero[posX][posY].setFigura(null);
			posY ++;
			Puzzle.getTabla().removeAll();
			Puzzle.getTabla().ActualizarTablero();
			VerificarRespuesta();
			Puzzle.Agregar();
			return;
		}
		}catch(ArrayIndexOutOfBoundsException e){			
		}
		try{
		if(board[posX+1][posY].getFigura() == null){ 
			Tablero.MatrizTablero[posX+1][posY].setFigura(this);
			Tablero.MatrizTablero[posX][posY].setFigura(null);
			posX ++;
			Puzzle.getTabla().remover();
			VerificarRespuesta();
			Puzzle.Agregar();
			return;
		}
		}catch(ArrayIndexOutOfBoundsException e){			
		}
		try{
		if(board[posX-1][posY].getFigura() == null){ 
			Tablero.MatrizTablero[posX-1][posY].setFigura(this);
			Tablero.MatrizTablero[posX][posY].setFigura(null);
			posX --;
			Puzzle.getTabla().remover();
			VerificarRespuesta();
			Puzzle.Agregar();
			return;
		}
		}catch(ArrayIndexOutOfBoundsException e){			
		}
		try{
		if(board[posX][posY-1].getFigura() == null){ 
			Tablero.MatrizTablero[posX][posY-1].setFigura(this);
			Tablero.MatrizTablero[posX][posY].setFigura(null);
			posY --;
			Puzzle.getTabla().remover();
			VerificarRespuesta();
			Puzzle.Agregar();
			return;
		}
		}catch(ArrayIndexOutOfBoundsException e){		
		}
	}
	public void VerificarRespuesta(){            
		Figuras figure = null;
		for(int i = 0; i<dimension; i++){
			for(int j = 0; j<dimension; j++){
				
				figure = Tablero.MatrizTablero[i][j].getFigura();
				if(figure == null)
					continue;
				
				if(figure.getPosX() != figure.getSolucionPosX() || figure.getPosY() != figure.getSolucionPosY()){					
                                    return;                                       
				}
			}	
		}
		Puzzle.getTemporizador().stop();
                
		JOptionPane.showMessageDialog(new JPanel(), "Felicidades completo el Puzzle","Puzzle Completado", JOptionPane.INFORMATION_MESSAGE);
                
                nick=JOptionPane.showInputDialog("Introdusca su nombre o nick: ");
                
                LosMov=Puzzle.ContarMovimientos;
                LaHora=Puzzle.Horas;
                Elminuto=Puzzle.Minutos;
                ElSegundo=Puzzle.Segundos; 
                ContieneValores=null;
                ContieneValores=DarValores(nick,LosMov,LaHora,Elminuto,ElSegundo);
                Conexion.Guardar(ContieneValores);               
                
	}
             public Jugadores DarValores(String no, int mov, int h, int m, int s){
                Jugadores r=null;
                String Nicks=no;
                int Movidas=mov;
                int Horas=h;
                int Minutos=m;
                int Segundos=s;
                r= new Jugadores(Nicks, Movidas, Horas, Minutos, Segundos);
                return r;
                }
        
}

