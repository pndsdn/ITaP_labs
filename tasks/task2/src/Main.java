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
}