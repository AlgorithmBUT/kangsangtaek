class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int size = brown + yellow;
        
        for (int h = 3; h < brown; h++) {
            if (size % h != 0) continue;
            
            int w = size / h;
            
            if ((h - 2) * (w - 2) == yellow) {
                answer[0] = w;
                answer[1] = h;
                break;
            }
        }
        
        return answer;
    }
}