import java.util.*;

class Solution {
    boolean solution(String s) {
        
        // Stack을 활용한 풀이
        // Stack<Character> stack = new Stack();
        // for (int i = 0; i < s.length(); i++) {
        //     if (!stack.isEmpty()) {
        //         if (s.charAt(i) == ')') stack.pop();
        //         else stack.push(s.charAt(i));
        //     } else {
        //         if (s.charAt(i) == ')') return false;
        //         else stack.push(s.charAt(i));
        //     }
        // }
        // return stack.isEmpty();
        
        // Deque을 활용한 풀이
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (deque.isEmpty()) return false;
                deque.pollFirst();
            } else {
                deque.offerFirst(c);
            }
        }

        return deque.isEmpty();
    }
}