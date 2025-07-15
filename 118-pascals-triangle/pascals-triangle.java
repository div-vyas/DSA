class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for(int row = 1; row<=numRows; row++){
            List<Integer> tempList = new ArrayList<>();

            for(int col=1; col<=row; col++){
                tempList.add(nCR(row-1, col-1));
            }
            ans.add(tempList);
        }
        return ans;
    }

    private static int nCR(int n, int r){
        long res = 1;

        for(int i=0; i<r; i++){
            res = res * (n-i);
            res = res / (i+1);
        }
        return (int) res;
    }
}