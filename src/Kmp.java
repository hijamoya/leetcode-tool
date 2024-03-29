public class Kmp {
  int[] getNext(String s) {
    int[] next = new int[s.length()];
    int j = -1;
    next[0] = j;
    for (int i = 1; i < s.length(); i++) {
      while (j >= 0 && s.charAt(i) != s.charAt(j + 1)) {
        j = next[j];
      }
      if (s.charAt(i) == s.charAt(j + 1)) {
        j++;
      }
      next[i] = j;
    }
    return next;
  }

  // 回傳第一個下標位置
  int strStr(String heystack, String needle) {
    if (needle.length() == 0) {
      return 0;
    }
    int[] next = getNext(needle);
    int j = -1;
    for (int i = 0; i < heystack.length(); i++) {
      while (j >= 0 && heystack.charAt(i) != needle.charAt(j + 1)) {
        j = next[j];
      }
      if (heystack.charAt(i) == needle.charAt(j + 1)) {
        j++;
      }
      if (j == needle.length() - 1) {
        return (i - needle.length() + 1);
      }
    }
    return -1;
  }

  // 回傳總共有多少個模式串
  int count(String heystack, String needle) {
    int count = 0;
    if (needle.length() == 0) {
      return 0;
    }
    int[] next = getNext(needle);
    int j = -1;
    for (int i = 0; i < heystack.length(); i++) {
      while (j >= 0 && heystack.charAt(i) != needle.charAt(j + 1)) {
        j = next[j];
      }
      if (heystack.charAt(i) == needle.charAt(j + 1)) {
        j++;
      }
      if (j == needle.length() - 1) {
        j = next[j];
        count++;
      }
    }
    return count;
  }
}
