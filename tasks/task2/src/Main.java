public class Main {
    public static void main(String[] args) {

    }

    // 2_1
    public static String repeat(String s, int n) {
        String res = "";
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < n; ++j) {
                res += s.charAt(i);
            }
        }
        return res;
    }

    // 2_2
    public static int differenceMaxMin(int[] arrOfNums) {
        double minNum = Double.POSITIVE_INFINITY;
        double maxNum = Double.NEGATIVE_INFINITY;
        for (int num : arrOfNums) {
            if (num < minNum) {
                minNum = num;
            }
            if (num > maxNum) {
                maxNum = num;
            }
        }
        return (int)(maxNum - minNum);
    }

    // 2_3
    public static boolean isAvgWhole(int[] arrOfNums) {
        int res = 0;
        for (int num : arrOfNums) {
            res += num;
        }
        return (res % arrOfNums.length == 0);
    }

    // 2_4
    public static void cumulativeSum(int[] arrOfNums) {
        int subtotal = 0;
        for (int i = 0; i < arrOfNums.length; ++i) {
            arrOfNums[i] += subtotal;
            subtotal = arrOfNums[i];
        }
    }

    // 2_5
    public static int getDecimalPlaces(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '.') {
                return (s.length() - 1 - i);
            }
        }
        return 0;
    }

    // 2_6
    public static int Fibonacci(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return (Fibonacci(n - 2) + Fibonacci(n - 1));
    }

    // 2_7
    public static boolean isValid(String s) {
        if (s.length() > 5) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            if ((int)(s.charAt(i)) < 48 || (int)(s.charAt(i)) > 57) {
                return false;
            }
        }
        return  true;
    }

    // 2_8
    public static boolean isStrangePair(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty()) {
            if (s1.isEmpty() && s2.isEmpty()) {
                return true;
            }
            return false;
        }

        return (s1.charAt(0) == s2.charAt(s2.length()-1) &&
                s1.charAt(s1.length()-1) == s2.charAt(0));
    }

    // 2_9
    public static boolean isPrefix(String word, String prefix) {
        if (prefix.endsWith("-")) {
            prefix = prefix.substring(0, prefix.length()-1);
            if (word.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSuffix(String word, String suffix) {
        if (suffix.startsWith("-")) {
            suffix = suffix.substring(1, suffix.length());
            if (word.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    // 2_10
    public static int boxSeq(int step) {
        if (step == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < step; ++i) {
            if (i % 2 == 0) {
                res+=3;
            }
            else {
                res-=1;
            }
        }
        return res;
    }
}