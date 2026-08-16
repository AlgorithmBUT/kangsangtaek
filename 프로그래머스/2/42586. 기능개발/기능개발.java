import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < progresses.length; i++) {
            q.offer((int)Math.ceil((100 - progresses[i]) / (double)speeds[i]));
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            int count = 1;
            
            while (!q.isEmpty() && cur >= q.peek()) {
                q.poll();
                count++;
            }
            
            list.add(count);
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}