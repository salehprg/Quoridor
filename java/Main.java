public class Main {

    public static void main(String[] args) {

        Quoridor quoridor = new Quoridor();
        String[][] board = new String[9][9];
        board = quoridor.getBoard();
        Player player1 = null;
//        MiniMaxPlayer player1 = null;
        Player player2 = null;
//        MiniMaxPlayer player2 = null;

        player1 = quoridor.getPlayer1();
        player2 = quoridor.getPlayer2();

        // test for put wall and is reachable or not
        String[][] map = player1.put_wall(board, "wall#2#2#vertical");
        map = player1.put_wall(map, "wall#2#0#vertical");
        map = player1.put_wall(map, "wall#2#4#vertical");
        map = player1.put_wall(map, "wall#2#6#vertical");
        map = player1.put_wall(map, "wall#6#7#vertical");
        map = player1.put_wall(map, "wall#4#6#horizontal");

        String[] temp = player1.get_neighbors(board ,player1.getPlayer_x(), player1.getPlayer_y());
//        for (String t:temp) {
//            System.out.println(t);
//        }
        System.out.println(player1.is_reachable(board));
        System.out.println("-*-*-*-");

//        String[][] map = player1.put_wall(board, "wall#2#2#vertical");
//        map = player1.put_wall(map, "wall#2#0#vertical");
//        map = player1.put_wall(map, "wall#2#4#vertical");
//        map = player1.put_wall(map, "wall#2#6#vertical");
//        map = player1.put_wall(map, "wall#6#7#vertical");
//        map = player1.put_wall(map, "wall#4#6#horizontal");

        quoridor.setBoard(map);
        quoridor.print_board(player1.getPlayer_x(), player1.getPlayer_y(), player2.getPlayer_x(), player2.getPlayer_y());

        System.out.println("----------");

        map = player1.move(map, "move#3#2");
        quoridor.setBoard(map);
        quoridor.print_board(player1.getPlayer_x(), player1.getPlayer_y(), player2.getPlayer_x(), player2.getPlayer_y());

        String[] legal_move = player1.get_legal_move(map);
//        for (String m:legal_move) {
//            System.out.println(m);
//        }

        MiniMaxPlayer miniMaxPlayer1 = new MiniMaxPlayer(player1.getPlayer_x(), player1.getPlayer_y(), player1.getPlayer_name());
        MiniMaxPlayer miniMaxPlayer2 = new MiniMaxPlayer(player2.getPlayer_x(), player2.getPlayer_y(), player2.getPlayer_name());
        System.out.println(miniMaxPlayer1.get_best_action(map, miniMaxPlayer1, miniMaxPlayer2));
        System.out.println(player1.getPlayer_x() + "," +  player1.getPlayer_y() + "," + player1.getPlayer_name());
        System.out.println(player2.getPlayer_x() + "," +  player2.getPlayer_y() + "," + player2.getPlayer_name());

    }
}
