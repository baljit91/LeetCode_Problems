public class UniquePaths {
    static int[][] K;
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        K = new int[m+1][n+1];
        
        return uniquePathsHelper(grid, 0, 0);
    }
    
    int uniquePathsHelper(int grid[][], int row, int col){
        
        // going outside the boundary
        if(row == grid.length || col == grid[0].length){
            return 0;
        }
        
        //reaching the final destination
        if(row == grid.length-1 && col == grid[0].length -1){
            return 1;
        }
        
        int ways = 0;
        
        //explore to the right of the cell
        if(K[row + 1][col] == 0){
            K[row + 1][col] = uniquePathsHelper(grid, row + 1, col);
        }
        
        //explore to the down of the cell
        if(K[row][col + 1] == 0){
            K[row][col + 1] = uniquePathsHelper(grid, row, col + 1);
        }
        
        // adding the no. of ways returned by the down cell and right cell
        ways =  K[row + 1][col] + K[row][col + 1];
        return ways;
    }
}
