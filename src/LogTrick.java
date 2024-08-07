import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 力扣上有很多这样的题，需要用到一个结论：
 * 给定一个数组，以某个右端点为结尾的所有子数组，其中不同的 And/Or/lcm/gcd 值至多只有 logU 个，其中U為組數中最大的數字
 *
 * 所以總複雜度為 O(nlogU)
 */
// https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or/solutions/1830911/by-endlesscheng-zai1/
public class LogTrick {
    void logTricks(int[] nums) {
        int n = nums.length;
        List<int[]> list = new ArrayList<>(); // 按位(and, or, lcm, gcd)的值 + 对应子数组的右端点的最小值
        for (int i = n - 1; i >= 0; i--) {
            list.add(new int[]{nums[i], i});
            int index = 0;
            for (int[] op : list) {
                // 這裡是And，可以改任意operation
                op[0] &= nums[i];
                if (list.get(index)[0] == op[0]) {
                    list.get(index)[1] = op[1]; // 合并相同值，下标取最小的
                } else {
                    index++;
                    list.set(index, op);
                }
            }
            // 把尾巴刪掉
            list.subList(index + 1, list.size()).clear();
            // TODO: 這邊就有目前所有的值，如果题目改成任意给定数值，可以在 list 中查找
        }
    }

    // 用set的版本
    // 從最右邊開始，每個以nums[i]為"左端點"的子組數的操作值
    void logTricks2(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            Set<Integer> newSet = new HashSet<>();
            newSet.add(nums[i]);
            for (int op : set) {
                // 這裡是And，可以改任意operation
                newSet.add(op & nums[i]);
            }
            // TODO: 值都在newSet裡面
            set = newSet;
        }
    }
}
