public class Main {
    public static void main(String[] args) {
        //Bessie(10, 7, "hello my name is Bessie and this is my essay");
        System.out.println(toCamelCase("is_modal_open"));
        System.out.println(toSnakeCase("isModalOpen"));
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

    // 4_2
    public static String[] split(String str) {

        return null;
    }

    // 4_3
    public static String toCamelCase(String str) {
        String res = "";
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) != '_') {
                res += str.charAt(i);
            }
            else {
                res += (char)((int) str.charAt(i+1) - 32);
                ++i;
            }
        }
        return res;
    }

    public static String toSnakeCase(String str) {
        String res = "";
        for (int i = 0; i < str.length(); ++i) {
            if ((int) str.charAt(i) >= 65 && (int) str.charAt(i) <= 90) {
                res += "_" + (char)((int) str.charAt(i) + 32);
            }
            else {
                res += str.charAt(i);
            }
        }
        return res;
    }
}