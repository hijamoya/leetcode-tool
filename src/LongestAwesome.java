import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/find-longest-awesome-substring
public class LongestAwesome {
    // 把每個數字壓縮到 2^n 裡面，使得xor不會相互影響

    public int longestAwesome(String s) {
        // 無法用數字，用出現次數來維護，先行轉化，用數字會互相影響
        // 如果讓每個人不會互相影響就沒事了 => 轉化為mask
        // 難的就在轉化，不過是確認奇偶性，應該能很快的想到xor + 互不相影響壓縮
        int xor = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - '0';
            int mask = 1 << index;
            xor = xor ^ mask;
            if (map.containsKey(xor)) {
                ans = Math.max(ans, i - map.get(xor));
            }
            for (int j = 0; j <= 9; j++) {
                int r = 1 << j;
                int k = xor ^ r;
                if (map.containsKey(k)) {
                    ans = Math.max(ans, i - map.get(k));
                }
            }
            if (!map.containsKey(xor)) {
                map.put(xor, i);
            }
        }
        return ans;
    }
}
