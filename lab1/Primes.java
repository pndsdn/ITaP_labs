/*
* class Primes has public method isPrime() to check number is prime
*/
public class Primes {
    public static void main(String[] args) {
        for (int i = 2; i <= 100; ++i) {
            if (isPrime(i)) {
                System.out.println(i);
            }
        }
    }

    // checking a number is prime
    public static boolean isPrime(int n) {
        for (int i = 2; i < n; ++i) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
