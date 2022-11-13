import java.awt.geom.Rectangle2D;
public class FractalExplorer {
    private int displaySize;
    private JImageDisplay displayImage;
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double complexAreaRange;

    public FractalExplorer (int size) {
        displaySize = size;
        fractalGenerator = new Mandelbrot();
        complexAreaRange = new Rectangle2D.Double(0, 0, 0, 0);
        fractalGenerator.getInitialRange(complexAreaRange);
    }
}
