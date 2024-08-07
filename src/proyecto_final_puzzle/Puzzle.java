package proyecto_final_puzzle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
public class Puzzle extends JFrame{
	//Añadimos Paneles
	private static JPanel puzzelArea;
	private JPanel TiempoMovimientos;
	//Imagenes y los iconos
	private Image IconoChico = CargarImagen.SubiendoImagen("/MiniIcono.png");
	private BufferedImage IconoPlay, IconoPause, IconoReintentar, IconoNuevoJuego, IconoTop; 
	private static BufferedImage BotonImagen = CargarImagen.SubiendoImagen(("/ImagenInicio.jpg"));
	//Barra de herramientas y sus elementos
	private JToolBar BarraHerramientas = new JToolBar();
	private JButton BotonPausePlay, BotonReintentar, BotonNuevoJuego, BotonTopJugadores;
	//Añadimos Labels
        private JLabel espacio1=new JLabel("     espacio ");
        private JLabel espacio2=new JLabel("      espacio ");
	private JLabel Tiempo = new JLabel("    0 : 0 : 0         ");
	private JLabel TituloPrincipal = new JLabel("                       •••••  MAGIC PUZZLE  •••••                                              ");
	public static int ContarMovimientos = 0;
        private JLabel nuevop=new JLabel("0");
	private static JLabel Movimientos = new JLabel(" "+ContarMovimientos+" ");
	private JLabel TituloTiempo = new JLabel("   Tiempo:                                ");
	private JLabel TituloMovimientos = new JLabel("   Movidas:                              ");
        private JLabel TiempoPause = new JLabel("Dale click en play para continuar jugando...");
        private Image ImagenPause=CargarImagen.SubiendoImagen("/TiempoFuera.jpg");
        private JLabel LabelPause=new JLabel();
	//Estos valores son los finales
	private final int Ancho = 670;
	private final int Alto = 485;
	private final int DimensionIcono = 30;
	private final int DimensionFuente = 35;
	private final int RetrasoTiempo = 1000;
	//Valores del cronometro
	public static int Segundos = 0;
	public static int Minutos = 0;
	public static int Horas = 0;
	//Tablero, mini imagenes y temporizador (estáticos)
	private static JButton MiniImagen = new JButton(new ImageIcon(BotonImagen.getScaledInstance(1105, 725, Image.SCALE_SMOOTH)));
	private static Tablero Tabla = null;
	private static Timer Cronometro;
	private static Container Contenedor;
        public static int np=0;
	public Puzzle(){
		//Establecemos los valores de la ventana
		this.setTitle("Proyecto Puzzle - Taller de Programacíon");
		this.setSize(1190, 829);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(Ancho, Alto));
		this.setMaximumSize(new Dimension(Ancho, Alto));
		this.setResizable(false);
		this.setIconImage(IconoChico);
		Contenedor = this.getContentPane();
		Cronometro = new Timer(RetrasoTiempo, new IconTimerLitener());
		//Cargar todas las imágenes e íconos
		IconoPlay = CargarImagen.SubiendoImagen("/BotonPlay.jpg");
		IconoPause = CargarImagen.SubiendoImagen("/BotonPause.jpg");
		IconoReintentar = CargarImagen.SubiendoImagen("/BotonRetry.jpg");
		IconoNuevoJuego = CargarImagen.SubiendoImagen("/BotonAdd.jpg");
                IconoTop=CargarImagen.SubiendoImagen("/Estrella.jpg");
		//Inicializamos todos los elementos de la barra de herramientas.
		BotonPausePlay = new JButton(new ImageIcon(IconoPause.getScaledInstance(DimensionIcono, DimensionIcono, Image.SCALE_SMOOTH)));
                BotonPausePlay.setBackground(Color.BLACK);
		BotonPausePlay.setName("Pause");
		BotonPausePlay.addActionListener(new IconTimerLitener());		
		BotonReintentar = new JButton(new ImageIcon(IconoReintentar.getScaledInstance(DimensionIcono, DimensionIcono, Image.SCALE_SMOOTH)));
                BotonReintentar.setBackground(Color.BLACK);
		BotonReintentar.setName("Reintentar");
		BotonReintentar.addActionListener(new IconTimerLitener());		
		BotonNuevoJuego = new JButton(new ImageIcon(IconoNuevoJuego.getScaledInstance(DimensionIcono, DimensionIcono, Image.SCALE_SMOOTH)));
                BotonNuevoJuego.setBackground(Color.BLACK);
		BotonNuevoJuego.setName("Nuevo");
		BotonNuevoJuego.addActionListener(new IconTimerLitener());
                BotonTopJugadores = new JButton(new ImageIcon(IconoTop.getScaledInstance(DimensionIcono, DimensionIcono, Image.SCALE_SMOOTH)));
                BotonTopJugadores.setBackground(Color.BLACK);
		BotonTopJugadores.setName("TOP5");
		BotonTopJugadores.addActionListener(new IconTimerLitener());
                
