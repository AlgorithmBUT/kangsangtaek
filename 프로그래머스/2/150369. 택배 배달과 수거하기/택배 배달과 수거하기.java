class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int dIdx = n - 1;
        int pIdx = n - 1;
        
        while (dIdx >= 0 || pIdx >= 0) {
            // deliveries에서 가장 멀리 떨어진 집 찾기
            while (dIdx >= 0 && deliveries[dIdx] == 0) {
                dIdx--;
            }
            
            // pickups에서 가장 멀리 떨어진 집 찾기
            while (pIdx >= 0 && pickups[pIdx] == 0) {
                pIdx--;
            }
            
            // 둘 다 끝났으면 종료
            if (dIdx < 0 && pIdx < 0) break;
            
            // 현재 가장 멀리 떨어진 집
            int far = Math.max(dIdx, pIdx);
            
            // 배달
            dIdx = process(deliveries, dIdx, cap);
            
            // 수거
            pIdx = process(pickups, pIdx, cap);
            
            answer += (long) (far + 1) * 2;
        }
        
        return answer;
    }
    
    private int process(int[] boxes, int idx, int cap) {
        while (idx >= 0 && cap > 0) {
            // 해당 위치의 값이 0이면 넘어가기
            if (boxes[idx] == 0) {
                idx--;
                continue;
            }
            
            // 해당 위치가 cap보다 클 경우
            if (boxes[idx] > cap) {
                boxes[idx] -= cap;
                cap = 0;
            }
            // 해당 위치가 cap보다 작을 경우
            else {
                cap -= boxes[idx];
                boxes[idx] = 0;
                idx--;
            }
        }
        
        return idx;
    }
}