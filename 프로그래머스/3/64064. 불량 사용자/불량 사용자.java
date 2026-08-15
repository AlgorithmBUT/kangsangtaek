import java.util.*;

class Solution {
    
    String[] user_id;
    String[] banned_id;
    boolean[] visited;
    Set<String> set = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        visited = new boolean[user_id.length];
        
        dfs(0);
        
        return set.size();
    }
    
    void dfs(int depth) {
        if (depth == banned_id.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) sb.append(user_id[i]);
            }
            
            set.add(sb.toString());
            return;
        }
        
        String ban = banned_id[depth];
        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i] && check(user_id[i], ban)) {
                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
    
    boolean check(String user_id, String ban) {
        if (user_id.length() != ban.length()) return false;
        
        for (int i = 0; i < user_id.length(); i++) {
            if (ban.charAt(i) == '*') continue;
            
            if (user_id.charAt(i) != ban.charAt(i)) return false;
        }
        
        return true;
    }
}