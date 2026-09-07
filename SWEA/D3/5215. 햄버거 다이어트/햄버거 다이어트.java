import java.io.*;
import java.util.*;

public class Solution {

	static int N, L;
	static int[] score;
	static int[] calorie;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			score = new int[N];
			calorie = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				calorie[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = Integer.MIN_VALUE;
			maxScore(0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		
		System.out.println(sb.toString());
	}

	static void maxScore(int depth, int sSum, int cSum) {
		if (cSum > L) return;
		
		if (depth == N) {
			answer = Math.max(answer, sSum);
			return;
		}
		
		maxScore(depth + 1, sSum, cSum);
		maxScore(depth + 1, sSum + score[depth], cSum + calorie[depth]);
	}
}
