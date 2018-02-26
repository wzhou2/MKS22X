public class KnightBoard {
    private int[][] board, moveSet;
    
    public KnightBoard(int rows, int cols) {
        try {
            board = new int[rows][cols];
        } catch (NegativeArraySizeException e) {
            throw new IllegalArgumentException();
        }
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = 0;
            }
        }
        moveSet = new int[2][8];
        
        int[] x = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] y = {-2, -1, 1, 2, 2, 1, -1, -2};
        
        moveSet[0] = x;
        moveSet[1] = y;
        /*
        moveSet = { 
        {-1, -2}, 
        {1, -2}, 
        {-2, -1}, 
        {2, -1}, 
        {-2, 1}, 
        {2, 1}, 
        {-1, 2}, 
        {1, 2}
        }; 
        */
    }
    
    public boolean solve(int startingRow, int startingCol) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) {
                    throw new IllegalStateException();
                }
            }
        }
        return solve(startingRow, startingRow, 1);
    }
    
    private boolean solve(int startingRow, int startingCol, int knights) {
        if (knights == board.length * board[0].length) {
            if (board[startingRow][startingCol] == 0) {
                board[startingRow][startingCol] = knights;
            }
            return true;
        }
        for (int x = 0; x < moveSet[0].length; x++) {
            int r = startingRow + moveSet[0][x];
            int c = startingCol + moveSet[1][x];
            try {
                if (board[r][c] == 0) {
                    board[startingRow][startingCol] = knights;
                    //System.out.println(Text.go(1,1));
                    //Text.go(1,1);
                    //System.out.println(this);
                    //Text.wait(10); //adjust this delay
                    if (solve(r, c, knights+1)) {
                        return true;
                    }
                    board[startingRow][startingCol] = 0;
                }
            } catch (IndexOutOfBoundsException e) {}
        }
        return false;
    }
    
    public String toString() {
        String returnStr = "";
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] < 10) {
                    if (board[row][col] == 0) {
                        returnStr += "__";
                    } else {
                        returnStr += "_" + board[row][col];
                    }
                } else {
                    returnStr += board[row][col];
                }
                returnStr += " ";
            }
            returnStr += "\n";
        }
        return returnStr;
    }
    
    public static void main(String[] args) {
        KnightBoard kb = new KnightBoard(5, 5);
        
        System.out.println(kb);
        System.out.println(kb.solve(0,0));
        System.out.println(kb);
    }
}