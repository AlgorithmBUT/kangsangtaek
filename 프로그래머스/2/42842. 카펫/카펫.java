class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int size = brown + yellow;
        
        for (int i = 3; i < brown; i++) {
            if (size % i != 0) continue;
            
            int h = size / i;
            
            if ((i - 2) * (h - 2) == yellow) {
                answer[0] = i;
                answer[1] = h;
            }
        }
        
        return answer;
    }
}