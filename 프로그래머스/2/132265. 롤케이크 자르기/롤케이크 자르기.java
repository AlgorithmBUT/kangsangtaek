import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Set<Integer> ch = new HashSet<>();
        Map<Integer, Integer> br = new HashMap<>();
        
        for (int t : topping) {
            br.put(t, br.getOrDefault(t, 0) + 1);
        }
        
        for (int t : topping) {
            ch.add(t);
            br.put(t, br.get(t) - 1);
            if (br.get(t) == 0) br.remove(t);
            
            if (ch.size() == br.size()) answer++;
        }
        
        return answer;
    }
}