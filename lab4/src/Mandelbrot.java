import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    //set initial range (-2 - 1.5i) - (1 + 1.5i)
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    @Override
    public int numIterations(double x, double y) {
        return 0;
    }
}
