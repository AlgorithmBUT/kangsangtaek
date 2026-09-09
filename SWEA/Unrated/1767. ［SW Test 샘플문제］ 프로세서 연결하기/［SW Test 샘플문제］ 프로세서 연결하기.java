import java.io.*;
import java.util.*;

public class Solution {
	
	static class Core {
		int x;
		int y;
		
		Core (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int[][] processor;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static List<Core> cores;
	static int maxConnected;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			processor = new int[N][N];
			cores = new ArrayList<>();;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int cur = Integer.parseInt(st.nextToken());
					processor[i][j] = cur;
					if (cur == 1 && i != 0 && i != N - 1 && j != 0 && j != N - 1) cores.add(new Core(i, j));
				}
			}
			
			maxConnected = 0;
			answer = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int depth, int connected, int length) {
        if (connected + (cores.size() - depth) < maxConnected) return;
        
		if (depth == cores.size()) {
			if (connected > maxConnected) {
				maxConnected = connected;
				answer = length;
			}
			else if (connected == maxConnected) {
				answer = Math.min(answer, length);
			}
			return;
		}
		
		int x = cores.get(depth).x;
		int y = cores.get(depth).y;
		
		for (int i = 0; i < 4; i++) {
			int nx = x;
			int ny = y;
			
			int len = 0;
			boolean canConnect = true;
			
			while (true) {
				nx += dx[i];
				ny += dy[i];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
				
				if (processor[nx][ny] != 0) {
					canConnect = false;
					break;
				}
				
				len++;
			}
			
			if (canConnect) {
				int wireX = x;
				int wireY = y;
				
				for (int j = 0; j < len; j++) {
					wireX += dx[i];
					wireY += dy[i];
					
					processor[wireX][wireY] = 2;
				}
				
				dfs(depth + 1, connected + 1, length + len);
				
				wireX = x;
				wireY = y;
				
				for (int j = 0; j < len; j++) {
					wireX += dx[i];
					wireY += dy[i];
					
					processor[wireX][wireY] = 0;
				}
			}
		}
		
		dfs(depth + 1, connected, length);
	}

}
