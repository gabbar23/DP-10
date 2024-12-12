class Solution {
    public int superEggDrop(int k, int n) {

        int[][] dp = new int[k + 1][n + 1];


        for (int egg = 1; egg <= k; egg++) {
            for (int floor = 1; floor <= n; floor++) {
                if (egg == 1) {
                    dp[egg][floor] = floor;
                } else if (floor == 1) {
                    dp[egg][floor] = 1;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int i = 0, j = floor - 1; i < floor; i++, j--) {
                        int option1 = dp[egg][j];//egg survive
                        int option2 = dp[egg - 1][i];//egg donot survice survive
                        min = Math.min(min, Math.max(option1, option2));
                    }
                    dp[egg][floor]=min+1;
                }
            }
        }
        return dp[k][n];
    }
}
