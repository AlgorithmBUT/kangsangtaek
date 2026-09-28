import java.util.*;

class Solution {
    
    // 하나의 칸 좌표를 표현하는 클래스
    class Node {
        int x;
        int y;
        
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    // 하나의 빈칸 또는 하나의 퍼즐 조각을 표현하는 클래스
    class Shape {
        List<Node> nodes;   // 현재 도형을 구성하는 모든 좌표
        
        Shape(List<Node> nodes) {
            this.nodes = nodes;
            normalize();    // 모양만 중요하기 때문에 즉시 정규화
        }
        
        // 위치 차이 제거를 위한 정규화 (좌측 상단 기준으로 정규화)
        void normalize() {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            
            // 현재 도형 좌표 중 가장 작은 x, y 값 탐색
            for (Node n : nodes) {
                minX = Math.min(minX, n.x);
                minY = Math.min(minY, n.y);
            }
            
            // 최소 x, y를 빼서 좌측 상단으로 이동
            for (Node n : nodes) {
                n.x -= minX;
                n.y -= minY;
            }
            
            // 도형 비교를 위한 정렬(탐색 순서에 따라 nodes에 들어가는 순서가 다를 수 있음)
            nodes.sort((o1, o2) -> {
                if (o1.x == o2.x) {
                    return Integer.compare(o1.y, o2.y);
                }
                return Integer.compare(o1.x, o2.x);
            });
        }
        
        // 현재 도형을 시계 방향으로 회전
        Shape rotate() {
            List<Node> rotated = new ArrayList<>();
            
            for (Node node : nodes) {
                rotated.add(new Node(node.y, -node.x));
            }
            
            // shape을 리턴하기에 정규화 자동 수행
            return new Shape(rotated);
        }
        
        // 현재 Shape와 다른 Shape가 같은 모양인지 확인
        boolean isSame(Shape other) {
            // 칸 개수 비교
            if (nodes.size() != other.nodes.size()) return false;
            
            // 정렬된 좌표 하나씩 비교
            for (int i = 0; i < nodes.size(); i++) {
                Node a = nodes.get(i);
                Node b = other.nodes.get(i);
                
                if (a.x != b.x || a.y != b.y) return false;
            }
            
            return true;
        }
    }
    
    int n;
    List<Shape> blanks;     // 모든 빈칸 도형
    List<Shape> puzzles;    // 모든 퍼즐 조각
    int[] dx = { -1, 1, 0, 0 };
    int[] dy = { 0, 0, -1, 1 };
    
    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;
        
        blanks = findShapes(game_board, 0); // 빈칸 도형 찾기
        puzzles = findShapes(table, 1);     // 퍼즐 조각 찾기
        
        boolean[] used = new boolean[puzzles.size()];
        int answer = 0;
        
        // 빈칸 하나씩 확인
        for (Shape blank : blanks) {
            // 모든 퍼즐 조각 순회
            for (int i = 0 ; i < puzzles.size(); i++) {
                if (used[i]) continue;
                
                Shape puzzle = puzzles.get(i);
                
                // 칸 개수 비교
                if (blank.nodes.size() != puzzle.nodes.size()) continue;
                
                // 회전하며 확인
                for (int r = 0; r < 4; r++) {
                    // 현재 회전한 도형이 빈칸과 일치하면 해당 퍼즐 사용
                    if (blank.isSame(puzzle)) {
                        answer += blank.nodes.size();
                        used[i] = true;
                        break;
                    }
                    
                    puzzle = puzzle.rotate();
                }
                
                if (used[i]) break;
            }
        }
        
        return answer;
    }
    
    // bfs를 통해 각각에 맞는 도형 찾아 반환
    List<Shape> findShapes(int[][] board, int target) {
        List<Shape> shapes = new ArrayList<>(); // 모든 도형 저장
        boolean[][] visited = new boolean[n][n];
        
        // 전체 보드 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != target) continue;
                if (visited[i][j]) continue;
                
                List<Node> nodes = new ArrayList<>();   // 하나의 도형 저장
                Queue<Node> q = new ArrayDeque<>();
                
                q.offer(new Node(i, j));
                visited[i][j] = true;
                
                while (!q.isEmpty()) {
                    Node cur = q.poll();
                    int x = cur.x;
                    int y = cur.y;
                    
                    nodes.add(new Node(x, y));
                    
                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        
                        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                        if (visited[nx][ny]) continue;
                        if (board[nx][ny] != target) continue;
                
                        q.offer(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                
                shapes.add(new Shape(nodes));
            }
        }
        
        return shapes;
    }
    
}