package proyecto_final_puzzle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
public class CargarImagen{
	public static BufferedImage SubiendoImagen(String path){
		try {
			return ImageIO.read(CargarImagen.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
		}
}