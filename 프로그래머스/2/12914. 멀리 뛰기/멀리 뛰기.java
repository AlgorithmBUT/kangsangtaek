class Solution {
    public long solution(int n) {
        if (n == 1) return 1;
        
        long[] answer = new long[n + 1];
        
        answer[1] = 1;
        answer[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            answer[i] = (answer[i - 2] + answer[i - 1]) % 1234567;
        }
        
        return answer[n];
    }
}