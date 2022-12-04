import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(new int[] {72, 33, -73, 84, -12, -3, 13, -13, -68}));
    }

    // 5_1
    public static ArrayList<Integer> encrypt(String str) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add((int) str.charAt(0));

        for(int i = 1; i < str.length(); ++i) {
            res.add((int) str.charAt(i) - (int) str.charAt(i-1));
        }

        return res;
    }

    public static String decrypt(int[] codes) {
        String res = "";
        res += (char)codes[0];
        int sm = 0;

        for (int i = 1; i < codes.length; ++i) {
            res += (char) (codes[i] + (sm+=codes[i-1]));
        }

        return res;
    }
}