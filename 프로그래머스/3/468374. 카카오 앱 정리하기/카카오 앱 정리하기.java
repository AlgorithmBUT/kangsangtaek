import java.util.*;

class Solution {
    
    class App {
        int id;
        int startX;
        int startY;
        int w;
        
        App (int id, int startX, int startY, int w) {
            this.id = id;
            this.startX = startX;
            this.startY = startY;
            this.w = w;
        }
    }
    
    int N, M;
    int[][] board;
    int[] dx = { 0, 1, 0, -1 };
    int[] dy = { 1, 0, -1, 0 };
    Map<Integer, App> apps;
    
    public int[][] solution(int[][] board, int[][] commands) {
        N = board.length;
        M = board[0].length;
        
        this.board = board;
        
        apps = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int id = board[i][j];
                
                if (id == 0) continue;
                
                if (!apps.containsKey(id)) {
                    int width = 0;
                    for (int k = j; k < M && board[i][k] == id; k++) {
                        width++;
                    }
                    
                    apps.put(id, new App(id, i, j, width));
                }
            }
        }
        
        for (int[] command : commands) {
            int id = command[0];
            int dir = command[1] - 1;
            
            push(id, dir);
        }
        
        return board;
    }
    
    void push(int id, int dir) {
        Set<Integer> group = chaining(id, dir);
        
        move(group, dir);
        
        while (true) {
            int brokenId = findBrokenApp(id, dir);
            
            if (brokenId == -1) break;
            
            group = chaining(brokenId, dir);
            
            move(group, dir);
        }
    }
    
    Set<Integer> chaining(int startId, int dir) {
        Set<Integer> group = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        
        group.add(startId);
        q.offer(startId);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            Set<Integer> nextApps = findNextApps(cur, dir);
            
            for (int next : nextApps) {
                if (!group.contains(next)) {
                    group.add(next);
                    q.offer(next);
                }
            }
        }
        
        return group;
    }
    
    Set<Integer> findNextApps(int cur, int dir) {
        Set<Integer> nextApps = new HashSet<>();
        App app = apps.get(cur);
        
        if (dir == 0) {
            int y = (app.startY + app.w) % M;
            
            for (int i = 0; i < app.w; i++) {
                int x = (app.startX + i) % N;
                
                int next = board[x][y];
                
                if (next != 0 && next != cur) {
                    nextApps.add(next);
                }
            }
        } else if (dir == 1) {
            int x = (app.startX + app.w) % N;
            
            for (int i = 0; i < app.w; i++) {
                int y = (app.startY + i) % M;
                
                int next = board[x][y];
                
                if (next != 0 && next != cur) {
                    nextApps.add(next);
                }
            }
        } else if (dir == 2) {
            int y = (app.startY - 1 + M) % M;
            
            for (int i = 0; i < app.w; i++) {
                int x = (app.startX + i) % N;
                
                int next = board[x][y];
                
                if (next != 0 && next != cur) {
                    nextApps.add(next);
                }
            }
        } else {
            int x = (app.startX - 1 + N) % N;
            
            for (int i = 0; i < app.w; i++) {
                int y = (app.startY + i) % M;
                
                int next = board[x][y];
                
                if (next != 0 && next != cur) {
                    nextApps.add(next);
                }
            }
        }
        
        return nextApps;
    }
    
    void move(Set<Integer> group, int dir) {
        for (int id : group) {
            App app = apps.get(id);
            
            for (int i = 0; i < app.w; i++) {
                for (int j = 0; j < app.w; j++) {
                    int x = (app.startX + i) % N;
                    int y = (app.startY + j) % M;
                    
                    board[x][y] = 0;
                }
            }
        }
        
        for (int id : group) {
            App app = apps.get(id);
            
            app.startX = (app.startX + dx[dir] + N) % N;
            app.startY = (app.startY + dy[dir] + M) % M;
            
            for (int i = 0; i < app.w; i++) {
                for (int j = 0; j < app.w; j++) {
                    int x = (app.startX + i) % N;
                    int y = (app.startY + j) % M;
                    
                    board[x][y] = app.id;
                }
            }
        }
    }
    
    int findBrokenApp(int id, int dir) {
        if (dir == 0 || dir == 2) {
            for (int i = 0; i < N; i++) {
                int left = board[i][0];
                int right = board[i][M - 1];
                
                if (left == 0 || left != right) continue;
                
                if (apps.get(left).w == M) continue;
                
                return left;
            }
        } else {
            for (int j = 0; j < M; j++) {
                int top = board[0][j];
                int bottom = board[N - 1][j];
                
                if (top == 0 || top != bottom) continue;
                
                if (apps.get(top).w == N) continue;
                
                return top;
            }
        }
        
        return -1;
    }
}