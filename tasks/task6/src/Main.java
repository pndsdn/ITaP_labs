import javax.swing.text.AttributeSet;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------1-------");
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));
        System.out.println("-------2-------");
        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println("-------3-------");
        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));
        System.out.println("-------4-------");
        System.out.println(stripUrlParser("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParser("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParser("https://edabit.com", new String[]{"b"}));
        System.out.println("-------5-------");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        System.out.println(Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));
        System.out.println("-------6-------");
        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));
        System.out.println("-------7-------");
        System.out.println(longestNonrepeatingString("abcbabcbb"));
        System.out.println(longestNonrepeatingString("aaaaaaa"));
        System.out.println(longestNonrepeatingString("abcde"));
        System.out.println(longestNonrepeatingString("abcda"));
        System.out.println("-------8-------");
        System.out.println(converttoRoman(2));
        System.out.println(converttoRoman(12));
        System.out.println(converttoRoman(16));
        System.out.println("-------9-------");
        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 7 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));
        System.out.println("-------10-------");
        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));

    }

    // 6_1
    public static int bell(int num) {
        ArrayList<ArrayList<Integer>> bellTriangle = new ArrayList<>(num);
        ArrayList<Integer> firstArray = new ArrayList<>(1);
        firstArray.add(1);
        bellTriangle.add(firstArray);

        for (int i = 1; i < num; ++i) {
            ArrayList<Integer> newInnerArray = new ArrayList<>(i+1);
            ArrayList<Integer> lastInnerArray = bellTriangle.get(bellTriangle.size() - 1);
            newInnerArray.add(lastInnerArray.get(lastInnerArray.size() - 1));

            for (int j = 0; j < i; ++j) {
                newInnerArray.add(lastInnerArray.get(j) + newInnerArray.get(j));
            }

            bellTriangle.add(newInnerArray);
        }

        ArrayList<Integer> last = bellTriangle.get(bellTriangle.size() - 1);
        return last.get(last.size() - 1);
    }

    // 6_2
    public static String translateWord(String word) {
        Pattern pattern = Pattern.compile("\\w+");
        if (!pattern.matcher(word).matches()) {
            return "";
        }

        String vowels = "aeiouy";
        if(vowels.indexOf(Character.toLowerCase(word.charAt(0))) != -1) {
            return word + "yay";
        }

        pattern = Pattern.compile("["+vowels+"]");
        Matcher matcher = pattern.matcher(word);
        int iVowel = -1;
        if (matcher.find()) {
            iVowel = matcher.start();
        }

        if (iVowel == -1) {
            return word + "ay";
        }

        String res = word.substring(iVowel) + word.substring(0, iVowel) + "ay";
        if(Character.isUpperCase(word.charAt(0))) {
            return res.substring(0, 1).toUpperCase() + res.substring(1).toLowerCase();
        }

        return res;
    }

    public static String translateSentence(String sentence) {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(sentence);
        return matcher.replaceAll(matchResult -> translateWord(matchResult.group()));
    }

    // 6_3
    public static boolean validColor(String s) {
        Integer r = null;
        Integer g = null;
        Integer b = null;
        Double a = null;

        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(s);

        while (matcher.find()) {
            try {
                if (r == null) {
                    r = Integer.parseInt(matcher.group());
                }
                else if (g == null) {
                    g = Integer.parseInt(matcher.group());
                }
                else if (b == null) {
                    b = Integer.parseInt(matcher.group());
                }
                else if (a == null) {
                    a = Double.parseDouble(matcher.group());
                }
            } catch (Exception e) {
                return false;
            }
        }

        if (r == null || r > 255 || r < 0) {
            return false;
        }
        if (g == null || g > 255 || g < 0) {
            return false;
        }
        if (b == null || b > 255 || b < 0) {
            return false;
        }
        if (s.contains("rgba")) {
            return a != null && a <= 1 && a >= 0;
        }
        else return a == null && s.contains("rgb");
    }

    // 6_4
    public static String stripUrlParser(String url, String[] paramsToDelete) {
        String[] urlParamPair = url.split("\\?");
        if (urlParamPair.length == 1) {
            return url;
        }

        String[] params = urlParamPair[1].split("&");

        Map<String, String> paramsValuesMap = new HashMap<>();
        for (String param : params) {
            String[] paramValuePair = param.split("=");
            boolean add = true;
            for (String paramToDelete : paramsToDelete) {
                if (paramValuePair[0].equals(paramToDelete)) {
                    add = false;
                    break;
                }
            }

            if (add) {
                paramsValuesMap.put(paramValuePair[0], paramValuePair[1]);
            }
        }

        StringJoiner joiner = new StringJoiner("&");
        for (Map.Entry<String, String> entry : paramsValuesMap.entrySet()) {
            joiner.add(entry.getKey() + "=" + entry.getValue());
        }

        return urlParamPair[0] + "?" + joiner;
    }

    public static String stripUrlParser(String url) {
        return stripUrlParser(url, new String[]{});
    }

    // 6_5
    public static String[] getHashTags(String s) {
        ArrayList<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()) {
            words.add(matcher.group());
        }

        String[] longestWords = new String[]{"", "", ""};

        final int iFirst = 0;
        final int iSecond = 1;
        final int iThird = 2;
        for (String word : words) {
            if (longestWords[iFirst].length() < word.length()) {
                longestWords[iThird] = longestWords[iSecond];
                longestWords[iSecond] =  longestWords[iFirst];
                longestWords[iFirst] = word;
            }
            else if (longestWords[iSecond].length() < word.length()) {
                longestWords[iThird] = longestWords[iSecond];
                longestWords[iSecond] = word;
            }
            else if (longestWords[iThird].length() < word.length()) {
                longestWords[iThird] = word;
            }
        }
        ArrayList<String> hashTags = new ArrayList<>(3);
        for (String longestWord : longestWords) {
            if (!"".equals(longestWord)) {
                hashTags.add("#" + longestWord.toLowerCase());
            }
        }

        return hashTags.toArray(new String[]{});
    }

    // 6_6
    public static int ulam(int n) {
        Map<Integer, Integer> savedSums = new TreeMap<>();
        ArrayList<Integer> ulams = new ArrayList<>();
        ulams.add(1);
        ulams.add(2);
        ulams.add(3);

        for (int i = 2; i < n - 1; ++i) {
            Integer newSum = null;

            for (int j = 0; j < i; ++j) {
                int currentSum = ulams.get(ulams.size() - 1) + ulams.get(j);
                if (newSum == null && !savedSums.containsKey(currentSum)) {
                    newSum = currentSum;
                }
                savedSums.merge(currentSum, 1, Integer::sum);
            }

            for (Map.Entry<Integer, Integer> entry : savedSums.entrySet()) {
                if (newSum > entry.getKey() && entry.getValue() == 1) {
                    newSum = entry.getKey();
                    break;
                }
            }

            ulams.add(newSum);
            savedSums.remove(newSum);
        }

        return ulams.get(n - 1);
    }

    // 6_7
    public static String longestNonrepeatingString(String s) {
        Set<Character> characters = new HashSet<>();
        StringBuilder res = new StringBuilder();
        res.append(s.charAt(0));
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(0)) {
                break;
            }

            if (characters.add(s.charAt(i))) {
                res.append(s.charAt(i));
            }
            else {
                break;
            }
        }
        return res.toString();
    }

    // 6_8
    public static String converttoRoman(int num) {
        Roman[] romans = Roman.values();
        StringBuilder res = new StringBuilder();
        for (Roman roman : romans) {
            if (num - roman.arab >= 0) {
                res.append(roman.name());
                num -= roman.arab;
            }
        }

        return res.toString();
    }

    private enum Roman {
        MMM(3000),
        MM(2000),
        M(1000),
        CM(900),
        DCCC(800),
        DCC(700),
        DC(600),
        D(500),
        CD(400),
        CCC(300),
        CC(200),
        C(100),
        XC(90),
        LXXX(80),
        LXX(70),
        LX(60),
        L(50),
        XL(40),
        XXX(30),
        XX(20),
        X(10),
        IX(9),
        VIII(8),
        VII(7),
        VI(6),
        V(5),
        IV(4),
        III(3),
        II(2),
        I(1);

        final int arab;

        Roman(int arab) {
            this.arab = arab;
        }
    }

    // 6_9
    public static boolean formula(String s) {
        String[] parts = s.split("=");

        Pattern patternDigits = Pattern.compile("\\d+");
        Matcher matcherDigits = patternDigits.matcher("");

        Pattern patternOperators = Pattern.compile("[*/+-]");
        Matcher matcherOperators = patternOperators.matcher("");

        Double res = null;

        for (String part : parts) {
            ArrayList<Integer> digits = new ArrayList<>();
            ArrayList<Character> operators = new ArrayList<>();

            matcherDigits.reset(part);
            matcherOperators.reset(part);

            while (matcherDigits.find()) {
                digits.add(Integer.parseInt(matcherDigits.group()));
            }

            while (matcherOperators.find()) {
                operators.add(matcherOperators.group().charAt(0));
            }

            if (digits.size() != operators.size() + 1) {
                return false;
            }

            double currentRes = digits.get(0);
            for (int i = 0; i < operators.size(); i++) {
                int nextInt = digits.get(i + 1);
                switch (operators.get(i)) {
                    case '*': {
                        currentRes *= nextInt;
                        break;
                    }
                    case '/': {
                        currentRes /= nextInt;
                        break;
                    }
                    case '+': {
                        currentRes += nextInt;
                        break;
                    }
                    case '-': {
                        currentRes -= nextInt;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
            if (res != null && currentRes != res) {
                return false;
            }
            res = currentRes;
        }
        return true;
    }

    // 6_10
    public static boolean palindromeDescendant(int num) {
        String strNum = String.valueOf(num);

        while (strNum.length() >= 2) {
            String reversedStrNum = new StringBuilder(strNum).reverse().toString();
            if (strNum.equals(reversedStrNum)) {
                return true;
            }

            StringBuilder newStrNum = new StringBuilder();
            for (int i = 0; i <= strNum.length() - 2; i += 2) {
                newStrNum.append(Integer.parseInt(strNum.substring(i, i + 1)) + Integer.parseInt(strNum.substring(i + 1, i + 2)));
            }
            strNum = newStrNum.toString();
        }
        return false;
    }
}