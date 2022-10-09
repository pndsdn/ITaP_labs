import javax.swing.*;

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

    // 3_3
    public static boolean checkPerfect(int num) {
        int res = 1;
        for (int i = 2; i*i < num; ++i) {
            if (num % i == 0) {
                res += i + num/i;
            }
        }
        return num == res;
    }

    // 3_4
    public static String flipEndChars(String s) {
        if (s.length() < 2) {
            return "Incompatible.";
        }
        if (s.charAt(0) == s.charAt(s.length()-1)) {
            return "Two`s a pair.";
        }
        return s.charAt(s.length()-1) + s.substring(1, s.length()-1) + s.charAt(0);
    }
}