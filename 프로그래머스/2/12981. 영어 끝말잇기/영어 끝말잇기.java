import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        List<String> list = new ArrayList<>();
        list.add(words[0]);
        
        for (int i = 1; i < words.length; i++) {
            if ((words[i - 1].charAt(words[i - 1].length() - 1)) != (words[i].charAt(0))) {
                return new int[] { i % n + 1, i / n + 1 };
            }
            
            if (list.contains(words[i])) {
                return new int[] { i % n + 1, i / n + 1 };
            }
            
            list.add(words[i]);
        }

        return new int[] { 0, 0 };
    }
}