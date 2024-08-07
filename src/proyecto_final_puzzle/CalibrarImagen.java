package proyecto_final_puzzle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
public class CalibrarImagen {
	public  static BufferedImage AjustarImagen(BufferedImage image, int ancho, int alto) {
       int tipo = 0;
       tipo = image.getType() == 0? BufferedImage.TYPE_INT_ARGB : image.getType();
       BufferedImage resizedImage = new BufferedImage(ancho, alto,tipo);
       Graphics2D g = resizedImage.createGraphics();
       g.drawImage(image, 0, 0, ancho, alto, null);
       g.dispose();
       return resizedImage;
    }
}
