import java.util.*;

class Solution {
    
    class Room {
        int startTime;
        int endTime;
        
        Room (int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
    
    public int solution(String[][] book_time) {
        Arrays.sort(book_time, (o1, o2) -> {
           return o1[0].compareTo(o2[0]);
        });
        
        Queue<Room> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.endTime, o2.endTime));
        
        int answer = 0;
        for (String[] book : book_time) {
            int start = toMinute(book[0]);
            int end = toMinute(book[1]) + 10;
            
            while (!pq.isEmpty() && pq.peek().endTime <= start) {
                pq.poll();
            }
            
            pq.offer(new Room(start, end));
            answer = Math.max(answer, pq.size());
        }
        
        return answer;
    }
    
    int toMinute(String time) {
        StringTokenizer st = new StringTokenizer(time, ":"); 
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        
        return hour * 60 + minute;
    }
}