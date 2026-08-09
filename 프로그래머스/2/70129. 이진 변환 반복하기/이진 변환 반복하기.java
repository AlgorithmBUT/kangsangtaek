class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        int zero = 0;
        int transform = 0;
        
        while (!s.equals("1")) {
            for (char c : s.toCharArray()) {
                if (c == '0') zero++;
            }
            
            s = s.replace("0", "");
            s = Integer.toBinaryString(s.length());
            transform++;
        }
        
        answer[0] = transform;
        answer[1] = zero;
        
        return answer;
    }
}