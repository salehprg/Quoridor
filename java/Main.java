public class Main {

    public static void main(String[] args) {

        Quoridor quoridor = new Quoridor();
        String[][] board = new String[9][9];
        board = quoridor.getBoard();
//        quoridor.print_board();
        Player player1 = null;
        Player player2 = null;

        player1 = quoridor.getPlayer1();
        player2 = quoridor.getPlayer2();

        String[][] map = player1.move(board, "move#1#2");
        quoridor.setBoard(map);
        quoridor.print_board();

        System.out.println("----------");
        
        map = player1.put_wall(map, "wall#2#2#vertical");
        quoridor.setBoard(map);
        quoridor.print_board();

        String[] legal_move = player1.get_legal_move(map);
        for (String m:legal_move) {
            System.out.println(m);
        }
    }
}
