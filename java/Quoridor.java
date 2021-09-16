public class Quoridor {

    private String[][] board = new String[9][9];
    private String status = ""; /* in progress, player1_win, player2_win*/
    private int player1_loc_x;
    private int player1_loc_y;
    private int player2_loc_x;
    private int player2_loc_y;
    private int player1_wall = 10;
    private int player2_wall = 10;

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
        this.player1_loc_x = 0;
        this.player1_loc_y = 3;
        this.player2_loc_x = 8;
        this.player2_loc_y = 3;
    }

    public String[][] getBoard() {
        return board;
    }

    public int getPlayer1_loc_x() {
        return player1_loc_x;
    }

    public int getPlayer1_loc_y() {
        return player1_loc_y;
    }

    public int getPlayer2_loc_x() {
        return player2_loc_x;
    }

    public int getPlayer2_loc_y() {
        return player2_loc_y;
    }

    public String getStatus() {
        return status;
    }

    public int getPlayer1_wall() {
        return player1_wall;
    }

    public int getPlayer2_wall() {
        return player2_wall;
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
