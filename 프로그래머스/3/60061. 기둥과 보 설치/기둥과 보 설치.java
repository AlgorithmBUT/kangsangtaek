import java.util.*;

class Solution {
    
    List<int[]> answer = new ArrayList<>();
    
    public int[][] solution(int n, int[][] build_frame) {
        
        for (int[] bf : build_frame) {
            int x = bf[0];
            int y = bf[1];
            int type = bf[2];
            int actionType = bf[3];
            
            if (actionType == 1 && canBuild(x, y, type)) {
                answer.add(new int[] { x, y, type });
            } else if (actionType == 0) {
                canDelete(x, y, type);
            }
        }
        
        // 정렬
        Collections.sort(answer, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            }
            if (o1[1] != o2[1]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[2], o2[2]);
        });
        
        return answer.toArray(new int[0][]);
    }
    
    // 설치
    boolean canBuild(int x, int y, int type) {
        if (type == 0) {    // 기둥
            if (y == 0) return true;
            else {
                for (int[] ans : answer) {
                    int ansX = ans[0];
                    int ansY = ans[1];
                    int ansT = ans[2];
                        
                    // 기반 구조물 확인
                    if (ansT == 0) {    // 기반이 기둥이라면
                        if (ansX == x && ansY + 1 == y) return true;
                    } else {    // 기반이 보라면
                        if (ansX == x - 1 && ansY == y) return true;
                        if (ansX == x && ansY == y) return true;
                    }
                }
            }
        } else {    // 보
            // 보 설치를 위한 좌우 확인
            boolean left = false;
            boolean right = false;
                
            for (int[] ans : answer) {
                int ansX = ans[0];
                int ansY = ans[1];
                int ansT = ans[2];
                    
                if (ansT == 0) {    // 기반이 기둥이라면
                    // 좌우 한 곳에 기둥이 있다면
                    if (ansX == x && ansY + 1 == y) return true;
                    if (ansX == x + 1 && ansY + 1 == y) return true;
                } else {    // 기반이 보라면
                    // 좌우 전부 확인
                    if (ansX == x - 1 && ansY == y) left = true;
                    if (ansX == x + 1 && ansY == y) right = true;
                }
            }
                
            if (left && right) return true;
        }
        
        return false;
    }
    
    // 삭제
    boolean canDelete(int x, int y, int type) {
        int[] removed = null;
        
        // 일단 삭제
        for (int i = 0; i < answer.size(); i++) {
            int[] arr = answer.get(i);
            
            if (arr[0] == x && arr[1] == y && arr[2] == type) {
                removed = arr;
                answer.remove(i);
                break;
            }
        }
        
        boolean valid = true;
        
        // 삭제 후에도 모두 정상인지 확인
        for (int[] ans : answer) {
            if (!canBuild(ans[0], ans[1], ans[2])) {
                valid = false;
                break;
            }
        }
        
        if (!valid) answer.add(removed);
        
        return valid;
    }
        
}