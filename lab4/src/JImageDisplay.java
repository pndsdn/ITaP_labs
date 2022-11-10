import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends JComponent{
    private BufferedImage image;

    public JImageDisplay(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Dimension prefferedSize = new Dimension(width, height);
        super.setPreferredSize(prefferedSize);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    // set the color of all points in the image to 0
    public void clearImage() {
        for (int w = 0; w < image.getWidth(); ++w) {
            for (int h = 0; h < image.getHeight(); ++h) {
                image.setRGB(w, h, 0);
            }
        }
    }
}