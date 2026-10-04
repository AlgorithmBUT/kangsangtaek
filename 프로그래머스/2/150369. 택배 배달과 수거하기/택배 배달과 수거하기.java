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
            int cur = cap;
            while (dIdx >= 0 && cur > 0) {
                if (deliveries[dIdx] == 0) {
                    dIdx--;
                    continue;
                }
                
                if (deliveries[dIdx] > cur) {
                    deliveries[dIdx] -= cur;
                    cur = 0;
                } else {
                    cur -= deliveries[dIdx];
                    deliveries[dIdx] = 0;
                    dIdx--;
                }
            }
            
            // 수거
            cur = cap;
            while (pIdx >= 0 && cur > 0) {
                if (pickups[pIdx] == 0) {
                    pIdx--;
                    continue;
                }
                
                if (pickups[pIdx] > cur) {
                    pickups[pIdx] -= cur;
                    cur = 0;
                } else {
                    cur -= pickups[pIdx];
                    pickups[pIdx] = 0;
                    pIdx--;
                }
            }
            
            answer += (long) (far + 1) * 2;
        }
        
        return answer;
    }
}