/*
* class Palindrome checks the string is a palindrome
*/
public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            String s = args[i];
            if (isPalindrome(s)) {
                System.out.println("\"" + s + "\"" + " is a palindrome");
            } else {
                System.out.println("\"" + s + "\"" + " is not a palindrome");
            }
        }
    }

    // returns reverse string
    public static String reverseString(String s) {
        String res = "";
        for (int i = s.length() - 1; i >= 0; --i) {
            res += s.charAt(i);
        }
        return res;
    }

    // returns true if the string is palindrome
    public static boolean isPalindrome(String s) {
        String reverse = reverseString(s);
        if (s.equals(reverse)) {
            return true;
        }
        return false;
    }
}
