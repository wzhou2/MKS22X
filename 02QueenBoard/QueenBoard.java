public class QueenBoard {
    private int[][] board;

    public QueenBoard(int size) {
        board = new int[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                board[r][c] = 0;
            }
        }
    }
    
    public String toString() {
        String returnStr = "";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                int num = board[r][c];
                if (num == -1) {
                    returnStr += "Q ";
                } else {
                    returnStr += "_ ";
                }
            }
            returnStr += "\n";
        }
        return returnStr;
    }
    
    public boolean addQueen(int r, int c) {
        if (board[r][c] == 0) {
            queenThreat(r,c,1);
            board[r][c] = -1;
            return true;
        }
        return false;
    }
    
    public boolean removeQueen(int r, int c) {
        if (board[r][c] == -1) {
            queenThreat(r,c,-1);
            board[r][c] = 0;
            return true;
        }
        return false;
    }
    
    public void queenThreat(int r, int c, int incre) {
        for (int row = r; row < board.length; row++) {
            board[row][c] += incre;
        }
        for (int col = c; col < board[r].length; col++) {
            board[r][col] += incre;
        }
        for (int diag = 0; diag + r < board.length && diag + c < board.length; diag++) {
            board[r+diag][c+diag] += incre;
        }
        for (int diag = 0; diag + r < board.length && c - diag >= 0; diag++) {
            board[r+diag][c-diag] += incre;
        }
    }
    
    public boolean solve() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) {
                    throw new IllegalStateException();
                }
            }
        }
        return solve(0);
    }
    
    private boolean solve(int row) {
        if (row == board.length) {
            return true;
        }
        for (int col = 0; col < board[row].length; col++) {
            if (addQueen(row, col)) {
                if (solve(row+1)) {
                    return true;
                }
            }
            removeQueen(row, col);
        }
        return false;
    }
    
    public int countSolutions() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] != 0) {
                    throw new IllegalStateException();
                }
            }
        }
        return countSolutions(0, 0);
    }
    
    private int countSolutions(int row, int count) {
        int total = 0;
        if (count == board.length) {
            return 1;
        }
        if (row == board.length) {
            return 0;
        }
        for (int col = 0; col < board[row].length; col++) {
            if (addQueen(row, col)) {
                total += countSolutions(row+1, count+1);
            
            removeQueen(row, col);}
        }
        return total;
    }
    
    public static void main(String[] args) {
        QueenBoard qb = new QueenBoard(5);
        
        System.out.println(qb);
        //System.out.println(qb.solve());
        System.out.println(qb.countSolutions());
        System.out.println(qb);
        
        
    }
}