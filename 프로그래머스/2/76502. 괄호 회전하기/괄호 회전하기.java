import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;

        for(int i = 0; i < s.length(); i++){
            String str = s.substring(i) + s.substring(0, i);
            Deque<Character> dq = new ArrayDeque<>();

            for(char c : str.toCharArray()){
                if(dq.isEmpty()){
                    dq.offerFirst(c);
                    continue;
                }
                if(c == ')' && dq.peek() == '('){
                    dq.pollFirst();
                    continue;
                }
                else if(c == '}' && dq.peek() == '{'){
                    dq.pollFirst();
                    continue;
                }
                else if(c == ']' && dq.peek() == '['){
                    dq.pollFirst();
                    continue;
                }
                dq.offerFirst(c);
            }
            
            if (dq.isEmpty()) answer++;
        }

        return answer;
    }
}