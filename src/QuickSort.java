import java.util.Random;

public class QuickSort {
  public int[] sortArray(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
    return nums;
  }

  void quickSort(int[] nums, int l, int r) {
    if (l >= r) {
      return;
    }
    int p = partition(nums, l, r);
    quickSort(nums, l, p - 1);
    quickSort(nums, p + 1, r);
  }

  int partition(int[] nums, int l, int r) {
    if (r > l) {
      swap(nums, r, l + new Random().nextInt(r - l));
    }
    int pivot = nums[r];
    int i = l;
    for (int j = l; j < r; j++) {
      if (nums[j] < pivot) {
        swap(nums, i, j);
        i++;
      }
    }
    swap(nums, i, r);
    return i;
  }

  void swap(int[] nums, int l, int r) {
    int temp = nums[l];
    nums[l] = nums[r];
    nums[r] = temp;
  }
}
