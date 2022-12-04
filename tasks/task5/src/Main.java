import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n-----1-----");
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(new int[] {72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println("\n-----2-----");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println("\n-----3-----");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println("\n-----4-----");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
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

    //5_2
    public static boolean canMove(String role, String sPos, String ePos) {
        char sX = sPos.charAt(0);
        int sY = (int) sPos.charAt(1);
        char eX = ePos.charAt(0);
        int eY = (int) ePos.charAt(1);

        if ("Pawn".equals(role)) {
            if (sX == eX && eY - sY == 1) {
                return true;
            }
            if (sX == eX && eY - sY == 2 && sY == 2) {
                return true;
            }
        }

        if ("King".equals(role)) {
            return (Math.abs((int) sX - (int) eX) <= 1 || Math.abs(sY - eY) <= 1);
        }

        if ("Knight".equals(role)) {
            return (
                    (Math.abs((int) sX - (int) eX) == 2 && Math.abs(sY - eY) == 1) ||
                    (Math.abs((int) sX - (int) eX) == 1 && Math.abs(sY - eY) == 2)
            );
        }

        if (sX == eX && sY != eY || sX != eX && sY == eY) {
            return ("Queen".equals(role) || "Rook".equals(role));
        }

        if (Math.abs((int) sX - (int) eX) == Math.abs(sY - eY)) {
            return ("Queen".equals(role) || "Bishop".equals(role));
        }

        return false;
    }

    // 5_3
    public static boolean canComplete(String str1, String str2) {
        int j = 0;
        for (int i = 0; i < str2.length(); ++i) {
            if (str1.charAt(j) == str2.charAt(i)) {
                ++j;
                if (j == str1.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    // 5_4
    public static int sumDigProd(int... nums) {
        int num = 0;
        for (int n : nums) {
            num += n;
        }

        String numStr = String.valueOf(num);
        int count = 0;
        while (num / 10 != 0) {
            num = 1;
            for (int i = 0; i < numStr.length(); ++i) {
                num *= Integer.parseInt(String.valueOf(numStr.charAt(i)));
            }
            numStr = String.valueOf(num);
            count = num;
        }
        
        return count;
    }
}