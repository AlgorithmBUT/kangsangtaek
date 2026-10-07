import java.io.*;
import java.util.*;

public class Solution {
	
	static class Node {
		int x, y;
		
		Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class State implements Comparable<State> {
		int cur;
		int mask;
		int distance;
		
		State (int cur, int mask, int distance) {
			this.cur = cur;
			this.mask = mask;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(State o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N;
	static Node company;
	static Node home;
	static Node[] customers;
	static int[][] dist;
	static int answer;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			customers = new Node[N];
			dist = new int[1 << N][N];
			for (int i = 0; i < (1 << N); i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			st = new StringTokenizer(br.readLine());
			company = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < N; i++) {
				customers[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			answer = Integer.MAX_VALUE;
			dijkstra();
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dijkstra() {
		PriorityQueue<State> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			int mask = 1 << i;
			int distance = getDistance(company, customers[i]);
			
			dist[mask][i] = distance;
			pq.offer(new State(i, mask, distance));
		}
		
		while (!pq.isEmpty()) {
			State now = pq.poll();
			
			int cur = now.cur;
			int mask = now.mask;
			int distance = now.distance;
			
			if (distance > dist[mask][cur]) continue;
			
			for (int next = 0; next < N; next++) {
				if ((mask & (1 << next)) != 0) continue;
				
				int nextMask = mask | (1 << next);
				
				int nextDistance = distance + getDistance(customers[cur], customers[next]);
				
				if (dist[nextMask][next] <= nextDistance) continue; 
				
				dist[nextMask][next] = nextDistance;
				
				pq.offer(new State(next, nextMask, nextDistance));
			}
		}
		
		int fullMask = (1 << N) - 1;
		
		for (int i = 0; i < N; i++) {
			answer = Math.min(answer, dist[fullMask][i] + getDistance(customers[i], home));
		}
	}
	
	static int getDistance(Node a, Node b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); 
	}

}
