import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator {
    public static final int MAX_ITERATIONS = 2000;

    /*
        Set initial range from (-2, -2.5) to (2, 1.5)
     */
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2.5;
        range.height = 4;
        range.width = 4;
    }

    /*
        burning ship function: z_n = (|Re(z_{n-1})| + i |Im(z_{n-1})|)^2 + c
     */
    @Override
    public int numIterations(double x, double y) {
        double currentIY = 0;
        double currentX = 0;
        int iCount = 0;
        while (iCount < MAX_ITERATIONS) {
            ++iCount;
            double realPart = currentX * currentX - currentIY * currentIY + x;
            double imaginaryPart = Math.abs(2 * currentX * currentIY) + y;
            currentX = realPart;
            currentIY = imaginaryPart;
            if ((currentX * currentX + currentIY * currentIY) > 4)
                break;
        }
        if (iCount == MAX_ITERATIONS) {
            return -1;
        } else {
            return iCount;
        }
    }

    @Override
    public String toString() {
        return "Burning Ship";
    }
}
