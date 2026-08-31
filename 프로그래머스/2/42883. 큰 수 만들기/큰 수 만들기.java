import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> dq = new ArrayDeque<>();
        
        int count = 0;
        for (char n : number.toCharArray()) {
            while (!dq.isEmpty() && count < k && dq.peekFirst() < n) {
                dq.pollFirst();
                count++;
            }
            
            dq.offerFirst(n);
        }
        
        while (count < k) {
            dq.pollFirst();
            count++;
        }
        
        StringBuilder answer = new StringBuilder();
        while (!dq.isEmpty()) {
            answer.insert(0, dq.pollFirst());
        }
        
        return answer.toString();
    }
}