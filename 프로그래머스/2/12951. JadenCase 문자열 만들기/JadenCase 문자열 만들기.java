class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();
        boolean isBlank = true;
        
        for (String str : s.toLowerCase().split("")) {    
            if (str.equals(" ")) {
                answer.append(str);
                isBlank = true;
            } else {
                answer.append(isBlank ? str.toUpperCase() : str);
                isBlank = false;
            }
        }
        
        return answer.toString();
    }
}