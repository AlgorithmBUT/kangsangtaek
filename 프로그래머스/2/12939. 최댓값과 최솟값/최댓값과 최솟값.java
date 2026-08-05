import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            int cur = Integer.parseInt(st.nextToken());
            max = Math.max(max, cur);
            min = Math.min(min, cur);
        }
        
        answer.append(min).append(" ").append(max);
        
        return answer.toString();
    }
}