class Solution {

    public int maxCoins(int[] nums) {
        // Create a new array with 1 at both ends to handle edge cases
        int[] dummyNums = new int[nums.length + 2];
        for (int i = 1; i <= nums.length; i++) {
            dummyNums[i] = nums[i - 1];
        }
        dummyNums[0] = 1;
        dummyNums[dummyNums.length - 1] = 1;

        // Initialize a DP array to store the results of subproblems
        int[][] dp = new int[dummyNums.length][dummyNums.length];

        // Iterate over the subranges in reverse order for the start index
        for (int startOfRange = nums.length; startOfRange >= 1; startOfRange--) {
            // Iterate over the subranges for the end index
            for (int endOfRange = 1; endOfRange <= nums.length; endOfRange++) {
                if (startOfRange > endOfRange) continue;

                int max = Integer.MIN_VALUE;

                // Try all options of picking a balloon as the last one to burst
                for (int idx = startOfRange; idx <= endOfRange; idx++) {
                    int cost = dummyNums[idx] * dummyNums[startOfRange - 1] * dummyNums[endOfRange + 1];
                    int remaining = dp[startOfRange][idx - 1] + dp[idx + 1][endOfRange];
                    max = Math.max(max, cost + remaining);
                }
                // Store the result for this subproblem in the DP table
                dp[startOfRange][endOfRange] = max;
            }
        }

        // Return the result for bursting all balloons
        return dp[1][nums.length];
    }
}
