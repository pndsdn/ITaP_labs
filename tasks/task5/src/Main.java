import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n-----1-----");
        System.out.println(encrypt("Hello"));
        System.out.println(decrypt(new int[] {72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println("\n-----2-----");
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println("\n-----3-----");
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));
        System.out.println("\n-----4-----");
        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));
        System.out.println("\n-----5-----");
        System.out.println(sameVowelGroup(new String[] {"toe", "ocelot", "maniac"}));
        System.out.println(sameVowelGroup(new String[] {"many", "carriage", "emit", "apricot", "animal"}));
        System.out.println(sameVowelGroup(new String[] {"hoops", "chuff", "bot", "bottom"}));
        System.out.println("\n-----6-----");
        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));
        System.out.println("\n-----7-----");
        System.out.println(numToEng(0));
        System.out.println(numToEng(18));
        System.out.println(numToEng(126));
        System.out.println(numToEng(909));
    }

    // 5_1
    public static ArrayList<Integer> encrypt(String str) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add((int) str.charAt(0));

        for(int i = 1; i < str.length(); ++i) {
            res.add((int) str.charAt(i) - (int) str.charAt(i-1));
        }

        return res;
    }

    public static String decrypt(int[] codes) {
        String res = "";
        res += (char)codes[0];
        int sm = 0;

        for (int i = 1; i < codes.length; ++i) {
            res += (char) (codes[i] + (sm+=codes[i-1]));
        }

        return res;
    }

    //5_2
    public static boolean canMove(String role, String sPos, String ePos) {
        char sX = sPos.charAt(0);
        int sY = sPos.charAt(1);
        char eX = ePos.charAt(0);
        int eY = ePos.charAt(1);

        if ("Pawn".equals(role)) {
            if (sX == eX && eY - sY == 1) {
                return true;
            }
            if (sX == eX && eY - sY == 2 && sY == 2) {
                return true;
            }
        }

        if ("King".equals(role)) {
            return (Math.abs((int) sX - (int) eX) <= 1 || Math.abs(sY - eY) <= 1);
        }

        if ("Knight".equals(role)) {
            return (
                    (Math.abs((int) sX - (int) eX) == 2 && Math.abs(sY - eY) == 1) ||
                    (Math.abs((int) sX - (int) eX) == 1 && Math.abs(sY - eY) == 2)
            );
        }

        if (sX == eX && sY != eY || sX != eX && sY == eY) {
            return ("Queen".equals(role) || "Rook".equals(role));
        }

        if (Math.abs((int) sX - (int) eX) == Math.abs(sY - eY)) {
            return ("Queen".equals(role) || "Bishop".equals(role));
        }

        return false;
    }

    // 5_3
    public static boolean canComplete(String str1, String str2) {
        int j = 0;
        for (int i = 0; i < str2.length(); ++i) {
            if (str1.charAt(j) == str2.charAt(i)) {
                ++j;
                if (j == str1.length()) {
                    return true;
                }
            }
        }

        return false;
    }

    // 5_4
    public static int sumDigProd(int... nums) {
        int num = 0;
        for (int n : nums) {
            num += n;
        }

        String numStr = String.valueOf(num);
        int count = 0;
        while (num / 10 != 0) {
            num = 1;
            for (int i = 0; i < numStr.length(); ++i) {
                num *= Integer.parseInt(String.valueOf(numStr.charAt(i)));
            }
            numStr = String.valueOf(num);
            count = num;
        }

        return count;
    }

    // 5_5
    public static ArrayList<String> sameVowelGroup(String[] words) {
        ArrayList<String> res = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        res.add(words[0]);

        ArrayList<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));
        for (char i : words[0].toCharArray()) {
            if (vowels.contains(i)) {
                set.add(i);
            }
        }

        for (int i = 1; i < words.length; ++i) {
            Set<Character> vow = new HashSet<>();
            for (char ch : words[i].toCharArray()) {
                if (vowels.contains(ch)) {
                    vow.add(ch);
                }
            }
            if (vow.equals(set)) {
                res.add(words[i]);
            }
        }

        return res;
    }

    // 5_6
    public static boolean validateCard(Long num) {
        String strNum = num.toString();
        if (strNum.length() < 14 || strNum.length() > 19){
            return false;
        }

        Long controlNum = num % 10;
        num /= 10;
        strNum = num.toString();

        String rStrNum = "";
        for (int i = strNum.length()-1; i >= 0; --i) {
            rStrNum += strNum.charAt(i);
        }

        int sum = 0;
        for(int i = 0; i < rStrNum.length(); ++i) {
            int iNum = rStrNum.charAt(i);
            if (i % 2 == 0) {
                iNum *= 2;

                if (iNum / 10 != 0) {
                    iNum = iNum / 10 + iNum % 10;
                }
            }
            sum += iNum;
        }

        return (10 - sum % 10 == controlNum);
    }

    // 5_7
    public static String numToEng(int num) {
        class NumToStringConvertor {
            private String unitsToString(int n) {
                return switch (n) {
                    case 1 -> "one";
                    case 2 -> "two";
                    case 3 -> "three";
                    case 4 -> "four";
                    case 5 -> "five";
                    case 6 -> "six";
                    case 7 -> "seven";
                    case 8 -> "eight";
                    case 9 -> "nine";
                    default -> null;
                };
            }

            private String tensToString(int n) {
                return switch (n) {
                    case 2 -> "twenty";
                    case 3 -> "thirty";
                    case 4 -> "forty";
                    case 5 -> "fifty";
                    case 6 -> "sixty";
                    case 7 -> "seventy";
                    case 8 -> "eighty";
                    case 9 -> "ninety";
                    default -> null;
                };
            }

            private String firstTenToString(int n) {
                return switch (n) {
                    case 0 -> "ten";
                    case 1 -> "eleven";
                    case 2 -> "twelve";
                    case 3 -> "thirteen";
                    case 4 -> "fourteen";
                    case 5 -> "fifteen";
                    case 6 -> "sixteen";
                    case 7 -> "seventeen";
                    case 8 -> "eighteen";
                    case 9 -> "nineteen";
                    default -> null;
                };
            }
        }

        if (num == 0) {
            return "zero";
        }

        final int hundreds = num / 100;
        final int tens = (num / 10) % 10;
        final int units = num % 10;

        StringBuilder res = new StringBuilder();
        NumToStringConvertor convertor = new NumToStringConvertor();

        String sHundreds = convertor.unitsToString(hundreds);
        String sTens;
        String sUnits;
        if (tens == 1) {
            sTens = convertor.firstTenToString(units);
            sUnits = null;
        }
        else {
            sTens = convertor.tensToString(tens);
            sUnits = convertor.unitsToString(units);
        }

        if (sHundreds != null) {
            res.append(sHundreds);
            res.append(" hundred");
        }
        if (sTens != null) {
            if (sHundreds != null) res.append(" ");
            res.append(sTens);
        }
        if (sUnits != null) {
            if (sHundreds != null || sTens != null) res.append(" ");
            res.append(sUnits);
        }

        return res.toString();
    }

    // 5_8
    public static String getSha256Hash(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }
}