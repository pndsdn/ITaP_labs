public class Main {
    public static void main(String[] args) {

    }

    // 3_1
    public static int solutions(double a, double b, double c) {
        double D = b*b - 4*a*c;
        if (D>0) {
            return 2;
        }
        else if (D == 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}