import java.util.*;

class Solution {
    public int solution(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            if (dq.isEmpty() || dq.peek() != c) {
                dq.offerFirst(c);
            } else if (!dq.isEmpty() && dq.peek() == c) {
                dq.pollFirst();
            }
        }

        return dq.isEmpty() ? 1 : 0;
    }
}