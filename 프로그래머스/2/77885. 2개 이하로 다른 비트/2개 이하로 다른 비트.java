class Solution {
    public long[] solution(long[] numbers) {
        int n = numbers.length;
        long[] answer = new long[n];
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n; i++) {
            // 홀수의 경우
            // 오른쪽부터 처음 등장하는 0을 1로 바꾸고, 그 바로 오른쪽의 1을 0으로 바꾸면 정답
            if (numbers[i] % 2 == 1) {
                sb.append("0").append(Long.toBinaryString(numbers[i]));
                
                for (int j = sb.length() - 1; j >= 0; j--) {
                    if (sb.charAt(j) == '0') {
                        sb.setCharAt(j, '1');
                        sb.setCharAt(j + 1, '0');
                        break;
                    }
                }
                
                answer[i] = Long.parseLong(sb.toString(), 2);
            }
            // 짝수의 경우
            // 해당 숫자에 +1을 하면 정답
            else {
                answer[i] = numbers[i] + 1;
            }
            
            sb.setLength(0);
        }
        
        return answer;
    }
}