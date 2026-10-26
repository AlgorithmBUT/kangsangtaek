import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 목표 알고력과 코딩력 계산
        int maxAlp = 0;
        int maxCop = 0;
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        // dp 초기화
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        dp[alp][cop] = 0;
        
        // 현재 알고력과 코딩력부터 목표까지 순회
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 현재 상태에 도달할 수 없다면 continue
                if (dp[i][j] == Integer.MAX_VALUE) continue;
                
                // 알고력 공부 (알고력 1 증가)
                if (i < maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                
                // 코딩력 공부 (코딩력 1 증가)
                if (j < maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                
                // 문제 풀이
                for (int[] problem : problems) {
                    int alpReq = problem[0];
                    int copReq = problem[1];
                    int alpRwd = problem[2];
                    int copRwd = problem[3];
                    int cost = problem[4];
                    
                    // 현재 알고력과 코딩력이 모두 요구치를 만족한다면
                    if (i >= alpReq && j >= copReq) {
                        int nextAlp = Math.min(maxAlp, i + alpRwd);
                        int nextCop = Math.min(maxCop, j + copRwd);
                        
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}