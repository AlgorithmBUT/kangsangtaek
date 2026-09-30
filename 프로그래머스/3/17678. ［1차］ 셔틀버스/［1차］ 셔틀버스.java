import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        // 비교, 계산을 위해 정수로 변경 후 정렬
        int[] minuteTime = toMinute(timetable);
        Arrays.sort(minuteTime);
        
        int crewIdx = 0;
        int answer = 0;
        // 셔틀 운행 횟수만큼 순회
        for (int bus = 0; bus < n; bus++) {
            int busTime = 540 + bus * t;    // 셔틀 출발 시간
            int count = 0;                  // 현재 탄 사람
            
            // 사람 태우기
            while (
                crewIdx < minuteTime.length &&      // 탈 사람이 남아있고
                minuteTime[crewIdx] <= busTime &&   // 셔틀 출발 시간 전에 대기했고
                count < m                           // 현재 탄 사람이 정원보다 적을 경우
            ) {
                crewIdx++;
                count++;
            }
            
            // 막차라면
            if (bus == n - 1) {
                if (count < m) answer = busTime;            // 자리가 남으면 버스 출발 시간에 타기
                else answer = minuteTime[crewIdx - 1] - 1;  // 자리가 없으면 마지막 탄 사람보다 1분 먼저 타기
            }
        }
        
        int hour = (answer / 60);
        int minute = (answer % 60);
        
        return String.format("%02d:%02d", hour, minute);
    }
    
    // 분단위로 변형
    int[] toMinute(String[] timetable) {
        int[] time = new int[timetable.length];
        
        for (int i = 0; i < timetable.length; i++) {
            String[] t = timetable[i].split(":");
            time[i] = (Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]));
        }
        
        return time;
    }
}