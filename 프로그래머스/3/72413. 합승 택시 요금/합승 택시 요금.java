import java.util.*;

class Solution {
    
    class Node {
        int to;
        int fare;
        
        Node(int to, int fare) {
            this.to = to;
            this.fare = fare;
        }
    }
    
    int n;
    List<Node>[] graph;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        
        // 그래프 초기화
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] f : fares) {
            int from = f[0];
            int to = f[1];
            int fare = f[2];
            
            graph[from].add(new Node(to, fare));
            graph[to].add(new Node(from, fare));
        }
        
        // 각 지점을 기준으로 모든 정점까지 최소 비용
        int[] distS = dijkstra(s);
        int[] distA = dijkstra(a);
        int[] distB = dijkstra(b);
        
        // 모든 정점을 기준으로 최소가 될 수 있는 정점 탐색
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, distS[i] + distA[i] + distB[i]);
        }
        
        return answer;
    }
    
    // 모든 정점까지의 최소 비용을 구하기 위한 다익스트라
    private int[] dijkstra(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.fare, o2.fare));
        
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int node = cur.to;
            int fare = cur.fare;
            
            if (dist[node] < fare) continue;
            
            for (Node next : graph[node]) {
                int nextNode = next.to;
                int nextFare = next.fare + fare;
                
                if (dist[nextNode] < nextFare) continue;
                
                dist[nextNode] = nextFare;
                pq.offer(new Node(nextNode, nextFare));
            }
        }
        
        return dist;
    }
}