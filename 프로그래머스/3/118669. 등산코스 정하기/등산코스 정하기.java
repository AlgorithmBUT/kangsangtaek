import java.util.*;

class Solution {
    
    class Node {
        int to;
        int intensity;
        
        Node(int to, int intensity) {
            this.to = to;
            this.intensity = intensity;
        }
    }
    
    int n;
    int[] gates;
    int[] summits;
    List<Node>[] route;
    int[] intensity;
    boolean[] isGate;
    boolean[] isSummit;
    int[] answer;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        this.gates = gates;
        this.summits = summits;
        
        // 인접 리스트 초기화
        route = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            route[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int intensity = path[2];
            
            route[from].add(new Node(to, intensity));
            route[to].add(new Node(from, intensity));
        }
        
        // 출입구 여부 표시
        isGate = new boolean[n + 1];
        for (int gate : gates) {
            isGate[gate] = true;
        }
        
        // 산봉우리 여부 표시
        isSummit = new boolean[n + 1];
        for (int summit : summits) {
            isSummit[summit] = true;
        }
        
        answer = new int[] { 0, Integer.MAX_VALUE };
        dijkstra();
        
        return answer;
    }
    
    void dijkstra() {
        // intensity가 작은 노드부터 탐색
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.intensity, o2.intensity);
        });
        
        // 각 노드까지의 최소 intensity 초기화
        intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        // 모든 출입구를 시작점으로 설정
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Node(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int to = cur.to;
            int curIntensity = cur.intensity;
            
            // 더 작은 intensity로 도달한 경우 탐색 생략
            if (curIntensity > intensity[to]) continue;
            
            // 산봉우리에 도착한 경우 이동 x
            if (isSummit[to]) continue;
            
            // 다음 노드 탐색
            for (Node next : route[to]) {
                if (isGate[next.to]) continue;
                
                int newIntensity = Math.max(curIntensity, next.intensity);
                
                // 기존 intensity보다 작은 경우 갱신
                if (newIntensity < intensity[next.to]) {
                    intensity[next.to] = newIntensity;
                    pq.offer(new Node(next.to, newIntensity));
                }
            }
        }
        
        // 모든 산봉우리 중 최소 intensity를 가진 산봉우리 선택
        for (int summit : summits) {
            if (intensity[summit] < answer[1] ||
               (intensity[summit] == answer[1] && summit < answer[0])) {
                answer[0] = summit;
                answer[1] = intensity[summit];
            }
        }
    }
    
}