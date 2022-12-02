import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    /*
        Set initial range from (-2, - 2) to (2, 2)
     */
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2;
        range.height = 4;
        range.width = 4;
    }

    /*
        tricorn function : z_n = z_{n-1}^2 + C
        z_0 = 0
     */
    @Override
    public int numIterations(double x, double y) {
        double currentX = 0;
        double currentIY = 0;
        int iCount = 0;
        while (iCount < MAX_ITERATIONS) {
            ++iCount;
            double realPart = currentX * currentX - currentIY * currentIY + x;
            double imaginaryPart = -(2 * currentX * currentIY) + y;
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
        return "Tricorn";
    }
}
