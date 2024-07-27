import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基本线段树 (无懒标记，无区间修改方法，不维护 nums[i])
 * 支持：单点修改 / 单点查询 / 区间查询 (同时支持 O(logn) 时间的区间和，区间最大值，区间最小值查询)
 */
class SegmentTreeBasic {
  int[] nums, treeSum, treeMin,treeMax;
  int n;
  public SegmentTreeBasic(int[] nums){
    this.nums = nums;
    this.n = nums.length;
    this.treeSum = new int[4 * n];
    this.treeMin = new int[4 * n];
    this.treeMax = new int[4 * n];
    build(0, n - 1, 1);
  }
  public void add(int i, int x){ // 单点修改(驱动): nums[i] += x
    add(i, x, 0, n - 1, 1);
  }
  public void update(int i, int x){// 单点修改(驱动): nums[i] = x
    update(i, x, 0, n - 1, 1);
  }
  public int query(int i){ // 单点查询 (驱动): 查询 nums[i]
    return query(i, 0, n - 1, 1);
  }
  public int sum(int l, int r){ // 区间查询(驱动): nums[l]~nums[r]之和
    return sum(l, r, 0, n - 1, 1);
  }
  public int min(int l, int r){ // 区间查询 (驱动): 查询[l,r]中的最小值
    return min(l, r, 0, n - 1, 1);
  }
  public int max(int l, int r){ // 区间查询 (驱动): 查询[l,r]中的最大值
    return max(l, r, 0, n - 1, 1);
  }
  // 单点查询 (具体): 查询 nums[i]，尾递归
  private int query(int idx, int s, int t, int i){
    if(s == t) return treeSum[i];
    int c = s + (t - s) / 2;
    if(idx <= c) return query(idx, s, c, i * 2);
    else return query(idx, c + 1, t, i * 2 + 1);
  }
  // 单点修改: nums[idx] += x
  private void add(int idx, int x, int s, int t, int i){
    if(s == t) {
      treeSum[i] += x; // 增量更新
      treeMin[i] += x; // 增量更新
      treeMax[i] += x; // 增量更新
      return;
    }
    int c = s + (t - s) / 2;
    if(idx <= c) add(idx, x, s, c, i * 2);
    else add(idx, x, c + 1, t, i * 2 + 1);
    pushUpSum(i);
    pushUpMin(i);
    pushUpMax(i);
  }
  // 单点修改: nums[idx] = x
  private void update(int idx, int x, int s, int t, int i){
    if(s == t) {
      treeSum[i] = x; // 覆盖更新
      treeMin[i] = x; // 覆盖更新
      treeMax[i] = x; // 覆盖更新
      return;
    }
    int c = s + (t - s) / 2;
    if(idx <= c) update(idx, x, s, c, i * 2);
    else update(idx, x, c + 1, t, i * 2 + 1);
    pushUpSum(i);
    pushUpMin(i);
    pushUpMax(i);
  }
  // 区间查询: nums[l]~nums[r]之和
  private int sum(int l, int r, int s, int t, int i){
    if(l <= s && t <= r) return treeSum[i];
    int c = s + (t - s) / 2, sum = 0;
    if(l <= c) sum += sum(l, r, s, c, i * 2); // 递归累加目标区间落在c左侧(含c)的区间和
    if(r > c) sum += sum(l, r, c + 1, t, i * 2 + 1); // 递归累加目标区间落在c右侧的区间和
    return sum;
  }
  // 区间查询: 查询[l,r]中的最小值
  private int min(int l, int r, int s, int t, int i){
    if(l <= s && t <= r) return treeMin[i];
    int c = s + (t - s) / 2, lmin = Integer.MAX_VALUE, rmin = Integer.MAX_VALUE;
    if(l <= c) lmin = min(l, r, s, c, i * 2);
    if(r > c) rmin = min(l, r, c + 1, t, i * 2 + 1);
    return Math.min(lmin, rmin);
  }
  // 区间查询: 查询[l,r]中的最大值
  private int max(int l, int r, int s, int t, int i){
    if(l <= s && t <= r) return treeMax[i];
    int c = s + (t - s) / 2, lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE;
    if(l <= c) lmax = max(l, r, s, c, i * 2);
    if(r > c) rmax = max(l, r, c + 1, t, i * 2 + 1);
    return Math.max(lmax, rmax);
  }
  // 构建线段树(tree数组)
  private void build(int s, int t, int i){
    if(s == t) { // s: start,nums当前区间起点下标，t: terminal,nums当前结点区间末尾下标
      treeSum[i] = nums[s];
      treeMin[i] = nums[s];
      treeMax[i] = nums[s];
      return;
    }
    int c = s + (t - s) / 2;
    build(s, c, i * 2);
    build(c + 1, t, i * 2 + 1);
    pushUpSum(i);
    pushUpMin(i);
    pushUpMax(i);
  }
  // pushUpSum: 更新 treeSum[i]
  private void pushUpSum(int i){
    treeSum[i] = treeSum[i * 2] + treeSum[i * 2 + 1];
  }
  // pushUpMin: 更新 treeMin[i]
  private void pushUpMin(int i){
    treeMin[i] = Math.min(treeMin[i * 2], treeMin[i * 2 + 1]);
  }
  // pushUpMax: 更新 treeMax[i]
  private void pushUpMax(int i){
    treeMax[i] = Math.max(treeMax[i * 2], treeMax[i * 2 + 1]);
  }

  private Map<Integer, Integer> discrete(int[] nums){ // 紧离散
    Map<Integer, Integer> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    for(int num : nums) set.add(num);
    List<Integer> list = new ArrayList<>(set);
    Collections.sort(list);
    int idx = 0;
    for(int num : list) {
      map.put(num, idx);
      idx++;
    }
    return map;
  }

}