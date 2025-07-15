class Solution {
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> ans = new ArrayList<>();
        long result = 1;
        for(int i=0; i<=rowIndex; i++){
            ans.add((int)result);
            result = result * (rowIndex - i);
            result = result / (i+1);
        }
        return ans;
    }
}