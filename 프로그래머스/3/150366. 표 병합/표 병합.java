import java.util.*;

class Solution {
    
    final int N = 50;
    String[][] board = new String[N][N];
    int[] root = new int[N * N];
    List<String> answer = new ArrayList<>();
    
    public String[] solution(String[] commands) {
        for (int i = 0; i < N * N; i++) {
            root[i] = i;
        }
        
        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            
            String cmd = st.nextToken();
            
            if (cmd.equals("UPDATE")) {
                if (st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken()) - 1;
                    String value = st.nextToken();
                    update(r, c, value);
                } else {
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    update(value1, value2);
                }
            } else if (cmd.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int c1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;
                int c2 = Integer.parseInt(st.nextToken()) - 1;
                merge(r1, c1, r2, c2);
            } else if (cmd.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                unmerge(r, c);
            } else if (cmd.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                print(r, c);
            }
        }
        
        return answer.toArray(new String[0]);
    }
    
    void update(int r, int c, String value) {
        int x = find(r * N + c);
        board[x / N][x % N] = value;
    }
    
    void update(String value1, String value2) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int idx = i * N + j;
                
                if (find(idx) == idx && value1.equals(board[i][j])) {
                    board[i][j] = value2;
                }
            }
        }
    }
    
    void merge(int r1, int c1, int r2, int c2) {
        int root1 = find(r1 * N + c1);
        int root2 = find(r2 * N + c2);
        
        String value1 = board[root1 / N][root1 % N];
        String value2 = board[root2 / N][root2 % N];
        
        String str = value1 != null ? value1 : value2;
        
        if (root1 != root2) {
            root[root2] = root1;
            
            if (value1 != null) {
                board[root1 / N][root1 % N] = str;
            } else {
                board[root1 / N][root1 % N] = str;
            }
            
            board[root2 / N][root2 % N] = null;
        }
    }
    
    void unmerge(int r, int c) {
        int x = find(r * N + c);
        String str = board[x / N][x % N];
        
        List<Integer> group = new ArrayList<>();
        
        for (int i = 0; i < N * N; i++) {
            if (find(i) == x) {
                group.add(i);
            }
        }
        
        for (int idx : group) {
            root[idx] = idx;
            board[idx / N][idx % N] = null;
        }
        
        board[r][c] = str;
    }
    
    void print(int r, int c) {
        int x = find(r * N + c);
        
        String str = board[x / N][x % N] == null ? "EMPTY" : board[x / N][x % N];
        
        answer.add(str);
    }
    
    int find(int x) {
        if (root[x] == x) return x;
        else return root[x] = find(root[x]);
    }
}