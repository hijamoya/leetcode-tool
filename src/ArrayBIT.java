public class ArrayBIT {
  int[] tree;
  int[] nums;

  public ArrayBIT(int[] nums) {
    this.nums = nums;
    tree = new int[nums.length + 1];
    for (int i = 1; i <= nums.length; i++) {
      add(i, nums[i - 1]);
    }
  }

  public void update(int index, int val) {
    add(index + 1, val - nums[index]);
    nums[index] = val;
  }

  public int sumRange(int left, int right) {
    return sum(right + 1) - sum(left);
  }

  // 將下標i的位置加上x
  private void add(int i, int x) {
    while (i < tree.length) {
      tree[i] += x;
      i += (i & (-i));
    }
  }

  // return 1 ~ i的sum
  private int sum(int i) {
    int sum = 0;
    while (i > 0) {
      sum += tree[i];
      i = i & (i - 1);
    }
    return sum;
  }
}
