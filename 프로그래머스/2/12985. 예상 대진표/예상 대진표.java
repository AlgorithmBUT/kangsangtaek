class Solution {
    public int solution(int n, int a, int b) {
        int answer = 1;
        
        while (a != b) {
            a = Math.round(a / 2.0f);
            b = Math.round(b / 2.0f);
            
            if (a == b) break;
            
            answer++;
        }

        return answer;
    }
}