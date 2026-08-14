import java.util.*;

class Solution {
    
    int[] dx = { -1, 1, 0, 0 };
    int[] dy = { 0, 0, -1, 1 };
    
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        
        int startX = 0;
        int startY = 0;
        
        OUTER:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i].charAt(j) == 'R') {
                    startX = i;
                    startY = j;
                    break OUTER;
                }
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.offer(new int[] { startX, startY, 0 });
        visited[startX][startY] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int count = cur[2];
            
            if (board[x].charAt(y) == 'G') return count;
            
            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                
                while (true) {
                    int xx = nx + dx[i];
                    int yy = ny + dy[i];
                    
                    if (xx < 0 || xx >= n || yy < 0 || yy >= m || board[xx].charAt(yy) == 'D') break;
                    nx = xx;
                    ny = yy;
                }
                
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[] { nx, ny, count + 1 });
                }
            }
        }
        
        return -1;
    }
}