package proyecto_final_puzzle;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
public class IniciarPuzzle extends JFrame implements ActionListener{
	private JPanel Principal = new JPanel();
	private JButton Abrir, Iniciar;
	private JLabel ImagenPuzzle = new JLabel("Selecciona una imagen y el nivel de dificultad");
	private JFileChooser EscogerArchivo;
	private JRadioButton Facil, Medio, Dificil;
	private BufferedImage ImagenJuego = null;
	private int Level = 0;
	private Image IconoDiminuto = CargarImagen.SubiendoImagen("/MiniIcono.png");                	
	public IniciarPuzzle(){
		setTitle("Añadir Una Imagen");
		setSize(671, 726);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
                Principal.setLayout(null);
                Principal.setBackground(Color.BLACK);
		Principal.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setIconImage(IconoDiminuto);		
		Facil = new JRadioButton("Fácil 3x3  ");
                Facil.setBackground(Color.BLACK);
                Facil.setForeground(Color.ORANGE);
                Facil.setFont(new Font("cooper black", Font.BOLD,15));
		Medio = new JRadioButton("Medio 4x4  ");
                Medio.setBackground(Color.BLACK);
                Medio.setForeground(Color.ORANGE);
                Medio.setFont(new Font("cooper black", Font.BOLD,15));
		Dificil = new JRadioButton("Difícil 5x5  ");
                Dificil.setBackground(Color.BLACK);
                Dificil.setForeground(Color.ORANGE);
                Dificil.setFont(new Font("cooper black", Font.BOLD,15));		
		ButtonGroup group = new ButtonGroup();
		group.add(Facil);
		group.add(Medio);
		group.add(Dificil);		
		Abrir = new JButton(new ImageIcon(CargarImagen.SubiendoImagen("/Click.png").getScaledInstance(645, 605, Image.SCALE_SMOOTH)));
                Abrir.setBackground(Color.ORANGE);
		Abrir.setName("Abrir");
		Abrir.addActionListener(this);
		Iniciar = new JButton("JUGAR");
                Iniciar.setBackground(Color.ORANGE);
                Iniciar.setFont(new Font("cooper black", Font.BOLD,20));
		Iniciar.setName("Iniciar");
		Iniciar.addActionListener(this);                
                ImagenPuzzle.setOpaque(true); 
                ImagenPuzzle.setForeground(Color.ORANGE);
                ImagenPuzzle.setBackground(Color.BLACK);
                ImagenPuzzle.setFont(new Font("cooper black", 1, 15));                
		Principal.add(ImagenPuzzle);
		Principal.add(Abrir);		
		Principal.add(Facil);
		Principal.add(Medio);
		Principal.add(Dificil);		
		Principal.add(Iniciar);
		add(Principal);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e){		
		JButton Boton2 = (JButton)e.getSource();
		if(Boton2.getName().equals("Abrir")){
			
			EscogerArchivo = new JFileChooser();
			int accion = EscogerArchivo.showOpenDialog(null);
			if(accion == JFileChooser.APPROVE_OPTION){
				File file = EscogerArchivo.getSelectedFile();
				try {
					ImagenJuego = ImageIO.read(file);
					Abrir.setIcon(new ImageIcon(ImagenJuego.getScaledInstance(645, 605, Image.SCALE_SMOOTH)));					
				} catch (IOException e1) {
					System.out.println("Debes seleccionar una imagen");
				}
			}
		}else if(Boton2.getName().equals("Iniciar")){
			if(Facil.isSelected()){
				Level = 3;
			}else if(Medio.isSelected()){
				Level = 4;
			}else if(Dificil.isSelected()){
				Level = 5;
			}else{
				return;
			}
			if(ImagenJuego == null)
				return;
			BufferedImage puzzelImage = CalibrarImagen.AjustarImagen(ImagenJuego, 739, 736);
			BufferedImage miniImage = CalibrarImagen.AjustarImagen(ImagenJuego, 418, 407);
			
			Puzzle.Iniciar(puzzelImage, Level, miniImage);
			this.dispose();
		}		
	}
}
