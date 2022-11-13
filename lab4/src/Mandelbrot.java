import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

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
        double currentX = 0;
        double currentIY = 0;
        int iCount = 0;
        while (iCount < MAX_ITERATIONS) {
            ++iCount;
            double realPart = currentX * currentX - currentIY * currentIY;
            double imaginaryPart = 2 * currentX * currentIY;
            currentX = realPart + x;
            currentIY = imaginaryPart + y;

            if (currentX * currentX + currentIY * currentIY <= 4) {
                break;
            }
        }

        if (iCount == MAX_ITERATIONS) {
            return -1;
        }
        else {
            return iCount;
        }
    }
}
