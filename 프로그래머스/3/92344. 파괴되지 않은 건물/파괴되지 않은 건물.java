class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        
        int[][] prefix = new int[N + 1][M + 1];
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            
            int d = (type == 1) ? degree : -degree;
            prefix[r1][c1] -= d;
            prefix[r1][c2 + 1] += d;
            prefix[r2 + 1][c1] += d;
            prefix[r2 + 1][c2 + 1] -= d;
        }
        
        int pN = prefix.length;
        int pM = prefix[0].length;
        for (int i = 0; i < pN; i++) {
            for (int j = 1; j < pM; j++) {
                prefix[i][j] += prefix[i][j - 1];
            }
        }
            
        for (int j = 0; j < pM; j++) {
            for (int i = 1; i < pN; i++) {
                prefix[i][j] += prefix[i - 1][j];
            }
        }
        
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + prefix[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
}