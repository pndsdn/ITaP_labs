public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static int remainder(int num1, int num2) {
        return (num1 % num2);
    }

    public static double triArea(double b, double h) {
        return ((b * h)/2);
    }

    public static int animals(int chickens, int cows, int pigs) {
        return (chickens*2 + cows*4 + pigs*4);
    }

    public static boolean profitableGamble(double prob, double prize, double pay) {
        return (prob * prize - pay > 0);
    }

    public static String operator(int N, int a, int b) {
        if (a+b == N) {
            return "added";
        }
        else if (a-b == N) {
            return "subtracted";
        }
        else if (a*b == N) {
            return "multiplied";
        }
        else if (a/b == N) {
            return "divided";
        }
        else {
            return "none";
        }
    }

    public static int ctoa(char c) {
        return (int)(c);
    }

    public static int addUpTo(int num) {
        int res = 0;
        for (int i = 1; i <= num; ++i) {
            res += i;
        }
        return res;
    }

    public static int nextEdge(int a, int b) {
        return (a+b-1);
    }

    public static int sumOfCubes(int[] arrOfNums) {
        int res = 0;
        for (int num : arrOfNums) {
            res += Math.pow(num, 3);
        }
        return res;
    }

    public static boolean abcmath(int a, int b, int c) {
        for (int i = 0; i < b; ++i) {
            a += a;
        }
        return (a % c == 0);
    }
}