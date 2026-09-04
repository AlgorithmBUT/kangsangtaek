import java.io.*;
import java.util.*;

public class Solution {
	
	static int H;
	static int W;
	static char[][] map;
	static int x;
	static int y;
	static int dir;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[] dirChar = { '^', 'v', '<', '>' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					char cur = str.charAt(j);
					map[i][j] = cur;
					if ("^v<>".indexOf(cur) != -1) {
						x = i;
						y = j;
						dir = "^v<>".indexOf(cur);
					}
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			for (char c : br.readLine().toCharArray()) {
				if (c == 'S') shoot();
				else move(c);
			}
			
			sb.append("#").append(test_case).append(" ");
			for (char[] m : map) {
				sb.append(m).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static void move(char command) {
		 dir = "UDLR".indexOf(command);
		 map[x][y] = dirChar[dir];
		 int nx = x + dx[dir];
		 int ny = y + dy[dir];
		 
		 if (nx < 0 || nx >= H || ny < 0 || ny >= W) return;
		 if (map[nx][ny] != '.') return;
		 
		 map[nx][ny] = dirChar[dir];
		 map[x][y] = '.';
		 x = nx;
		 y = ny;
	}
	
	static void shoot() {
		int nx = x;
		int ny = y;
		
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			
			if (nx < 0 || nx >= H || ny < 0 || ny >= W) break;
			if (map[nx][ny] == '#') break;
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				break;
			}
		}
	}
}
