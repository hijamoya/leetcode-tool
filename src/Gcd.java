public class Gcd {
    long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }

    long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
