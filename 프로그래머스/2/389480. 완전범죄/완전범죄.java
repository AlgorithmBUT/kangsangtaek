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
        if (nCount >= n || mCount >= m) return;
        
        if (memo[depth][mCount] <= nCount) return;
        
        memo[depth][mCount] = nCount;
        
        if (nCount >= answer) return;
        
        if (depth == info.length) {
            answer = Math.min(answer, nCount);
            return;
        }
        
        dfs(depth + 1, nCount + info[depth][0], mCount);
        dfs(depth + 1, nCount, mCount + info[depth][1]);
    }
}