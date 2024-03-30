import java.util.HashMap;
import java.util.Map;

public class ExactKWindow {
    // https://leetcode.com/problems/subarrays-with-k-different-integers/
    public int subarraysWithKDistinct(int[] nums, int k) {
        // 最多包含 K 个不同整数的子区间的个数 - 最多包含 K - 1 个不同整数的子区间的个数
        return (int) (atMostK(nums, k) - atMostK(nums, k - 1));
    }

    long atMostK(int[] nums, int k) {
        // 恰好k個，可以轉化成 最多k個 - 最多k - 1個這是難點
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0;
        long ans = 0;
        for (int r = 0; r < nums.length; r++) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.size() > k) {
                int t = map.get(nums[l]) - 1;
                if (t == 0) {
                    map.remove(nums[l]);
                } else {
                    map.put(nums[l], t);
                }
                l++;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
