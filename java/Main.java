public class Main {

    public static void main(String[] args) {

        Quoridor quoridor = new Quoridor();
        String[][] board = new String[9][9];
        board = quoridor.getBoard();
        quoridor.print_board();
    }
}
