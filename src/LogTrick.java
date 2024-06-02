import java.util.ArrayList;
import java.util.List;

/**
 * 力扣上有很多这样的题，需要用到一个结论：
 * 给定一个数组，以某个右端点为结尾的所有子数组，其中不同的 And/Or/lcm/gcd 值至多只有 logU 个，其中U為組數中最大的數字
 */
// https://leetcode.cn/problems/smallest-subarrays-with-maximum-bitwise-or/solutions/1830911/by-endlesscheng-zai1/
public class LogTrick {
    void tricks(int[] nums) {
        int n = nums.length;
        List<int[]> list = new ArrayList<>(); // 按位(and, or, lcm, gcd)的值 + 对应子数组的右端点的最小值
        for (int i = n - 1; i >= 0; i--) {
            list.add(new int[]{nums[i], i});
            int index = 0;
            for (int[] op : list) {
                op[0] &= nums[i];
                if (list.get(index)[0] == op[0]) {
                    list.get(index)[1] = op[1]; // 合并相同值，下标取最小的
                } else {
                    index++;
                    list.set(index, op);
                }
            }
            list.subList(index + 1, list.size()).clear();
            // TODO: 這邊就有目前所有的值，如果题目改成任意给定数值，可以在 list 中查找
        }
    }
}
