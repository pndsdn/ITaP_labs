public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
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
        for(int i = 0; i < arrOfNums.length; ++i) {
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
}