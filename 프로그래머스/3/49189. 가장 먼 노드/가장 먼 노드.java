import java.util.*;

class Solution {
    
    List<Integer>[] graph;
    int[] dist;
    
    public int solution(int n, int[][] edge) {
        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] e : edge) {
            int u = e[0];
            int v = e[1];
            
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // 거리 배열 초기화
        dist = new int[n + 1];
        Arrays.fill(dist, -1);
        
        // 1번 노드부터 시작
        bfs(1);
        
        // 가장 멀리 떨어진 노드 탐색
        int max = -1;
        int answer = 0;
        for (int d : dist) {
            if (d > max) {
                max = d;
                answer = 1;
            } else if (d == max) {
                answer++;
            }
        }
        
        return answer;
    }
    
    // 가장 먼 노드를 구하기 위한 bfs
    private void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        
        q.offer(start);
        dist[start] = 0;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            for (int next : graph[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }
    }
}