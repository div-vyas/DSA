class Solution {
    public int maxSubArray(int[] nums) {
        // // Brute O(N^3)
        // int n = nums.length;
        // int maxi  = Integer.MIN_VALUE;

        // for(int i=0; i<n; i++){
        //     for(int j=i; j<n; j++){
        //         int sum = 0;
        //         for(int k=i; k<=j; k++){
        //             sum += nums[k];
        //         }
        //         maxi = Math.max(sum, maxi);
        //     }
        // }
        // return maxi;

        // // Better O(N^2)
        // int n = nums.length;
        // int maxi = Integer.MIN_VALUE;

        // for(int i=0; i<n; i++){
        //     int sum = 0;
        //     for(int j=i; j<n; j++){
        //         sum += nums[j];
        //         maxi = Math.max(sum, maxi);
        //     }
            
        // }
        // return maxi;

        // Optimal - Kadence Algorithm

        int maxi = Integer.MIN_VALUE;
        int n = nums.length;
        int sum = 0;

        for(int i=0; i<n; i++){
            if(sum < 0){
                sum = 0;
            }
            sum += nums[i];
            maxi = Math.max(sum, maxi);
        }
        return maxi;
    }
}