import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        
        int[] minuteTime = toMinute(timetable);
        Arrays.sort(minuteTime);
        
        int last = 540 + (n * t);
        int crewIdx = 0;
        int ans = 0;
        for (int i = 540; i < last; i += t) {
            int count = 0;
            
            while (crewIdx < minuteTime.length) {
                if (minuteTime[crewIdx] > i) break;
                if (count >= m) break;
                
                crewIdx++;
                count++;
            }
            
            if (n != 1) n--;
            else {
                if (count < m) ans = i;
                else if (count == m) ans = minuteTime[crewIdx - 1] - 1;
            }
        }
        
        int hour = (ans / 60);
        String HH = hour < 10 ? "0" + hour : String.valueOf(hour);
        int minute = (ans % 60);
        String MM = minute < 10 ? "0" + minute : String.valueOf(minute);
        
        return HH + ":" + MM;
    }
    
    int[] toMinute(String[] timetable) {
        int[] time = new int[timetable.length];
        
        for (int i = 0; i < timetable.length; i++) {
            String[] t = timetable[i].split(":");
            time[i] = (Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]));
        }
        
        return time;
    }
}