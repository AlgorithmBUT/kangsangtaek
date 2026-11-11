import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        long min = 0;
        long max = (long)times[0] * n;
        
        while (min < max) {
            long mid = (min + max) / 2;
            
            // mid보다 작은 수가 나올 수 있는지 확인
            long count = 0;
            for (int time : times) {
                count += mid / time;
            }
            
            // 나오면 mid가 max가 되고
            if (count >= n) {
                max = mid;
                answer = mid;
            }
            // 안나오면 mid가 min이 된다.
            else {
                min = mid + 1;
            }
        }
        
        return answer;
    }
}