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
}