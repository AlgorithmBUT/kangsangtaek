import java.util.*;

class Solution {
    
    class Robot {
        int curX;
        int curY;
        int[] path;     // 이동 경로
        int curIndex;   // 다음 목적지 인덱스
        
        Robot(int curX, int curY, int[] path) {
            this.curX = curX;
            this.curY = curY;
            this.path = path;
            curIndex = 1;
        }
    }
    
    int[][] points;
    int answer;
    List<Robot> robots;
    
    public int solution(int[][] points, int[][] routes) {
        this.points = points;
        answer = 0;
        robots = new ArrayList<>();
        
        // 로봇 출발지, 이동 경로 초기화
        for (int i = 0; i < routes.length; i++) {
            int[] start = points[routes[i][0] - 1];
            robots.add(new Robot(start[0], start[1], routes[i]));
        }
        
        move();
        
        return answer;
    }
    
    void move() {
        while (!robots.isEmpty()) {
            // 좌표별 로봇 수 저장
            Map<Integer, Integer> collisions = new HashMap<>();
            
            // 현재 위치 기록
            for (Robot robot : robots) {
                int position = robot.curX * 101 + robot.curY;
                collisions.put(position, collisions.getOrDefault(position, 0) + 1);
            }
            
            // 충돌 횟수 계산
            for (int count : collisions.values()) {
                if (count >= 2) answer++;
            }
            
            // 모든 로봇 1초 동안 한 칸씩 이동
            for (int i = robots.size() - 1; i >= 0; i--) {
                Robot robot = robots.get(i);
                
                // 최종 목적지에 도착한 로봇 제거
                if (robot.curIndex == robot.path.length) {
                    robots.remove(i);
                    continue;
                }
                
                // 다음 목적지 좌표
                int destination = robot.path[robot.curIndex] - 1;
                int desX = points[destination][0];
                int desY = points[destination][1];
                
                if (robot.curX < desX) robot.curX++;
                else if (robot.curX > desX) robot.curX--;
                else if (robot.curY < desY) robot.curY++;
                else if (robot.curY > desY) robot.curY--;
                
                // 경유지 도착 시 다음 목적지로 변경
                if (robot.curX == desX && robot.curY == desY) {
                    robot.curIndex++;
                }
            }
        }
    }
    
}