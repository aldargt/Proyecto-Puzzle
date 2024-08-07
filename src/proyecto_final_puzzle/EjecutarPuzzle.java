package proyecto_final_puzzle;

public class EjecutarPuzzle{
	public static void main(String[] args){                       
		Puzzle puzzle = new Puzzle();
		puzzle.setVisible(true);
                Conexion cnx=new Conexion();
                cnx.conexion();                
	}
}
