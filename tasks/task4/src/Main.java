public class Main {
    public static void main(String[] args) {
        Bessie(10, 7, "hello my name is Bessie and this is my essay");
    }

    // 4_1
    public static void Bessie(int n, int k, String str) {
        String[] words = str.split(" ");
        String currWord = words[0];
        int currLen = currWord.length();

        for (int i = 1; i < n; ++i) {
            if ((currLen += words[i].length()) <= k) {
                currWord += " " + words[i];
            }
            else {
                System.out.println(currWord);
                currWord = words[i];
                currLen = currWord.length();
            }
        }

        if (currLen < k) {
            System.out.println(currWord);
        }

    }
}