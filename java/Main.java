public class Main {

    public static void main(String[] args) {

        Quoridor quoridor = new Quoridor();
        String[][] board = new String[9][9];
        board = quoridor.getBoard();
        Player player1 = null;
        Player player2 = null;

        player1 = quoridor.getPlayer1();
        player2 = quoridor.getPlayer2();


        String[][] map = player1.put_wall(board, "wall#2#2#vertical");
        quoridor.setBoard(map);
        quoridor.print_board(player1.getPlayer_x(), player1.getPlayer_y(), player2.getPlayer_x(), player2.getPlayer_y());

        System.out.println("----------");

        map = player1.move(map, "move#3#2");
        quoridor.setBoard(map);
        quoridor.print_board(player1.getPlayer_x(), player1.getPlayer_y(), player2.getPlayer_x(), player2.getPlayer_y());

        String[] legal_move = player1.get_legal_move(map);
        for (String m:legal_move) {
            System.out.println(m);
        }
    }
}
