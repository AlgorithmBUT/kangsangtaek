import java.util.*;

class Solution {
    
    int n, m;
    int[][] info;
    int answer;
    int[][] memo;
    
    public int solution(int[][] info, int n, int m) {
        this.n = n;
        this.m = m;
        this.info = info;
        answer = Integer.MAX_VALUE;
        memo = new int[info.length + 1][m];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        
        dfs(0, 0, 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    void dfs(int depth, int nCount, int mCount) {
        // A 또는 B의 흔적이 n 또는 m 이상이면 탐색 종료
        if (nCount >= n || mCount >= m) return;
        
        // 동일한 상태에서 이미 더 적은 경우가 있다면 탐색 종료
        if (memo[depth][mCount] <= nCount) return;
        
        // 현재 상태에서 A의 최소 흔적 갱신
        memo[depth][mCount] = nCount;
        
        // 현재 A의 흔적이 이미 구한 정답 이상이면 탐색 종료
        if (nCount >= answer) return;
        
        // 모든 물건을 훔쳤다면 정답 갱신
        if (depth == info.length) {
            answer = Math.min(answer, nCount);
            return;
        }
        
        // A가 훔치는 경우
        dfs(depth + 1, nCount + info[depth][0], mCount);
        // B가 훔치는 경우
        dfs(depth + 1, nCount, mCount + info[depth][1]);
    }
}