import java.util.PriorityQueue;

// https://leetcode.cn/problems/p0NxJO/description
// 反悔貪心：
// 1. 先衝到最底，並且記住過去最差的狀況
// 2. 不符合條件後，吐出以前最差的狀況，來繼續前進
// 3. 這樣貪心以後，最後的值就會是最佳的
class ReverseGreedy {
  public int magicTower(int[] nums) {
    long s = 1;
    for (int n : nums) {
      s += n;
    }
    if (s <= 0) {
      return -1;
    }
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    long sum = 1;
    int i = 0;
    int ans = 0;
    while (i < nums.length) {
      // 先一口氣衝到最遠去
      while (i < nums.length && sum > 0) {
        sum += nums[i];
        if (nums[i] < 0) {
          pq.offer(nums[i]);
        }
        i++;
      }
      while (sum <= 0) {
        // 不夠了，吐出以前最小的
        sum -= pq.poll();
        ans++;
      }
    }
    return ans;
  }
}