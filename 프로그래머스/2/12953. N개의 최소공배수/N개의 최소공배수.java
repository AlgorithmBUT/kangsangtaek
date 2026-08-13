class Solution {
    public int solution(int[] arr) {
        int answer = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            answer = answer * arr[i] / gcd(answer, arr[i]);
        }
        
        return answer;
    }
    
    int gcd(int a, int b) {
        while(a % b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        
        return b;
    }
}