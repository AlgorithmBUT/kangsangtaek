class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int len = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = sequence[0];
        
        while (left <= right && right < sequence.length) {
            if (sum < k) {
                right++;
                if (right >= sequence.length) break;
                sum += sequence[right];
            } else if (sum > k) {
                sum -= sequence[left];
                left++;
            } else {
                if (right - left < len) {
                    answer[0] = left;
                    answer[1] = right;
                    len = right - left;
                }
                sum -= sequence[left];
                left++;
            }
        }
        
        return answer;
    }
}