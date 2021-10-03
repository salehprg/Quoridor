public class Board {
    private String[][] board = new String[9][9];

    public Board() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                this.board[i][j] = "free";
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public void setPiece(int x, int y , String state){
        this.board[y][x] = state;
    }

    public String getPiece(int x, int y){
        return this.board[y][x];
    }

    //◼ block, . free

    public void print_board(Player player1, Player player2){
        int p1X = player1.getPlayer_x();
        int p1Y = player1.getPlayer_y();
        int p2X = player2.getPlayer_x();
        int p2Y = player2.getPlayer_y();
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (j == p1X && i == p1Y) {
                    System.out.print("P1" + " ");
                }
                else if (j == p2X && i == p2Y){
                    System.out.print("P2" + " ");
                }
                else if (board[i][j].equals("free")) {
                    System.out.print("." + "  ");
                }
                else if(board[i][j].contains("block")){
                    System.out.print("◼" + "  ");
                }
                if (j == 8) {
                    System.out.println();
                }


            }
        }
    }
}
