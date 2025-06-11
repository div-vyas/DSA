class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int topRow=0, bottomRow=n-1, leftCol=0, rightCol=n-1;
        int current = 1;
        while(current <= n*n){
            for(int i=leftCol; i<= rightCol && current<= n*n; i++){
                matrix[topRow][i] = current++;
            }
            topRow++;
            for(int i=topRow; i<= bottomRow && current<= n*n; i++){
                matrix[i][rightCol] = current++;
            }
            rightCol--;
            for(int i=rightCol; i>= leftCol && current<= n*n; i--){
                matrix[bottomRow][i] = current++;
            }
            bottomRow--;
            for(int i=bottomRow; i>= topRow && current<= n*n; i--){
                matrix[i][leftCol] = current++;
            }
            leftCol++;
        }
        return matrix;
    }
}