import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for (int i = 0; i <= discount.length - 10; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = i; j < i + 10; j++) {
                map.put(discount[j], map.getOrDefault(discount[j], 0) + 1);
            }
            
            boolean check = true;
            for (int j = 0; j < want.length; j++) {
                if (!map.containsKey(want[j]) || map.get(want[j]) != number[j]) {
                    check = false;
                    break;
                }
            }
            
            if (check) answer++;
        }
        
        return answer;
    }
}