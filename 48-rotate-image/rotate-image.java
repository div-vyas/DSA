class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // transpose
        transpose(matrix, n);
        // reverseArray of tanspose
        for(int i=0; i<n; i++){
            reverseArray(matrix[i]);
        }
    }
    public void transpose(int[][] matrix, int n){
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void reverseArray(int[] arr){
        int i=0, j=arr.length-1;
        while(i<j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        } 
    }
}