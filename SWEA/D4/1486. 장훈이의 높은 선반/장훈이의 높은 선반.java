import java.io.*;
import java.util.*;

public class Solution {
	
	static int N, B;
	static int[] h;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			h = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				h[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MAX_VALUE;
			dfs(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(answer - B).append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int depth, int sum) {
		if (sum > answer) return;
		
		if (depth == N) {
			answer = (sum >= B) ? Math.min(answer, sum) : answer;
			return;
		}
		
		dfs(depth + 1, sum + h[depth]);
		dfs(depth + 1, sum);
	}

}
