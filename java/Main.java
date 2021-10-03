import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        Board board = new Board();

        MiniMaxPlayer player1 = new MiniMaxPlayer(0, 3, "P1", board);
        MiniMaxPlayer player2 = new MiniMaxPlayer(8, 3, "P2", board);

        String action = player1.get_best_action(player1, player2);
        player1.play(action, false);
        board.print_board(player1, player2);
        System.out.println("----");
        TimeUnit.SECONDS.sleep(3);



        action = player2.get_best_action(player2, player1);
        player2.play(action, false);
        board.print_board(player1, player2);
        System.out.println("----");
        TimeUnit.SECONDS.sleep(3);

        action = player1.get_best_action(player1, player2);
        player1.play(action, false);
        board.print_board(player1, player2);
        System.out.println("----");
        TimeUnit.SECONDS.sleep(3);


    }
}
