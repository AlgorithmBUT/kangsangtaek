import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int answer = 0;
        for (int e : enemy) {
            pq.offer(e);
            n -= e;
            
            // 병사가 부족하다면
            if (n < 0) {
                // 무적권 없으면 break
                if (k <= 0) break;
                
                // 가장 많은 적이 나온 라운드에 무적권 사용
                n += pq.poll();
                k--;
            }
            
            answer++;
        }
        
        return answer;
    }
}