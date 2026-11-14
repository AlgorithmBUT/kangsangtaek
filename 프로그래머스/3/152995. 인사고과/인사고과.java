import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;
        
        int wanA = scores[0][0];
        int wanB = scores[0][1];
        int wanho = wanA + wanB;
        
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(b[0], a[0]);
        });
        
        int max = 0;
        
        for (int[] score : scores) {
            int scoreA = score[0];
            int scoreB = score[1];
            
            if (scoreB < max) {
                if (scoreA == wanA && scoreB == wanB) {
                    return -1;
                }
            } else {
                max = Math.max(max, scoreB);
                
                if (scoreA + scoreB > wanho) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}