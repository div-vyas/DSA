class Solution {
    public List<Integer> majorityElement(int[] nums) {

        // // Brute - O(N^2)
        // ArrayList<Integer> ls = new ArrayList<>();
        // int n = nums.length;

        // for(int i=0; i<n; i++){
        //     if(ls.size() == 0 || ls.get(0) != nums[i]){
        //         int cnt=0;
        //         for(int j=0; j<n; j++){
        //             if(nums[j] == nums[i]){
        //                 cnt++;
        //             }
        //         }
                
        //         if(cnt > (n/3)){
        //             ls.add(nums[i]);
        //         }

        //     }
        //     if(ls.size() == 2){ 
        //     break;
        // }
        // }
        // return ls;


        // Better - O(N)
        ArrayList<Integer> ls = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        int min = (int)(n/3) + 1;

        for(int i =0; i<n; i++){
            int value = map.getOrDefault(nums[i], 0);
            map.put(nums[i], value+1);

            if(map.get(nums[i]) == min){
                ls.add(nums[i]);
            }
        }
        return ls;
    }
}