import java.util.PriorityQueue;

public class MedianFinder {
  PriorityQueue<Integer> min = new PriorityQueue<>();
  PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);

  public MedianFinder() {
  }

  public void addNum(int num) {
    // 盡量讓兩個heap一樣多
    // 我們定義min queue永遠比max queue還多
    if (min.isEmpty() || num > min.peek()) {
      min.offer(num);
      if (min.size() - max.size() > 1) {
        // balance
        max.offer(min.poll());
      }
    } else {
      max.offer(num);
      if (max.size() - min.size() > 0) {
        // balance
        min.offer(max.poll());
      }
    }
  }

  public double findMedian() {
    return min.size() > max.size() ? (double) min.peek() : ((double) min.peek() + (double) max.peek()) / 2d;
  }
}
