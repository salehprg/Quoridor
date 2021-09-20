public class Quoridor {

    private String[][] board = new String[9][9];
    private String status = ""; /* in progress, player1_win, player2_win*/

    private Player player1 = null;
    private Player player2 = null;

    public Quoridor() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                this.board[i][j] = ".";
                if (i == 3 && j == 0) {
                    this.board[i][j] = "P1";
                }
                else if (i == 3 && j == 8) {
                    this.board[i][j] = "P2";
                }
            }
        }
        this.status= "in progress";
        this.player1 = new Player(0,3,"P1");
        this.player2 = new Player(8,3,"P2");
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }


    public void print_board(){
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                if (!board[i][j].equals("P1") && !board[i][j].equals("P2")) {
                    System.out.print(board[i][j] + "  ");
                }
                else {
                    System.out.print(board[i][j] + " ");
                }
                if (j == 8) {
                    System.out.println();
                }

            }
        }
    }
}
