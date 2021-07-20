import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Algorithmen zur Anwendung von Filtern auf ein Picture
 * z.B. drehen, spiegeln usw.
 *
 * @author S. Gebert
 * @version 06.2021
 */
public class Mehrpixeloperationen
{
   
    public Picture faltung(Picture originalBild, double[][] filter) 
    {
        Color[][] pixel = originalBild.getPixelsTableColor();
        Color[][] pixelNeu = pixel.clone();
        int laenge = filter.length;
        int halb = laenge/2;
        for (int x = halb; x < originalBild.getWidth() - halb; x++){
            for (int y = halb; y < originalBild.getHeight() -halb; y++){
                double rot = 0.0;
                double gruen = 0.0;
                double blau = 0.0;
                int xx = x - halb;
                int yy = y - halb;
                for (int i = 0; i < laenge; i++) {
                    for (int j = 0; j < laenge; j++){
                        rot += filter[i][j] * pixel[xx+i][yy+j].getRed();
                        gruen += filter[i][j] * pixel[xx+i][yy+j].getGreen();
                        blau += filter[i][j] * pixel[xx+i][yy+j].getBlue();
                    }
                }
                if (rot < 0.0)   rot = 0.0;
                if (rot > 1.0)   rot = 1.0;
                if (gruen < 0.0) gruen = 0.0;
                if (gruen > 1.0) gruen = 1.0;
                if (blau < 0.0)  blau = 0.0;
                if (blau > 1.0)  blau = 1.0;
                
                pixelNeu[x][y] = new Color( rot, gruen, blau, 1.0);
            }
        }
        Picture neuesBild = originalBild.copy();
        neuesBild.setPixels(pixelNeu);
        return neuesBild;
    }
}