		//Inicializamos los paneles
		puzzelArea = new JPanel();
		puzzelArea.setOpaque(true);
		puzzelArea.setBackground(Color.DARK_GRAY);		
		TiempoMovimientos = new JPanel();
                TiempoMovimientos.setLayout(null);
		TiempoMovimientos.setLayout(new FlowLayout(FlowLayout.CENTER));
		TiempoMovimientos.setOpaque(true);
		TiempoMovimientos.setBackground(Color.DARK_GRAY);		
		//Agregamos los botones a la barra de herramientas
                BarraHerramientas.setOpaque(true);
                BarraHerramientas.setBackground(Color.BLACK);
		BarraHerramientas.add(BotonNuevoJuego);
		BarraHerramientas.add(BotonPausePlay);
		BarraHerramientas.add(BotonReintentar);
                BarraHerramientas.add(BotonTopJugadores);
		TituloPrincipal.setFont(new Font("cooper black", Font.ITALIC, DimensionFuente));
		TituloPrincipal.setForeground(Color.ORANGE);
                TituloPrincipal.setOpaque(true);
                TituloPrincipal.setBackground(Color.BLACK);               		
		BarraHerramientas.add(TituloPrincipal);
		//Establecemos los valores de los labels
		Tiempo.setForeground(Color.WHITE);
		Tiempo.setFont(new Font("Georgia", Font.BOLD, DimensionFuente));		
		Movimientos.setForeground(Color.WHITE);
		Movimientos.setFont(new Font("Georgia", Font.BOLD, DimensionFuente));		
		TituloTiempo.setFont(new Font("Georgia", Font.BOLD, DimensionFuente));
		TituloTiempo.setForeground(Color.ORANGE);	
                nuevop.setFont(new Font("Georgia", Font.BOLD, DimensionFuente));
		nuevop.setForeground(Color.YELLOW);
		TituloMovimientos.setFont(new Font("Georgia", Font.BOLD, DimensionFuente));
		TituloMovimientos.setForeground(Color.ORANGE);               
                LabelPause.setHorizontalAlignment(SwingConstants.CENTER);
                LabelPause.setBounds(10, 10, 1175, 610);
                LabelPause.setIcon(new ImageIcon(ImagenPause.getScaledInstance(1175, 610, Image.SCALE_SMOOTH)));
                TiempoPause.setFont(new Font("cooper black", 3, 45));
                TiempoPause.setForeground(Color.DARK_GRAY);                
                espacio1.setFont(new Font("Georgia", Font.BOLD, DimensionFuente));
                espacio1.setForeground(Color.DARK_GRAY);
                espacio2.setFont(new Font("Georgia", Font.BOLD, DimensionFuente));
                espacio2.setForeground(Color.DARK_GRAY);		
		//Añadimos componentes a los paneles		
		MiniImagen.setBackground(Color.ORANGE);
		TiempoMovimientos.add(MiniImagen);
                TiempoMovimientos.add(nuevop);
                //TiempoMovimientos.add(espacio1);
		TiempoMovimientos.add(TituloTiempo);
		TiempoMovimientos.add(Tiempo);
                TiempoMovimientos.add(espacio2);
		TiempoMovimientos.add(TituloMovimientos);
		TiempoMovimientos.add(Movimientos);               		
		Contenedor.add(BarraHerramientas, BorderLayout.NORTH);
		Contenedor.add(puzzelArea, BorderLayout.EAST);
		Contenedor.add(TiempoMovimientos, BorderLayout.CENTER);
	}
	class IconTimerLitener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object comp = e.getSource();
			if(comp instanceof JButton){
				JButton Boton1 = (JButton)comp;
				if(Boton1.getName().equals("Pause")){
					if(Tabla != null){
						Cronometro.stop();
						Boton1.setIcon(new ImageIcon(IconoPlay.getScaledInstance(DimensionIcono, DimensionIcono, Image.SCALE_SMOOTH)));
						Boton1.setName("Play");
						Tabla.setVisible(false);
                                                Tiempo.setForeground(Color.DARK_GRAY);
                                                Movimientos.setForeground(Color.DARK_GRAY);
                                                TituloTiempo.setForeground(Color.DARK_GRAY);
                                                TituloMovimientos.setForeground(Color.DARK_GRAY);
                                                TiempoMovimientos.remove(nuevop);
                                                TiempoMovimientos.remove(TituloTiempo);
                                                TiempoMovimientos.remove(TituloMovimientos);
                                                TiempoMovimientos.remove(espacio1);
                                                TiempoMovimientos.remove(espacio2);
                                                TiempoMovimientos.remove(MiniImagen);
                                                TiempoMovimientos.add(LabelPause);
                                                TiempoMovimientos.add(TiempoPause);
                                                TiempoPause.setForeground(Color.YELLOW);                                                                                              
					}					
				}else if(Boton1.getName().equals("Play")){
					if(Tabla != null){
						Cronometro.start();
						Boton1.setIcon(new ImageIcon(IconoPause.getScaledInstance(DimensionIcono, DimensionIcono, Image.SCALE_SMOOTH)));
						Boton1.setName("Pause");
						Tabla.setVisible(true);
                                                Tiempo.setForeground(Color.WHITE);
                                                Movimientos.setForeground(Color.WHITE);
                                                TituloTiempo.setForeground(Color.ORANGE);
                                                TituloMovimientos.setForeground(Color.ORANGE);
                                                TiempoMovimientos.remove(TiempoPause);
                                                TiempoMovimientos.add(MiniImagen);
                                                //TiempoMovimientos.add(espacio1);
                                                TiempoMovimientos.add(nuevop);
		                                TiempoMovimientos.add(TituloTiempo);
		                                TiempoMovimientos.add(Tiempo);
                                                TiempoMovimientos.add(espacio2);
		                                TiempoMovimientos.add(TituloMovimientos);
		                                TiempoMovimientos.add(Movimientos);
                                                TiempoMovimientos.remove(LabelPause);
					}
				}else if(Boton1.getName().equals("Reintentar")){
					ContarMovimientos = 0;
					Segundos = 0;
					Minutos = 0;
					Horas = 0;
					Tiempo.setText("       "+Horas+" : "+Minutos+" : "+Segundos+"       ");
					Movimientos.setText(" "+ContarMovimientos+" ");
					if(Tabla != null)
						Tabla.TableroMensajero();
					
				}else if(Boton1.getName().equals("Nuevo")){
					IniciarPuzzle start = new IniciarPuzzle();
                                        np++;
                                        String uno=Integer.toString(np);
                                        nuevop.setText("  Jugador "+uno);
				}else if(Boton1.getName().equals("TOP5")){
					TablaGanadores muestre = new TablaGanadores();
                                       
				}
                                
			}else if(comp instanceof Timer){
				Segundos ++;
				if(Segundos == 60){
					Segundos = 0;
					Minutos ++;
					if(Minutos == 60){
						Minutos = 0;
						Horas ++;
					}
				}
				Tiempo.setText("       "+Horas+" : "+Minutos+" : "+Segundos+"       ");				
			}
		}		
	}
	public static void Iniciar(BufferedImage img, int level, BufferedImage mini){
		MiniImagen.setIcon(new ImageIcon(mini));
		if(Tabla != null)
			Contenedor.remove(Tabla);
		Tabla = new Tablero(level, img);
		Cronometro.start();
		Contenedor.remove(puzzelArea);
		Contenedor.add(Tabla, BorderLayout.EAST);
		Contenedor.validate();
	}
	public static Tablero getTabla() {
		return Tabla;
	}
	public static void Agregar(){
		ContarMovimientos ++;
		Movimientos.setText(" "+ContarMovimientos+" ");
	}
	public static Timer getTemporizador(){
		return Cronometro;
	}
	public static Container getContenedor(){
		return Contenedor;
	}
}
