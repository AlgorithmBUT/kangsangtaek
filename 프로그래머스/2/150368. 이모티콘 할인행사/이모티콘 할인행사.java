class Solution {
    
    int[][] users;
    int[] emoticons;
    int[] discounts = { 10, 20, 30, 40 };   // 적용 가능한 할인율
    int[] selected; // 각 이모티콘에 적용할 할인율
    int[] answer;
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        
        selected = new int[emoticons.length];
        answer = new int[2];
        
        dfs(0);
        
        return answer;
    }
    
    void dfs(int depth) {
        // 모든 이모티콘 할인율 지정한 경우
        if (depth == emoticons.length) {
            calculate();
            return;
        }
        
        // 현재 이모티콘에 적용 가능한 모든 할인율 탐색
        for (int discount : discounts) {
            selected[depth] = discount; // 현재 이모티콘의 할인율 지정
            dfs(depth + 1);             // 다음 이모티콘의 할인율 지정
        }
    }
    
    // 현재 할인율 조합에 대한 가입자 수와 이모티콘 판매액 계산
    void calculate() {
        int subscribers = 0;    // 가입자 수
        int sales = 0;          // 이모티콘 판매액
        
        // 모든 사용자 순회
        for (int[] user : users) {
            int discount = user[0];
            int money = user[1];
            int sum = 0;
            
            // 모든 이모티콘 순회
            for (int i = 0; i < emoticons.length; i++) {
                if (selected[i] < discount) continue;
                sum += emoticons[i] * (100 - selected[i]) / 100;
            }
            
            if (sum >= money) {
                subscribers++;
            } else {
                sales += sum;
            }
        }
        
        // 정답 갱신
        if (subscribers > answer[0]) {
            answer[0] = subscribers;
            answer[1] = sales;
        } else if (subscribers == answer[0] && sales > answer[1]) {
            answer[1] = sales;
        }
    }
}