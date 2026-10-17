class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                // 왼쪽 끝
                if (j == 0) {
                    dp[i][0] = dp[i - 1][0] + triangle[i][0];
                }
                // 오른쪽 끝
                else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                }
                // 중간
                else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }
        
        int answer = -1;
        for (int max : dp[n - 1]) {
            answer = Math.max(answer, max);
        }
        
        return answer;
    }
}