import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarehouseSelection {

    // 給一個array，算出他所有的數到target index距離之和
    // 可以用來做中位數貪心 計算所有數字到中位數之和
    // https://leetcode.cn/problems/minimum-operations-to-make-all-array-elements-equal/description/
    int distance(int[] nums, int index) {
        Arrays.sort(nums);
        int[] prefix = new int[nums.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        int leftCount = index;
        int rightCount = nums.length - index;
        int s1 = leftCount * nums[index] - (prefix[index] - prefix[0]);
        int s2 = prefix[prefix.length - 1] - prefix[index] - rightCount * nums[index];
        return s1 + s2;
    }

    public long minimumMoves(int[] nums, int k, int m) {
        // 分析：
        // 1. 左右鄰居的，可以花費一次就撿到
        // 2. 其他的最佳狀況，就是使用maxChanges變成1之後移過來，因此花費 2 * maxChanges
        // 3. 如果maxChanges用完了，就只能慢慢地移過來
        // 我們要先找一個最佳起始點 1個1 / 2個1 / 3個1
        int c = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                c = Math.max(c, 1);
                if (i > 0 && nums[i - 1] == 1) {
                    c = Math.max(c, 2);
                }
                if (i > 1 && nums[i - 1] == 1 && nums[i - 2] == 1) {
                    c = Math.max(c, 3);
                }
            }
        }
        // 然後我們最多就可以用c - 1次撿到c個1，剩下的就討論maxChanges
        // 如果 maxChanges >= k - c => 代表可以直接撿完
        c = Math.min(c, k);
        if (m >= k - c) {
            return Math.max(c - 1, 0) + 2 * (k - c);
        }
        // 如果maxChanges不夠，剩下的就必須用移動的
        // 問題就變成，選哪個位置移動比較好 => 中位數貪心
        // 我們剩下k - maxChanges需要用移動的 其餘的用 2 * maxChanges來取得
        int remain = k - m;
        // 那麼問題就變成，選哪個位置 取得remain個1是最佳的 => 中位數貪心 貨倉選址
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                positions.add(i);
            }
        }
        long[] prefix = new long[positions.size() + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + positions.get(i - 1);
        }
        long ans = Long.MAX_VALUE;
        // 每個remain window 都進行一次貨倉選址
        for (int i = 0; i <= positions.size() - remain; i++) {
            // 中位數的位置
            int mid = i + remain / 2;
            long value = positions.get(mid);
            int leftCount = mid - i;
            int rightCount = remain + i - mid;
            long s1 = leftCount * value - (prefix[mid] - prefix[i]);
            long s2 = prefix[remain + i] - prefix[mid] - rightCount * value;
            ans = Math.min(ans, s1 + s2);
        }
        return ans + 2 * m;
    }

}
