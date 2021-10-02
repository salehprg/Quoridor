import java.util.*;

public class Player {

    private int player_x;
    private int player_y;
    private String player_name;
    private int player_wall = 10;
    private LinkedList<String> action_logs = new LinkedList<String>();
    int movie_count = 0;
    public Player(int x, int y, String name){
        this.player_x = x;
        this.player_y = y;
        this.player_name = name;
    }



    public int getPlayer_wall() {
        return player_wall;
    }

    public int getPlayer_x() {
        return player_x;
    }

    public int getPlayer_y() {
        return player_y;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_x(int player_x) {
        this.player_x = player_x;
    }

    public void setPlayer_y(int player_y) {
        this.player_y = player_y;
    }

    public String[][] move(String[][] map, String command){
        String[] split_cmd = command.split("#");
        map[this.player_y][this.player_x] = "free";
        setPlayer_x(Integer.parseInt(split_cmd[1]));
        setPlayer_y(Integer.parseInt(split_cmd[2]));
        return map;
    }

    public String[][] put_wall(String[][] map, String command){
        String[] split_cmd = command.split("#");
        String x = split_cmd[1];
        String y = split_cmd[2];
        String orientation = split_cmd[3];

        this.player_wall -=1 ;

        if (orientation.equals("horizontal")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "block,"+x+"horizontal";
            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "block,"+x+"horizontal"+y;

            map[Integer.parseInt(y)+ 1][Integer.parseInt(x)] = "block,"+x+"horizontal";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "block,"+x+"horizontal"+y;
        }
        else if (orientation.equals("vertical")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "block,"+x+"vertical"+y;
            map[Integer.parseInt(y) + 1][Integer.parseInt(x)] = "block,"+x+"vertical";

            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "block,"+x+"vertical"+y;
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "block,"+x+"vertical";
        }

        return map;
    }


    public String[][] remove_wall(String[][] map, String command){
        String[] split_cmd = command.split("#");
        String x = split_cmd[1];
        String y = split_cmd[2];
        String orientation = split_cmd[3];

        this.player_wall +=1 ;

        if (orientation.equals("horizontal")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "free";
            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "free";

            map[Integer.parseInt(y)+ 1][Integer.parseInt(x)] = "free";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "free";
        }
        else if (orientation.equals("vertical")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "free";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x)] = "free";

            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "free";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "free";
        }
        return map;
    }

    public String[] get_legal_move(String[][] map){
        int pX = this.player_x;
        int pY = this.player_y;
        System.out.println(pX + " , " + pY);
        Set<String> legal = new HashSet<String>();
        if ((pX+1) <= 8) {
            if ((map[pY][pX + 1].equals("free")) || (map[pY][pX].equals("free")) && map[pY][pX + 1].contains("block")){
                legal.add("move#"+(pX+1) +"#"+pY);
            }
        }
        if((pX + 2) <= 8){
            if (!map[pY][pX + 1].equals("free") && !map[pY][pX + 1].contains("block")) {
                legal.add("move#"+(pX+2) +"#"+pY);
            }
        }
        if ((pX - 1) >= 0){
            if ((map[pY][pX - 1].equals("free")) || (map[pY][pX].equals("free")) && map[pY][pX - 1].contains("block")) {
                legal.add("move#"+(pX-1) +"#"+pY);
            }
        }
        if ((pX - 2) >= 0){
            if (!map[pY][pX - 1].equals("free") && !map[pY][pX - 1].contains("block")) {
                legal.add("move#"+(pX-2) +"#"+pY);
            }
        }
        if ((pY + 1) <= 8){
            if ((map[pY + 1][pX].equals("free")) || (map[pY][pX].equals("free")) && map[pY + 1][pX].contains("block")) {
                legal.add("move#"+pX +"#"+(pY+1));
            }

        }
        if ((pY + 2) <= 8){
            if (!map[pY + 1][pX].equals("free") && !map[pY + 1][pX].contains("block")) {
                legal.add("move#"+pX +"#"+(pY+2));
            }
        }
        if ((pY - 1) >= 0){
            if ((map[pY - 1][pX].equals("free")) || (map[pY][pX].equals("free")) && map[pY - 1][pX].contains("block")) {
                legal.add("move#"+pX +"#"+(pY-1));
            }
        }

        if ((pY - 2) >= 0) {
            if (!map[pY - 1][pX].equals("free") && !map[pY - 1][pX].contains("block")){
                legal.add("move#"+pX +"#"+(pY-2));
            }

        }

        if (map[pY][pX].contains("block") && !map[pY][pX+1].equals(map[pY][pX]) && ((pX + 1) <= 8)) {
            legal.add("move#"+(pX+1) +"#"+(pY));
        }

        if (map[pY][pX].contains("block") && !map[pY][pX-1].equals(map[pY][pX]) && ((pX - 1) >= 0)) {
            legal.add("move#"+(pX-1) +"#"+(pY));
        }

        if (map[pY][pX].contains("block") && !map[pY+1][pX].equals(map[pY][pX]) && ((pY + 1) <= 8)) {
            legal.add("move#"+(pX) +"#"+(pY+1));
        }

        if (map[pY][pX].contains("block") && !map[pY-1][pX].equals(map[pY][pX]) && ((pX - 1) >= 0)) {
            legal.add("move#"+(pX) +"#"+(pY-1));

        }

        Set<String> how = new HashSet<String>();
        how.add("vertical");
        how.add("horizontal");
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                for (String s : how) {
                    String command = "wall#" + i + "#" + j + "#" + s;
//                    System.out.println(command);
                    map = put_wall(map, command);
                    if (is_reachable(map)) {
                        legal.add(command);
//                        System.out.println("reach");
                    }
                    map = remove_wall(map, command);
                }
            }
        }


        int index = 0;
        String[] temp = new String[legal.size()];
        for (String l: legal) {
            temp[index++] = l;
        }
        return temp;
    }


    public String[] get_neighbors(String[][] map, int x, int y){

//        int pX = this.player_x;
        int pX = x;
//        int pY = this.player_y;
        int pY = y;
        Set<String> neighbors = new HashSet<String>();

        if (pX + 1 <= 8 && !map[pY][pX + 1].contains("block")) {
            neighbors.add((pX + 1) + "," + pY);
        }
        if (pY + 1 <= 8 && !map[pY + 1][pX].contains("block")) {
            neighbors.add(pX + "," + (pY + 1));
        }
        if (pX - 1 >= 0 && !map[pY][pX - 1].contains("block")) {
            neighbors.add((pX - 1) + "," + pY);
        }
        if (pY - 1 >= 0 && !map[pY - 1][pX].contains("block")) {
            neighbors.add(pX + "," + (pY - 1));
        }

        int index = 0;
        String[] temp = new String[neighbors.size()];
        for (String l: neighbors) {
            temp[index++] = l;
        }
        return temp;

    }


    public boolean is_winner(){
        if(this.getPlayer_name().equals("P1")){
            return this.player_x == 8;
        }
        else {
            return this.player_x == 0;
        }

    }


    public boolean is_reachable(String[][] map){
        int pX = this.player_x;
        int pY = this.player_y;

        boolean winner = false;
        LinkedList<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();

        queue.add(pX + "," + pY);
        visited.add(pX + "," + pY);
        boolean reachable = false;
        while (queue.size() != 0){
            String location = queue.poll();
            if (this.player_name.equals("P1") && location.split(",")[0].equals("8")) {
//                System.out.println(location);
                reachable = true;
                break;
            }
            else if (this.player_name.equals("P2") && location.split(",")[0].equals("0")){
                reachable = true;
                break;
            }

//            String[] neighbors = get_neighbors(map, pX, pY);
            String[] neighbors = get_neighbors(map, Integer.parseInt(location.split(",")[0]), Integer.parseInt(location.split(",")[1]));

            for (int i = 0; i < neighbors.length; i++) {
                if (!visited.contains(neighbors[i])){
                    queue.add(neighbors[i]);
                    visited.add(neighbors[i]);
                }
            }
        }
        return reachable;

    }


    public String[][] play(String[][] map, String command, boolean is_evaluating){
        if (is_evaluating) {
            movie_count += 1;
        }
        String[] split_cmd = command.split("#");

        if (split_cmd[0].equals("move")) {
            action_logs.add("move#" + this.getPlayer_x() + "#" + this.getPlayer_y() + "#" + Integer.parseInt(split_cmd[1]) + "#" + Integer.parseInt(split_cmd[2]));
            return move(map, command);
        }
        else {
            action_logs.add(command);
            return put_wall(map, command);
        }
    }

    public int bfs(String[][] map, Player self_player, Player opponent_player) {
        return 0;
    }
    public String get_best_action(String[][] map, Player self_player, Player opponent_player){
        return null;
    }


    public String[][] undo_last_action(String[][] map){
        String temp = action_logs.poll();
        String[] split_cmd = temp.split("#");

        if (split_cmd[0].equals("wall")) {
            return remove_wall(map, temp);
        }
        else {
            int x = Integer.parseInt(split_cmd[1]);
            int y = Integer.parseInt(split_cmd[1]);
            return move(map, "move#" + x + "#" + y);
        }
    }




}
