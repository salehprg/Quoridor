public class Quoridor {

    private String[][] board = new String[9][9];
    private String status = ""; /* in progress, player1_win, player2_win*/

    private Player player1 = null;
    private Player player2 = null;
//    private MiniMaxPlayer player1 = null;
//    private MiniMaxPlayer player2 = null;

    public Quoridor() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                this.board[i][j] = "free";
            }
        }
        this.status= "in progress";
        this.player1 = new Player(0,3,"P1");
//        this.player1 = new MiniMaxPlayer(0,3,"P1");
//        this.player1 = new MiniMaxPlayer(8,3,"P2");
        this.player2 = new Player(8,3,"P2");
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public Player getPlayer1() {
//        return player1;
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    //◼ block, . free
    public void print_board(int p1X, int p1Y, int p2X, int p2Y){
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
