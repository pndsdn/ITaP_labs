import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        Bessie(10, 7, "hello my name is Bessie and this is my essay");
//        System.out.println(toCamelCase("is_modal_open"));
//        System.out.println(toSnakeCase("isModalOpen"));
//        System.out.println(overTime(new double[] {13.25, 15, 30, 1.5}));
//        System.out.println(BMI("154 pounds", "2 meters"));
//        System.out.println(bugger(39));
//        System.out.println(toStarShorthand("77777geff"));
//        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));


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

    // 4_4
    public static String overTime(double[] arr) {
        double res;
        if (arr[1] < 17) {
            res = (arr[1]-arr[0])*arr[2];
        }
        else {
            res = (17-arr[0])*arr[2] + (arr[1]-17)*arr[2]*arr[3];
        }
        return "$" + String.valueOf(res) + "0";
    }

    // 4_5
    public static String BMI(String w, String h) {
        double weight = Double.parseDouble(w.substring(0, w.lastIndexOf(" ")));
        if(w.endsWith("pounds")){
            weight = weight * 0.453592;
        }

        double height = Double.parseDouble(h.substring(0, h.lastIndexOf(" ")));
        if(h.endsWith("inches")){
            height = height * 0.0254;
        }

        double bmi = weight/(height*height);
        if (bmi < 18.5) {
            return String.format("%.1f", bmi) + " Underweight";
        }
        else if (bmi >= 25) {
            return String.format("%.1f", bmi) + " Overweight";
        }
        return String.format("%.1f", bmi) + " Normal weight";
    }

    // 4_6
    public static int bugger (int num) {
        String numStr = String.valueOf(num);
        int count = 0;
        while (num / 10 != 0) {
            num = 1;
            for (int i = 0; i < numStr.length(); ++i) {
                num *= Integer.parseInt(String.valueOf(numStr.charAt(i)));
            }
            numStr = String.valueOf(num);
            ++count;
        }
        return count;
    }

    // 4_7
    public static String toStarShorthand(String str) {
        String res = "";
        int count = 1;
        int i;
        if (str.length() == 0) {
            return "";
        }
        for (i = 0; i < str.length()-1; ++i) {
            if (str.charAt(i) == str.charAt(i+1)) {
                ++count;
            }
            else {
                res += str.charAt(i);
                if (count != 1) {
                    res += "*" + Integer.toString(count);
                    count = 1;
                }
            }
        }

        res += str.charAt(i);
        if (count != 1) {
            res += "*" + Integer.toString(count);
            count = 1;
        }

        return res;
    }

    // 4_8
    public static boolean doesRhyme (String str1, String str2) {
        String word1 = str1.split(" ")[str1.split(" ").length-1].toLowerCase();
        String word2 = str2.split(" ")[str2.split(" ").length-1].toLowerCase();

        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < word1.length(); ++i) {
            for (int j = 0; j < vowels.length; ++j) {
                if (word1.charAt(i) == vowels[j]) {
                    list1.add((int)vowels[j]);
                }
            }
        }

        for (int i = 0; i < word2.length(); ++i) {
            for (int j = 0; j < vowels.length; ++j) {
                if (word2.charAt(i) == vowels[j]) {
                    list2.add((int)vowels[j]);
                }
            }
        }
        return list1.equals(list2);
    }
}