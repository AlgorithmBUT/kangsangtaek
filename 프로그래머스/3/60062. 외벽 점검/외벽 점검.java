class Solution {
    
    int answer = Integer.MAX_VALUE;
    int wLen;
    int[] weak;
    int[] dist;
    boolean[] visited;
    
    public int solution(int n, int[] weak, int[] dist) {
        wLen = weak.length;
        this.weak = new int[wLen * 2];
        for (int i = 0; i < wLen; i++) {
            this.weak[i] = weak[i];
            this.weak[i + wLen] = weak[i] + n;
        }
        
        this.dist = dist;
        visited = new boolean[dist.length];
        
        for (int i = 0; i < wLen; i++) {
            for (int j = 0; j < dist.length; j++) {
                visited[j] = true;
                dfs(i, j, 0, 1);
                visited[j] = false;
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    void dfs(int wIndex, int dIndex, int wCount, int dCount) {
        if (dCount >= answer) return;
        
        int cur = weak[wIndex] + dist[dIndex];
        int cnt = 0;
        for (int i = wIndex; i < wIndex + wLen; i++) {
            if (cur >= weak[i]) cnt++;
            else break;
        }
        
        if (wCount + cnt >= wLen) {
            answer = Math.min(answer, dCount);
            return;
        }
        
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(wIndex + cnt, i, wCount + cnt, dCount + 1);
                visited[i] = false;
            }
        }
    }
}