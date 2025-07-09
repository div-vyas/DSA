class Solution {
    public int majorityElement(int[] nums) {
        // // Brute (TC => O(N^2))

        // int n = nums.length;

        // for(int i=0; i<n; i++){
        //     int count = 0;
        //     for(int j=0; j<n; j++){
        //         if(nums[j] == nums[i]){
        //             count++;
        //         }
        //     }
        //     if(count > (n/2)){
        //         return nums[i];
        //     }
        // }
        // return -1;
        
        // // Better(TC => O(N) + O(N Log N), SC => O(N))

        // int n = nums.length;
        // HashMap<Integer, Integer> map = new HashMap<>();
        // // storing in the Hashmap
        // for(int i=0; i<n; i++){
        //     int value = map.getOrDefault(nums[i], 0);
        //     map.put(nums[i], value + 1);
        // }

        // // Retriving the data
        // for(Map.Entry<Integer, Integer> it : map.entrySet()){
        //     if(it.getValue() > (n/2)){
        //         return it.getKey();
        //     }
        // }
        // return -1;

        // Optimal (Moore's Voting Algorithm)

        int n = nums.length;
        int cnt = 0;
        int el=0;

        for(int i=0; i<n; i++){
            if(cnt == 0){
                cnt=1;
                el = nums[i];
            }
            else if(el == nums[i]){
                cnt++;
            }
            else{
                cnt--;
            }
        }

        int finalCnt = 0;
        for(int i=0; i<n; i++){
            if(nums[i] == el){
                finalCnt++;
            }
        }

        if(finalCnt > (n/2)){
            return el;
        }

        return -1;
    }
}