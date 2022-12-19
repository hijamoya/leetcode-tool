public class PrefixSum {
  void prefixSum(int[] nums) {
    int[] prefix = new int[nums.length + 1];
    for (int i = 1; i < prefix.length; i++) {
      prefix[i] = prefix[i - 1] + nums[i - 1];
    }
  }
}
