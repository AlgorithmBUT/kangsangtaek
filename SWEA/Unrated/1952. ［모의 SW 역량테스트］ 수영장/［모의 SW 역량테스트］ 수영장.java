import java.io.*;
import java.util.*;

public class Solution {

	static final int PRICETYPE = 4;				// 이용권 종류: 1일, 1달, 3달, 1년
	static final int MONTH = 12;
	static int[] price = new int[PRICETYPE];	// 각 이용권의 요금을 담은 배열
	static int[] plan = new int[MONTH];			// 각 달의 이용 계획을 담은 배열
	static int minCost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			// 이용권 가격 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < PRICETYPE; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}

			// 1월 ~ 12월 수영장 이용 계획 입력 
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < MONTH; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			minCost = price[3];					// 1년 이용권을 초기값으로 설정
			// dfs(0, 0);						// DFS 방식
			minCost = Math.min(minCost, dp());	// DP 방식

			sb.append("#").append(test_case).append(" ").append(minCost).append("\n");
		}

		System.out.println(sb.toString());
	}
	
	// DFS
	// 현재 month에서 선택할 수 있는 이용권을 하나씩 선택하면서 모든 경우의 수 탐색
	static void dfs(int month, int cost) {
		// 12개월 모두 처리 시 최소 비용 갱신
		if (month >= MONTH) {
			minCost = Math.min(minCost, cost);
			return;
		}
		
		// 현재 달을 1일 이용권으로 이용
		dfs(month + 1, cost + (plan[month] * price[0]));
		// 현재 달을 1달 이용권으로 이용
		dfs(month + 1, cost + price[1]);
		// 현재 달부터 3개월권으로 이용
		dfs(month + 3, cost + price[2]);
	}
	
	// DP
	static int dp() {
		// 1개월부터 12개월까지 사용하기 위해 MONTH + 1 크기로 지정
		int[] minCostByMonth = new int[MONTH + 1];
		
		for (int i = 1; i <= MONTH; i++) {
			// 현재 달에서 1일 이용권과 1개월 이용권 중 더 작은 값을 선택
			// 이전 달까지의 최소 비용에 현재 달 최소 비용 합
			minCostByMonth[i] = minCostByMonth[i - 1] + Math.min(plan[i - 1] * price[0], price[1]);
			
			// i번째 달까지의 최소 비용을 구할 때
			// 이전 3개월까지의 최소 비용 + 3달 이용권 가격
			if (i >= 3) {
				minCostByMonth[i] = Math.min(minCostByMonth[i], minCostByMonth[i - 3] + price[2]);
			}
		}
		
		return minCostByMonth[MONTH];
	}
}
