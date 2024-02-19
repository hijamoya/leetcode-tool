public class ZAlgorithm {

    /**
     * 对于一个长度为 n 的字符串 s，定义函数 z[i] 表示 s 和 s[i,n-1]（即以 s[i] 开头的后缀）
     * 的最长公共前缀（LCP）的长度，则 z 被称为 s 的 Z 函数。特别地，z[0] = 0。
     * @param s
     * @return
     */
    int[] getZ(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && t[z[i]] == t[i + z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
        }
        // z[0] = 0;
        z[0] = n;
        return z;
    }

    void isPrefixSuffixSame(int n, int[] z) {
        for (int i = 0; i < n; i ++) {
            if (z[n - 1 - i] == i + 1) { // t 的长为 i+1 的前后缀相同
                // 代表此時含有一個相同的前後綴s.substring(0, i + 1)
            }
        }
    }

}
