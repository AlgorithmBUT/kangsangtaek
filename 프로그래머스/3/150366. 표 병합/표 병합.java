import java.util.*;

class Solution {
    
    static final int N = 50;
    static final int SIZE = N * N;
    String[] position = new String[SIZE];   // 각 셀의 위치(r * N + c)에 value 저장
    int[] root = new int[SIZE];             // Union-Find 부모 배열
    List<String> answer = new ArrayList<>();
    
    public String[] solution(String[] commands) {
        // 부모 배열 초기화
        for (int i = 0; i < SIZE; i++) {
            root[i] = i;
        }
        
        // 명령어 순회
        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            
            String cmd = st.nextToken();
            
            switch (cmd) {
                case "UPDATE": {
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
                    break;
                }
                
                case "MERGE": {
                    int r1 = Integer.parseInt(st.nextToken()) - 1;
                    int c1 = Integer.parseInt(st.nextToken()) - 1;
                    int r2 = Integer.parseInt(st.nextToken()) - 1;
                    int c2 = Integer.parseInt(st.nextToken()) - 1;
                    merge(r1, c1, r2, c2);
                    break;
                }
                
                case "UNMERGE": {
                    int r = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken()) - 1;
                    unmerge(r, c);
                    break;
                }
                
                case "PRINT": {
                    int r = Integer.parseInt(st.nextToken()) - 1;
                    int c = Integer.parseInt(st.nextToken()) - 1;
                    print(r, c);
                    break;
                }
            }
        }
        
        return answer.toArray(new String[0]);
    }
    
    void update(int r, int c, String value) {
        int parent = find(r * N + c);   // 병합되어 있을 수 있기에 대표 셀 찾기
        position[parent] = value;
    }
    
    void update(String value1, String value2) {
        for (int i = 0; i < SIZE; i++) {
            // 대표 값이 실제 값을 갖고 있기에 root만 확인
            if (find(i) == i && value1.equals(position[i])) {   
                position[i] = value2;
            }
        }
    }
    
    void merge(int r1, int c1, int r2, int c2) {
        int parent1 = find(r1 * N + c1);
        int parent2 = find(r2 * N + c2);
        
        // 이미 같은 그룹이면 return
        if (parent1 == parent2) return;
        
        String value1 = position[parent1];
        String value2 = position[parent2];
        
        // 병합 후 유지할 값 설정
        String str = (value1 != null) ? value1 : value2;
        
        root[parent2] = parent1;
        
        position[parent1] = str;
        
        position[parent2] = null;
    }
    
    void unmerge(int r, int c) {
        int target = r * N + c;
        int parent = find(target);
        String str = position[parent];  // unmerge 후에 유지할 값 저장
        
        List<Integer> group = new ArrayList<>();
        
        // 병합되어 있던 셀 찾기
        for (int i = 0; i < SIZE; i++) {
            if (find(i) == parent) {
                group.add(i);
            }
        }
        
        for (int idx : group) {
            root[idx] = idx;        // 자기 자신을 대표로 설정
            position[idx] = null;   // 전부 값 제거
        }
        
        position[target] = str; // 명령 받은 셀에 값 저장
    }
    
    void print(int r, int c) {
        int parent = find(r * N + c);
        
        String str = position[parent] == null ? "EMPTY" : position[parent];
        
        answer.add(str);
    }
    
    int find(int x) {
        if (root[x] == x) return x;
        else return root[x] = find(root[x]);
    }
}