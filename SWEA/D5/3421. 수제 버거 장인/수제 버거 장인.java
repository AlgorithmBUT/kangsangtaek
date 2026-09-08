import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, M;
	static boolean[][] impossible;
	static boolean[] selected;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			impossible = new boolean[N][N];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				impossible[a][b] = true;
				impossible[b][a] = true;
			}
			
			selected = new boolean[N];
			answer = 0;
			
			dfs(0);
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int depth) {
		if (depth == N) {
			answer++;
			return;
		}
		
		dfs(depth + 1);
		
		boolean possible = true;
		
		for (int i = 0; i < depth; i++) {
			if (selected[i] && impossible[depth][i]) {
				possible = false;
				break;
			}
		}
		
		if (possible) {
			selected[depth] = true;
			dfs(depth + 1);
			selected[depth] = false;
		}
		
	}

}
