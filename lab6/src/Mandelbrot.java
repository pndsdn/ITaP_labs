import java.awt.geom.Rectangle2D;

public class Mandelbrot extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    /*
        Set initial range (-2 - 1.5i) - (1 + 1.5i)
     */
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -1.5;
        range.height = 3;
        range.width = 3;
    }

    /*
        mandelbrot function : z_n = z_{n-1}^2 + C
        z_0 = 0
        |z| < 2^2
        z^2 = x^2 + 2xiy - y^2
     */
    @Override
    public int numIterations(double x, double y) {
        double currentX = 0;
        double currentIY = 0;
        int iCount = 0;
        while (iCount < MAX_ITERATIONS) {
            ++iCount;
            double realPart = currentX * currentX - currentIY * currentIY + x;
            double imaginaryPart = 2 * currentX * currentIY + y;
            currentX = realPart;
            currentIY = imaginaryPart;

            if (currentX * currentX + currentIY * currentIY > 4) {
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

    @Override
    public String toString() {
        return "Mandelbrot";
    }
}
