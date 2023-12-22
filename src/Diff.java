public class Diff {
  // 差分模板 2772
  void check(int[] nums, int k) {
    int n = nums.length;
    // 注意是 i + 1
    int[] diff = new int[n + 1];
    int sumD = 0;
    for (int i = 0; i < n; i++) {
      // 先累計上一次的差分值
      sumD += diff[i];
      // 目前x的實際值
      int x = nums[i] + sumD;
      // 做一些判斷...
      if (x == 0) {
        continue;
      }
      // 注意這邊是可以等於n的，因為在動態差分當中，第一輪是輪空的
      if (x < 0 || i + k > n) {
        return;
      }
      // 範圍增加/減少 => sumD為當下的這個位置, i+k為之後要扣回來的位置
      sumD -= x;
      diff[i + k] += x;
    }
  }
}
