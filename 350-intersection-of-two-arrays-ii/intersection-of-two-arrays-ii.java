class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0;
        int j=0;
        int n= nums1.length;
        int m = nums2.length;
        ArrayList<Integer> resultSet = new ArrayList<>();
        while(i<n && j<m){
            if(nums1[i] < nums2[j]){
                i++;
            }
            else if(nums2[j] < nums1[i]){
                j++;
            }
            else{
                resultSet.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[resultSet.size()];
        int it = 0;
        for(int num : resultSet){
            result[it++] = num;
        }
        return result;
    }
}