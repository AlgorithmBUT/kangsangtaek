class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int count = 1;
        while (count <= n) {
            int sum = 0;
            for (int i = count; i <= n; i++) {
                sum += i;
                if (sum > n) break;
                else if (sum == n) {
                    answer++;
                    break;
                }
            }
            
            count++;
        }
        
        return answer;
    }
}