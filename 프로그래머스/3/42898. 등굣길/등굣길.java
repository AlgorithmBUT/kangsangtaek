class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n + 1][m + 1];
        boolean[][] isPuddle = new boolean[n + 1][m + 1];
        for (int[] puddle : puddles) {
            int x = puddle[1];
            int y = puddle[0];
            isPuddle[x][y] = true;
            map[x][y] = 0;
        }
        
        map[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i == 1 && j == 1) continue;
                if (isPuddle[i][j]) continue;
                map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1_000_000_007;
            }
        }
        
        return map[n][m];
    }
}