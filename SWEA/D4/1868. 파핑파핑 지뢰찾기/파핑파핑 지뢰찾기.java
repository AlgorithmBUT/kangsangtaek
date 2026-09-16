import java.io.*;
import java.util.*;

public class Solution {
	
	static class Node {
		int x;
		int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int[][] map;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = (str.charAt(j) == '*') ? -1 : 0;
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == -1) continue;
					
					map[i][j] = find(i, j);
				}
			}
			
			int answer = 0;
			visited =  new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0 || visited[i][j]) continue;
					
					bfs(i, j);
					answer++;
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] != -1 && !visited[i][j]) answer++;
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static int find(int x, int y) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!inRange(nx, ny)) continue;
			if (map[nx][ny] == -1) count++;
		}
		
		return count;
	}

	static void bfs(int startX, int startY) {
		Queue<Node> q = new ArrayDeque<>();
		
		q.offer(new Node(startX, startY));
		visited[startX][startY] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (!inRange(nx, ny)) continue;
				if (map[nx][ny] == -1) continue;
				if (visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				
				if (map[nx][ny] != 0) continue;
				
				q.offer(new Node(nx, ny));
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return false;
		return true;
	}
}
