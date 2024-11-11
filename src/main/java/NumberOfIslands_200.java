public class NumberOfIslands_200 {
    public int numIslands(char[][] grid) {

        int count = 0;

        for(int i=0;i<grid.length; i++){
            for(int j=0;j<grid[i].length; j++){
                if(grid[i][j] == '1'){
                    count += 1;
                    doBFS(grid, i,j);
                }
            }
        }
        return count;
    }

    public void doBFS(char[][] grid, int i, int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[i].length || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        doBFS(grid, i+1, j); //down
        doBFS(grid,i-1,j); //up
        doBFS(grid,i,j+1); //right
        doBFS(grid,i,j-1); //left
    }
}
