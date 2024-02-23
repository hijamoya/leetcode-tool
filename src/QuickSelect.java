import java.util.Random;

public class QuickSelect {
    // https://leetcode.com/problems/kth-largest-element-in-an-array/

    int quickSelect(int[] nums, int k) {
        // k大就是 nums.length - k
        // k小就是 k
        int target = nums.length - k;
        int l = 0;
        int r = nums.length - 1;
        while (true) {
            int p = partition(nums, l , r);
            if (p == target) {
                return nums[p];
            } else if (p > target) {
                r = p - 1;
            } else {
                l = p + 1;
            }
        }
    }

    int partition(int[] nums, int l, int r) {
        if (r > l) {
            swap(nums, r, l + new Random().nextInt(r - l));
        }
        int pivot = nums[r];
        int left = l;
        for (int i = l; i < r; i++) {
            if (pivot > nums[i]) {
                swap(nums, left, i);
                left++;
            }
        }
        swap(nums, left, r);
        return left;
    }

    void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
