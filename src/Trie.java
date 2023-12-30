public class Trie {
  Trie[] trie = new Trie[26];
  String word;
  boolean isWord;

  public void insert(String word) {
    Trie t = this;
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (t.trie[c - 'a'] == null) {
        t.trie[c - 'a'] = new Trie();
      }
      t = t.trie[c - 'a'];
    }
    t.word = word;
    t.isWord = true;
  }

  public boolean search(String word) {
    Trie c = this;
    for (int i = 0; i < word.length(); i++) {
      if (c.trie[word.charAt(i) - 'a'] == null) {
        return false;
      }
      c = c.trie[word.charAt(i) - 'a'];
    }
    return c.isWord;
  }

  public boolean startsWith(String prefix) {
    Trie c = this;
    for (int i = 0; i < prefix.length(); i++) {
      if (c.trie[prefix.charAt(i) - 'a'] == null) {
        return false;
      }
      c = c.trie[prefix.charAt(i) - 'a'];
    }
    return true;
  }
}