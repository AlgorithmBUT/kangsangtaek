class Solution {
    
    int answer = Integer.MAX_VALUE;
    int[] weak;
    int[] dist;
    boolean[] visited;
    
    public int solution(int n, int[] weak, int[] dist) {
        int wLen = weak.length;
        this.weak = new int[wLen * 2];
        for (int i = 0; i < wLen; i++) {
            this.weak[i] = weak[i];
            this.weak[i + wLen] = weak[i] + n;
        }
        
        int dLen = dist.length;
        this.dist = new int[dLen];
        for (int i = 0; i < dLen; i++) {
            this.dist[i] = dist[dLen - 1 - i];
        }
        
        visited = new boolean[dLen];
        
        for (int i = 0; i < wLen; i++) {
            for (int j = 0; j < dLen; j++) {
                visited[j] = true;
                dfs(i, j, 0, 1);
                visited[j] = false;
            }
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    void dfs(int wIndex, int dIndex, int wCount, int dCount) {
        if (dCount >= answer) return;
        
        int cover = weak[wIndex] + dist[dIndex];
        int count = 0;
        for (int i = wIndex; i < wIndex + weak.length / 2; i++) {
            if (cover >= weak[i]) count++;
            else break;
        }
        
        if (wCount + count >= weak.length / 2) {
            answer = Math.min(answer, dCount);
            return;
        }
        
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(wIndex + count, i, wCount + count, dCount + 1);
                visited[i] = false;
            }
        }
    }
}