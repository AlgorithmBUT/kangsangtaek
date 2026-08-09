import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        
        Arrays.sort(files, (o1, o2) -> {
            String[] o1File = split(o1);
            String[] o2File = split(o2);
            
            if (!o1File[0].equalsIgnoreCase(o2File[0])) {
                return o1File[0].compareToIgnoreCase(o2File[0]);
            }
            
            int o1Num = Integer.parseInt(o1File[1]);
            int o2Num = Integer.parseInt(o2File[1]);
            if (o1Num != o2Num) {
                return Integer.compare(o1Num, o2Num);
            }
            
            return 0;
        });
        
        
        return files;
    }
    
    String[] split(String file) {
        int start = 0;
        while (!Character.isDigit(file.charAt(start))) start++;
        
        int end = start;
        while (end < file.length() && Character.isDigit(file.charAt(end))) end++;
        
        String head = file.substring(0, start);
        String number = file.substring(start, end);
        String tail = file.substring(end);
        
        return new String[] { head, number, tail };
    }
}