//applying dfs here 

public class Solution {
    static int[][] K;
    public int UniquePathsWithObstacles(int[][] obstacleGrid) {
        //K = new int[obstacleGrid.length + 1][obstacleGrid[0].length + 1];
        
        return uniquePathsWithObstaclesHelper(obstacleGrid,0,0);
    }
    
    int uniquePathsWithObstaclesHelper(int grid[][], int row, int col){
    
    //out of the grid
    if(row == grid.length || col == grid[0].length){
        return 0;
    }
    
    //when there is a obstacle
    if(grid[row][col] == 1){
        return 0;
    }
    
    //reaching destination
    if(row == grid.length -1 && col == grid[0].length - 1){
        return 1;
    }
    
    int ways = 0;
    
    ways = uniquePathsWithObstaclesHelper(grid,row + 1,col) + uniquePathsWithObstaclesHelper(grid,row,col + 1);
    
    return ways;
    
    }
}
