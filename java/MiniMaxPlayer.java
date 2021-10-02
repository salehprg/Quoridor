import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashMap;

//public class MiniMaxPlayer extends Player {
public class MiniMaxPlayer extends Player {


    private int MAX_DEPTH = 2;
    private int INFINITY = 9999;

    public MiniMaxPlayer(int x, int y, String name) {
        super(x, y, name);
    }

    @Override
    public int bfs(String[][] map, Player self_player, Player opponent_player){
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
//                    visited.put(map[j][i], false);
                    visited.put(i + "," + j, false);
//                    distances.put(map[j][i], INFINITY);
                    distances.put(i + "," + j, INFINITY);
                }
            }
            int pX = player.getPlayer_x();
            int pY = player.getPlayer_y();
//            visited.put(map[pY][pX], true);
            visited.put(pX + "," + pY, true);
//            distances.put(map[pY][pX], 0);
            distances.put(pX + "," + pY, 0);
//            System.out.println(distances);
            queue.add(pX + "," + pY);
            while (queue.size() != 0){
                String piece = queue.poll();
                String[] neighbors = player.get_neighbors(map, Integer.parseInt(piece.split(",")[0]), Integer.parseInt(piece.split(",")[1]));

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

//        Set<Integer> distance = new HashSet<Integer>();
//        distance.add(self_distance);
//        distance.add(opponent_distance);
        System.out.println(opponent_distance + ", " + self_distance + " , " + this.getPlayer_wall() + " , " + this.getPlayer_name());
        return (5 * opponent_distance - self_distance) * ( 1 + this.getPlayer_wall() / 2);


//        LinkedList<String> queue = new LinkedList<String>();
//        HashMap<String, Boolean> visited = new HashMap<>();
//        HashMap<String, Integer> distances = new HashMap<>();
//
//        for (int j = 0; j <= 8; j++) {
//            for (int i = 0; i <= 8; i++) {
//                visited.put(map[j][i], false);
//                distances.put(map[j][i], INFINITY);
//            }
//        }
//        int pX = opponent_player.getPlayer_x();
//        int pY = opponent_player.getPlayer_y();
//        visited.put(map[pY][pX], true);
//        distances.put(map[pY][pX], 0);
//
//        queue.add(pX + "," + pY);
//        while (queue.size() != 0){
//            String piece = queue.poll();
//            String[] neighbors = opponent_player.get_neighbors(map, Integer.parseInt(piece.split(",")[0]), Integer.parseInt(piece.split(",")[1]));
//
//            for (int i = 0; i < neighbors.length; i++) {
//                if (!visited.get(neighbors[i])) {
//                    distances.put(neighbors[i], distances.get(piece) + 1);
//                    visited.put(neighbors[i], true);
//                    queue.add(neighbors[i]);
//                }
//            }
//
//            int min_distance = INFINITY;
//
//            for (String location : distances.keySet()) {
//                if (opponent_player.getPlayer_name().equals("P1") && location.split(",")[0].equals("8")) {
//                    if (distances.get(location) < min_distance) {
//                        min_distance = distances.get(location);
//                    }
//                }
//                else if (opponent_player.getPlayer_name().equals("P2") && location.split(",")[0].equals("0")) {
//                    if (distances.get(location) < min_distance) {
//                        min_distance = distances.get(location);
//                    }
//                }
//            }
//        }
    }
    @Override
    public String get_best_action(String[][] map, Player self_player, Player opponent_player){
        int best_action_value = - (INFINITY);
        String best_action = "";
        String[] legal_move = this.get_legal_move(map);
        for (int i = 0; i < legal_move.length; i++) {
            String[][] temp_map = this.play(map, legal_move[i], true);
            if (this.is_winner()) {
                temp_map = this.undo_last_action(temp_map);
                System.out.println(legal_move[i]);
                return legal_move[i];
            }
            int actrion_value = bfs(temp_map, self_player, opponent_player);
//            System.out.println(actrion_value + ", " + best_action_value);
            if (actrion_value > best_action_value) {
                best_action_value = actrion_value;
                System.out.println(legal_move[i]);
                best_action = legal_move[i];
            }

            temp_map = this.undo_last_action(temp_map);

        }
        return best_action;
    }

}
