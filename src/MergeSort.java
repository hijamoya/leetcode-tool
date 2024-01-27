public class MergeSort {
  int res = 0;

  public int reversePairs(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return res;
  }


  void mergeSort(int[] nums, int l, int r) {
    if (l >= r) {
      return;
    }
    int mid = l + (r - l) / 2;
    mergeSort(nums, l, mid);
    mergeSort(nums, mid + 1, r);
    merge(nums, l, mid, r);
  }

  void merge(int[] nums, int l, int mid, int r) {
    int[] buffer = new int[r - l + 1];
    int i = 0;
    int i1 = l;
    int i2 = mid + 1;
    while (i1 <= mid && i2 <= r) {
      if (nums[i1] <= nums[i2]) {
        // 左邊增加
        buffer[i] = nums[i1];
        i1++;
      } else {
        // 右邊增加
        buffer[i] = nums[i2];
        i2++;
        res += mid - i1 + 1;
      }
      i++;
    }
    for (int k = i1; k <= mid; k++) {
      buffer[i] = nums[k];
      i++;
    }
    for (int k = i2; k <= r; k++) {
      buffer[i] = nums[k];
      i++;
    }
    for (int j = l; j <= r; j++) {
      nums[j] = buffer[j - l];
    }
  }
}
