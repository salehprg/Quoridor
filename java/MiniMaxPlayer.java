import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MiniMaxPlayer extends Player{

    private int MAX_DEPTH = 2;
    private int INFINITY = 9999;

    public MiniMaxPlayer(int x, int y, String name, Board board) {
        super(x, y, name, board);
    }


    @Override
    public int bfs(Player self_player, Player opponent_player){
        int self_distance = 0;
        int opponent_distance = 0;
        Set<Player> players = new HashSet<Player>();
        players.add(self_player);
        players.add(opponent_player);

        for (Player player : players) {
            LinkedList<String> queue = new LinkedList<String>();
            HashMap<String, Boolean> visited = new HashMap<>();
            HashMap<String, Integer> distances = new HashMap<>();

            for (int j = 0; j <= 8; j++) {
                for (int i = 0; i <= 8; i++) {

                    visited.put(i + "," + j, false);

                    distances.put(i + "," + j, INFINITY);
                }
            }
            int pX = player.getPlayer_x();
            int pY = player.getPlayer_y();

            visited.put(pX + "," + pY, true);

            distances.put(pX + "," + pY, 0);

            queue.add(pX + "," + pY);
            while (queue.size() != 0){
                String piece = queue.poll();
                String[] neighbors = player.get_neighbors(Integer.parseInt(piece.split(",")[0]), Integer.parseInt(piece.split(",")[1]));

                for (int i = 0; i < neighbors.length; i++) {
                    if (!visited.containsKey(neighbors[i])) {
                        distances.put(neighbors[i], distances.get(piece) + 1);
                        visited.put(neighbors[i], true);
                        queue.add(neighbors[i]);
                    }
                }

                int min_distance = INFINITY;

                for (String location : distances.keySet()) {
                    if (player.getPlayer_name().equals("P1") && location.split(",")[0].equals("8")) {
                        if (distances.get(location) < min_distance) {
                            min_distance = distances.get(location);
                        }
                    }
                    else if (player.getPlayer_name().equals("P2") && location.split(",")[0].equals("0")) {
                        if (distances.get(location) < min_distance) {
                            min_distance = distances.get(location);
                        }
                    }
                }

                if (player == self_player) {
                    self_distance = min_distance;
                }
                else opponent_distance = min_distance;
            }
        }

        return (5 * opponent_distance - self_distance) * ( 1 + this.getPlayer_wall() / 2);


    }



    @Override
    public String get_best_action(Player self_player, Player opponent_player){
        int best_action_value = - (INFINITY);
        String best_action = "";
        String[] legal_move = this.get_legal_move();
        for (int i = 0; i < legal_move.length; i++) {
            this.play(legal_move[i], true);
            if (this.is_winner()) {
                this.undo_last_action();

                return legal_move[i];
            }
            int actrion_value = bfs(self_player, opponent_player);
            if (actrion_value > best_action_value) {
                best_action_value = actrion_value;
                best_action = legal_move[i];

            }
            this.undo_last_action();
        }

        return best_action;
    }


}
