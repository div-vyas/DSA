class Solution {
    public int longestConsecutive(int[] nums) {

        // // Brute - O(N^3)
        // int n = nums.length;
        // int longestCnt = 0;

        // for(int i=0; i<n; i++){
        //     int cnt = 1;
        //     int x = nums[i];

        //     while(true){
        //         boolean found = false;
        //         for(int j=0; j<n; j++){
        //             if(nums[j] == x+1){
        //                 cnt++;
        //                 x++;
        //                 found = true;
        //                 break;
        //             }
        //         }
        //         if(!found) break;
        //     }
        //     longestCnt = Math.max(longestCnt, cnt);
        // }
        // return longestCnt;

        // // Better 

        // int n = nums.length;
        // int longestCnt = 0;
        // int lastSmaller = Integer.MIN_VALUE;
        // int cnt = 0;

        // Arrays.sort(nums);
        // for(int i=0; i<n; i++){
  
        //     if(nums[i] - 1 == lastSmaller){
        //         cnt++;
        //         lastSmaller = nums[i];
        //     }

        //     else if(nums[i] != lastSmaller){
        //         cnt = 1;
        //         lastSmaller = nums[i];
        //     }

        //     longestCnt = Math.max(longestCnt, cnt);
        // }
        // return longestCnt;


        // Optimal

        int n = nums.length;
        int longest = 0;
        Set<Integer> st = new HashSet<>();

        for(int i=0; i<n; i++){
            st.add(nums[i]);
        }

        for(int it : st){
            if(!st.contains(it - 1)){
                int cnt = 1;
                int x = it;
                while(st.contains(x + 1)){
                    cnt++;
                    x = x + 1;
                }
                longest = Math.max(cnt, longest);
            }
        }
        return longest;
    }
}