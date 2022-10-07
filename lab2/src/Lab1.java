public class Lab1 {
    public static void main(String[] args) {

    }

    public static double computeArea(Point3d P1, Point3d P2, Point3d P3) {
        double a = P1.distanceTo(P2);
        double b = P2.distanceTo(P3);
        double c = P3.distanceTo(P1);
        double p = (a + b + c) / 2;
        return (Math.sqrt(p * (p - a) * (p - b) * (p - c)));
    }
}
