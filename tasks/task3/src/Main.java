import java.util.*;

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

    // 3_5
    public static boolean isValidHexCode(String code) {
        if (!code.startsWith("#")) {
            return false;
        }
        if (code.length() != 7) {
            return false;
        }
        for (int i = 1; i < code.length(); ++i) {
            if (!(((int)code.charAt(i) >= 48 && (int)code.charAt(i) <= 57) ||
                    ((int)code.charAt(i) >= 65 && (int)code.charAt(i) <= 70) ||
                    ((int)code.charAt(i) >= 97 && (int)code.charAt(i) <= 102))) {
                return false;
            }
        }
        return true;
    }

    // 3_6
    public static boolean same(int[] arr1, int[] arr2) {
        List<Integer> uniqueArr1 = new ArrayList<Integer>();
        List<Integer> uniqueArr2 = new ArrayList<Integer>();
        for (int num : arr1) {
            if (!(uniqueArr1.contains(num))) {
                uniqueArr1.add(num);
            }
        }
        for (int num : arr2) {
            if (!(uniqueArr2.contains(num))) {
                uniqueArr2.add(num);
            }
        }
        return uniqueArr1.size() == uniqueArr2.size();
    }

    // 3_7
    public static boolean isKaprekar(int num) {
        String sNum = Integer.toString(num*num);
        if (sNum.length() < 2) {
            return num == num*num;
        }
        String left = "";
        String right = "";
        for (int i = 0; i < sNum.length() / 2; ++i) {
            left += sNum.charAt(i);
        }
        right = sNum.substring(left.length());
        return Integer.parseInt(left) + Integer.parseInt(right) == num;
    }

    // 3_8
    public static String longestZero(String s) {
        String maxChain = "";
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                ++count;
                if (maxChain.length() < count) {
                    maxChain += "0";
                }
            }
            else {
                count = 0;
            }
        }
        return maxChain;
    }

    // 3_9
    public static int nextPrime(int num) {
        for (int i = num; i < num*num; ++i) {
            if (isPrime(i)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}