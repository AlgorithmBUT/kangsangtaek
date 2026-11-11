import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int camera = -30001;
        
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        
        for (int[] route : routes) {
            int in = route[0];
            int out = route[1];
            
            if (camera < in) {
                answer++;
                camera = out;
            }
        }
        
        return answer;
    }
}