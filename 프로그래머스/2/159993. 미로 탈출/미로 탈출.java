import java.util.*;

class Node {
    int x;
    int y;
    int count;
    
    Node(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

class Solution {
    
    String[] maps;
    int n;
    int m;
    int[] dx = { -1, 1, 0, 0 };
    int[] dy = { 0, 0, -1, 1 };
    
    public int solution(String[] maps) {
        this.maps = maps;
        n = maps.length;
        m = maps[0].length();
        
        int sX = 0;
        int sY = 0;
        int lX = 0;
        int lY = 0;
        int eX = 0;
        int eY = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i].charAt(j) == 'S') {
                    sX = i;
                    sY = j;
                } else if (maps[i].charAt(j) == 'L') {
                    lX = i;
                    lY = j;
                } else if (maps[i].charAt(j) == 'E') {
                    eX = i;
                    eY = j;
                }
            }
        }
        
        int STOL = bfs(sX, sY, lX, lY);
        if (STOL == -1) return -1;
        
        int LTOE = bfs(lX, lY, eX, eY);
        if (LTOE == -1) return -1;
            
        return STOL + LTOE;
    }
    
    int bfs(int startX, int startY, int endX, int endY) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new Node(startX, startY, 0));
        visited[startX][startY] = true;
        
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int count = cur.count;
            
            if (x == endX && y == endY) return count;
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || maps[nx].charAt(ny) == 'X') continue;
                
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, count + 1));
                }
            }
        }
        
        return -1;
    }
}