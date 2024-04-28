public class BaseKConvert {
    public String baseK(int n, int k) {
        if (n == 0) {
            return "0";
        }
        StringBuilder b = new StringBuilder();
        while (n != 0) {
            int r = n % k;
            if (r >= 0) {
                b.append(String.valueOf(r));
                n = n / k;
            } else {
                r += -k;
                b.append(String.valueOf(r));
                n = n / k;
                n++;
            }
        }
        return b.reverse().toString();
    }
}
