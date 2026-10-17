class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[] dp = new int[n];
        
        dp[0] = triangle[0][0];
        
        for (int i = 1; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (j == 0) {
                    dp[j] = dp[j] + triangle[i][j];
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]) + triangle[i][j];
                }
            }
        }
        
        int answer = -1;
        for (int max : dp) {
            answer = Math.max(answer, max);
        }
        
        return answer;
    }
}