import java.io.*;
import java.util.*;

public class Solution {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N, lastDay, answer, count;
	static List<Integer>[] cheese;
	static boolean[] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] root;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			lastDay = 0;
			answer = 1;
			cheese = new ArrayList[101];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int cur = Integer.parseInt(st.nextToken());
					if (cheese[cur] == null) {
						cheese[cur] = new ArrayList<>();
					}
					cheese[cur].add(i * N + j);
					lastDay = Math.max(lastDay, cur);
				}
			}
			
			root = new int[N * N];
			for (int i = 0; i < N * N; i++) {
				root[i] = i;
			}
			
			visited = new boolean[N * N];
			count = 0;
			
			for (int day = lastDay; day >= 1; day--) {
				answer = Math.max(answer, activateCheese(cheese[day]));
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static int activateCheese(List<Integer> positions) {
		if (positions == null) return count;
		
		for (int position : positions) {
			int x = position / N;
			int y = position % N;
			
			visited[position] = true;
			
			count++;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visited[nx * N + ny]) {
					int cur = find(x * N + y);
					int next = find(nx * N + ny);
					
					if (cur != next) {
						union(cur, next);
						count--;
					}
				}
			}
		}
		
		return count;
	}
	
	static int find(int x) {
		if (root[x] == x) return x;
		else return root[x] = find(root[x]);
	}
	
	static void union(int x, int y) {
		root[y] = x;
	}
	
}
