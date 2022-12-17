import java.util.Deque;
import java.util.LinkedList;

public class Calculator {
  public int calculate(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char sign = '+';
    int num = 0;
    int i = 0;
    int n = s.length();
    Deque<Integer> stack = new LinkedList<>();
    while (i < n) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        num = num * 10 + (c - '0');
      } else if (c == '(') {
        int j = i;
        // 匹配括號數量
        int count = 0;
        while (i < n) {
          if (s.charAt(i) == '(') {
            count++;
          }
          if (s.charAt(i) == ')') {
            count--;
          }
          if (count == 0) {
            num = calculate(s.substring(j + 1, i));
            break;
          }
          i++;
        }
      }
      // 直接前進到下一個符號 或是 到最一個
      if (!Character.isDigit(c) || i == s.length() - 1) {
        // sign是上一個符號
        switch(sign) {
          case '+':
            stack.push(num);
            break;
          case '-':
            stack.push(-num);
            break;
          case '*':
            stack.push(stack.pop() * num);
            break;
          case '/':
            stack.push(stack.pop() / num);
            break;
        }
        // 算完上一個符號的結果，紀錄下一個符號
        sign = c;
        // 算完後 歸零
        num = 0;
      }
      i++;
    }
    int ans = 0;
    while (!stack.isEmpty()) {
      ans += stack.pop();
    }
    return ans;
  }
}
