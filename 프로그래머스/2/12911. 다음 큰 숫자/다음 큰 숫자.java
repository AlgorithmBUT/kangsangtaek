class Solution {
    public int solution(int n) {
        int answer = n + 1;
        int nBi = 0;
        for (char c : Integer.toBinaryString(n).toCharArray()) {
            if (c == '1') nBi++;
        }
        
        while (true) {
            int aBi = 0;
            
            for (char c : Integer.toBinaryString(answer).toCharArray()) {
                if (c == '1') aBi++;
                if (aBi > nBi) break;
            }
            
            if (aBi == nBi) break;
            else answer++;
        }
        
        return answer;
    }
}