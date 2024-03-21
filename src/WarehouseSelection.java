import java.util.Arrays;

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
        int left = index;
        int right = nums.length - index - 1;
        int s1 = left * nums[index] - (prefix[index] - prefix[0]);
        int s2 = prefix[prefix.length - 1] - prefix[index + 1] - right * nums[index];
        return s1 + s2;
    }

}
