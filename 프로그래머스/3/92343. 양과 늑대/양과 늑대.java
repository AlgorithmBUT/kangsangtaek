import java.util.*;

class Solution {
    
    int answer;
    int[] info;
    List<Integer>[] graph;
    
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        this.info = info;
        
        // 그래프 초기화
        int n = info.length;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // 부모 노드에 자식 노드 연결
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        
        // 방문 가능한 노드 목록
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        
        dfs(0, 0, nextNodes);
        
        return answer;
    }
    
    // dfs(현재까지 모은 양, 현재까지 모은 늑대, 방문 가능한 노드 목록)
    void dfs(int sheep, int wolf, List<Integer> nextNodes) {
        // 방문 가능한 노드 하나씩 선택
        for (int next : nextNodes) {
            int ns = sheep;
            int nw = wolf;
            
            if (info[next] == 0) ns++;
            else nw++;
            
            // 늑대 수가 더 많으면 continue
            if (nw >= ns) continue;
            
            answer = Math.max(answer, ns);
            
            // 방문 가능 목록
            List<Integer> list = new ArrayList<>(nextNodes);
            
            // 방문한 노드 목록에서 제거
            list.remove(Integer.valueOf(next));
            
            // 현재 노드의 자식들 목록에 추가
            list.addAll(graph[next]);
            
            dfs(ns, nw, list);
        }
    }
}