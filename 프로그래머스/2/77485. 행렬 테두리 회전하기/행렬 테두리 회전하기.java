import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int num = 1;
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = num++;
            }
        }
        
        for (int q = 0; q < queries.length; q++) {
            int[] querie = queries[q];
            int x1 = querie[0] - 1;
            int y1 = querie[1] - 1;
            int x2 = querie[2] - 1;
            int y2 = querie[3] - 1;
            
            int temp = matrix[x1][y1];
            int min = temp;
            
            for (int i = x1; i < x2; i++) {
                int moved = matrix[i + 1][y1];
                matrix[i][y1] = moved;
                min = Math.min(min, moved);
            }
            
            for (int i = y1; i < y2; i++) {
                int moved = matrix[x2][i + 1];
                matrix[x2][i] = moved;
                min = Math.min(min, moved);
            }
            
            for (int i = x2; i > x1; i--) {
                int moved = matrix[i - 1][y2];
                matrix[i][y2] = moved;
                min = Math.min(min, moved);
            }
            
            for (int i = y2; i > y1; i--) {
                int moved = matrix[x1][i - 1];
                matrix[x1][i] = moved;
                min = Math.min(min, moved);
            }
            
            matrix[x1][y1 + 1] = temp;
            
            answer[q] = min;
        }
        
        return answer;
    }
}