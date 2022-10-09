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

    // 3_2
    public static int findZip(String s) {
        int iZip = s.indexOf("zip");
        String sc = s.substring(iZip+3);
        System.out.println(sc);
        if (!sc.contains("zip")) {
            return -1;
        }
        return sc.indexOf("zip")+iZip+3;
    }
}