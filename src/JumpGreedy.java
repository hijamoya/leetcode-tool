// https://leetcode.cn/problems/minimum-number-of-valid-strings-to-form-target-ii/
// https://leetcode.cn/problems/jump-game-ii/description/
// https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/description/
public class JumpGreedy {
    public int minValidStrings(String[] words, String target) {
        // 算出每個word的最大跳躍距離
        // z函數 => 最大公共前綴
        // 每一個位置最大可以跳到哪
        int[] maxJump = new int[target.length()];
        for (String w : words) {
            // 這邊巧妙地用Z函數，製造出最大公共前綴的長度
            int[] z = getZ(w + "#" + target);
            int l = w.length() + 1;
            // 那麼 w 和 target的最大公共前綴就是 l + i
            for (int i = 0; i < target.length(); i++) {
                maxJump[i] = Math.max(maxJump[i], z[l + i]);
            }
        }
        // 現在開始跳躍
        // i 是人的位置, curR 是目前可走到得最遠位置, nxtR是我在走過來的路途中，選的橋樑
        int curR = 0;
        int nxtR = 0;
        int ans = 0;
        for (int i = 0; i < target.length(); i++) {
            nxtR = Math.max(nxtR, i + maxJump[i]);
            if (i == curR) {
                // 建一座新橋
                if (i == nxtR) {
                    // 動不了了
                    return -1;
                }
                curR = nxtR;
                ans++;
            }
        }
        return ans;
    }

    int[] getZ(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        int[] z = new int[n];
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(z[i - l], r - i + 1);
            }
            while (i + z[i] < n && t[z[i]] == t[i + z[i]]) {
                l = i;
                r = i + z[i];
                z[i]++;
            }
        }
        // z[0] = 0;
        z[0] = n;
        return z;
    }
}
