import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Point3d[] threePoints = new Point3d[3];
        Scanner sc = new Scanner(System.in);
        // entering coordinates of 3 Point3D objects
        for (int i = 0; i < 3; ++i) {
            double[] point = new double[3];
            for (int j = 0; j < 3; ++j) {
                System.out.print("point" + i + " " + (char)(120+j) + "Coord: ");
                point[j] = sc.nextDouble();
            }
            System.out.println();
            threePoints[i] = new Point3d(point[0], point[1], point[2]);
        }
        // equal points check
        if (!(threePoints[0].equal(threePoints[1]) ||
                threePoints[1].equal(threePoints[2]) ||
                threePoints[2].equal(threePoints[0]))) {
            double res = computeArea(threePoints[0], threePoints[1], threePoints[2]);
            System.out.println(res);
        }
        else {
            System.out.println("Some points are equal!");
        }
        sc.close();
    }

    public static double computeArea(Point3d P1, Point3d P2, Point3d P3) {
        double a = P1.distanceTo(P2);
        double b = P2.distanceTo(P3);
        double c = P3.distanceTo(P1);
        double p = (a + b + c) / 2;
        return  (Math.round(Math.sqrt(p * (p - a) * (p - b) * (p - c)) * 100) /100.0);
    }
}
