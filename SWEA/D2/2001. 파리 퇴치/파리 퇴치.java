import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] prefix = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					prefix[i][j] = Integer.parseInt(st.nextToken()) + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
				}
			}
			
			int answer = 0;
			for (int i = M; i <= N; i++) {
				for (int j = M; j <= N; j++) {
					int sum = prefix[i][j] - prefix[i - M][j] - prefix[i][j - M] + prefix[i - M][j - M];
					answer = Math.max(answer, sum);
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
