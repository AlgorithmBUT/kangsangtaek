import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;
        int dir;
        int dis;

        Node (int x, int y, int dir, int dis) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dis = dis;
        }
    }

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int N;
    static int[][] turn = {
        { 0, 2, 3, 1 },
        { 1, 3, 2, 0 },
        { 2, 1, 0, 3 },
        { 3, 0, 1, 2 }
    };
    static int[] move = { 2, 1, 3, 0 };
    static int[][] sea;
    static boolean[][] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int d = Integer.parseInt(st.nextToken()) - 1;

        sea = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sea[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sb.append(r + 1).append(" ").append(c + 1).append("\n");
        visitSea(r, c, d);

        System.out.println(sb.toString());
    }

    static void visitSea(int x, int y, int d) {
        visited[x][y] = true;

        while (true) {
            boolean moved = false;

            for (int i = 0; i < 4; i++) {
                int nd = turn[d][i];

                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (sea[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                sb.append(nx + 1).append(" ").append(ny + 1).append("\n");
                x = nx;
                y = ny;
                d = nd;
                moved = true;
                break;
            }

            if (!moved) {
                List<Node> near = findNearSea(x, y, d);
                if (near.isEmpty()) return;
                
                Node nearest = getNextSea(near);

                x = nearest.x;
                y = nearest.y;
                d = nearest.dir;
                visited[x][y] = true;
                moved = true;
                sb.append(x + 1).append(" ").append(y + 1).append("\n");
            }

            if (!moved) return;
        }
    }

    static List<Node> findNearSea(int startX, int startY, int d) {
        List<Node> list = new ArrayList<>();

        Queue<Node> q = new LinkedList<>();
        boolean[][] nearVisited = new boolean[N][N];

        q.offer(new Node(startX, startY, d, 0));
        nearVisited[startX][startY] = true;

        int minDis = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int dis = cur.dis;

            if (!visited[x][y] && dis <= minDis) {
                list.add(new Node(x, y, dir, dis));
                minDis = dis;
            } else if (dis > minDis) {
                return list;
            }

            for (int i = 0; i < 4; i++) {
                int nd = move[i];
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (nearVisited[nx][ny]) continue;
                if (sea[nx][ny] == 1) continue;

                q.offer(new Node(nx, ny, nd, dis + 1));
                nearVisited[nx][ny] = true;
            }
        }

        return list;
    }

    static Node getNextSea(List<Node> list) {
        Collections.sort(list, (o1, o2) -> {
            if (o1.x == o2.x) {
                return Integer.compare(o1.y, o2.y);
            }
            return Integer.compare(o1.x, o2.x);
        });

        return list.get(0);
    }
}