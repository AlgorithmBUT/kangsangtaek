import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> {
            return Integer.compare(o1[1], o2[1]);
        });
        
        int camera = Integer.MIN_VALUE;
        for (int[] route : routes) {
            if(camera < route[0]) {
                answer++;
                camera = route[1];
            }
        }
        
        return answer;
    }
}