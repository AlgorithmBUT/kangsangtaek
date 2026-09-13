import java.util.*;

class Solution {
    
    int k;
    int n;
    int[][] reqs;
    int[][] waiting;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int k, int n, int[][] reqs) {
        this.k = k;
        this.n = n;
        this.reqs = reqs;
        waiting = new int[k + 1][n - k + 2];
        
        for (int i = 1; i <= k; i++) {
            calWaitingTime(i, 1);
        }
        
        minWaiting(1, n, 0);
        
        return answer;
    }
    
    void calWaitingTime(int t, int cnt) {
        if (cnt >= n - k + 2) return;
        
        Queue<Integer> pq = new PriorityQueue<>();
        int totalWaiting = 0;
        
        for (int i = 0; i < cnt; i++) {
            pq.offer(0);
        }
        
        for (int[] req : reqs) {
            int reqT = req[0];
            int time = req[1];
            int type = req[2];
            
            if (type != t) continue;
            
            int endTime = pq.poll();
            if (endTime <= reqT) {
                pq.offer(reqT + time);
            } else {
                totalWaiting += endTime - reqT;
                pq.offer(endTime + time);
            }
        }
        
        waiting[t][cnt] = totalWaiting;
        
        calWaitingTime(t, cnt + 1);
    }
    
    void minWaiting(int type, int remain, int totalWaiting) {
        if (type > k) {
            if (remain == 0) {
                answer = Math.min(answer, totalWaiting);
            }
            return;
        }
        
        int max = remain - (k - type);
        for (int i = 1; i <= max; i++) {
            minWaiting(type + 1, remain - i, totalWaiting + waiting[type][i]);
        }
    }
}