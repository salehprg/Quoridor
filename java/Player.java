import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashMap;


public class Player {

    private int player_x;
    private int player_y;
    private String player_name;
    private int player_wall = 10;
    private Queue<String> action_logs = new LinkedList<String>();
    int movie_count = 0;
    protected Board board = null;
    public Player(int x, int y, String name, Board board){
        this.player_x = x;
        this.player_y = y;
        this.player_name = name;
        this.board = board;
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

    public void move(String command){
        String[] split_cmd = command.split("#");
        this.board.setPiece(this.player_x, this.player_y, "free");
        this.setPlayer_x(Integer.parseInt(split_cmd[1]));
        this.setPlayer_y(Integer.parseInt(split_cmd[2]));
    }


    public void put_wall(String command){
        String[] split_cmd = command.split("#");
        String x = split_cmd[1];
        String y = split_cmd[2];
        String orientation = split_cmd[3];

        this.player_wall -=1 ;

        if (orientation.equals("horizontal")) {
            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y), "block,"+x+",horizontal");
            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y), "block,"+x+",horizontal,"+y);

            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y) + 1, "block,"+x+",horizontal");
            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y) + 1, "block,"+x+",horizontal,"+y);
        }
        else if (orientation.equals("vertical")) {
            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y), "block,"+x+",vertical,"+y);
            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y) + 1, "block,"+x+"vertical");

            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y), "block,"+x+",vertical,"+y);
            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y) + 1, "block,"+x+",vertical");
        }
    }


    public void remove_wall(String command){
        String[] split_cmd = command.split("#");
        String x = split_cmd[1];
        String y = split_cmd[2];
        String orientation = split_cmd[3];

        this.player_wall +=1 ;

        if (orientation.equals("horizontal")) {
            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y), "free");
            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y), "free");

            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y) + 1, "free");
            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y) + 1, "free");
        }
        else if (orientation.equals("vertical")) {
            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y), "free");
            this.board.setPiece(Integer.parseInt(x), Integer.parseInt(y) + 1, "free");

            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y), "free");
            this.board.setPiece(Integer.parseInt(x) + 1, Integer.parseInt(y) + 1, "free");
        }

    }



    public String[] get_legal_move(){
        int pX = this.player_x;
        int pY = this.player_y;

        Set<String> legal = new HashSet<String>();
        if ((pX+1) <= 8) {
            if ((this.board.getPiece((pY),(pX + 1)).equals("free")) || (this.board.getPiece(pY, pX).equals("free")) && this.board.getPiece((pY), (pX + 1)).contains("block")){
                legal.add("move#"+(pX+1) +"#"+pY);
            }
        }
        if((pX + 2) <= 8){
            if (!this.board.getPiece((pY),(pX + 1)).equals("free") && !this.board.getPiece((pY),(pX + 1)).contains("block")) {
                legal.add("move#"+(pX+2) +"#"+pY);
            }
        }
        if ((pX - 1) >= 0){
            if ((this.board.getPiece((pY), (pX - 1)).equals("free")) || (this.board.getPiece(pY, pX).equals("free")) && this.board.getPiece((pY), (pX - 1)).contains("block")) {
                legal.add("move#"+(pX-1) +"#"+pY);
            }
        }
        if ((pX - 2) >= 0){
            if (!this.board.getPiece((pY), (pX - 1)).equals("free") && !this.board.getPiece((pY), (pX - 1)).contains("block")) {
                legal.add("move#"+(pX-2) +"#"+pY);
            }
        }
        if ((pY + 1) <= 8){
            if ((this.board.getPiece((pY + 1), (pX)).equals("free")) || (this.board.getPiece(pY, pX).equals("free")) && this.board.getPiece((pY + 1), (pX)).contains("block")) {
                legal.add("move#"+pX +"#"+(pY+1));
            }

        }
        if ((pY + 2) <= 8){
            if (!this.board.getPiece((pY + 1), (pX)).equals("free") && !this.board.getPiece((pY + 1), (pX)).contains("block")) {
                legal.add("move#"+pX +"#"+(pY+2));
            }
        }
        if ((pY - 1) >= 0){
            if ((this.board.getPiece((pY - 1), (pX)).equals("free")) || (this.board.getPiece((pY), (pX)).equals("free")) && this.board.getPiece((pY - 1), (pX)).contains("block")) {
                legal.add("move#"+pX +"#"+(pY-1));
            }
        }

        if ((pY - 2) >= 0) {
            if (!this.board.getPiece((pY - 1), (pX)).equals("free") && !this.board.getPiece((pY - 1), (pX)).contains("block")){
                legal.add("move#"+pX +"#"+(pY-2));
            }

        }

        if (this.board.getPiece((pY), (pX)).contains("block") && !this.board.getPiece((pY), (pX + 1)).equals(this.board.getPiece((pY), (pX))) && ((pX + 1) <= 8)) {
            legal.add("move#"+(pX+1) +"#"+(pY));
        }

        if (this.board.getPiece((pY), (pX)).contains("block") && !this.board.getPiece((pY), (pX - 1)).equals(this.board.getPiece((pY), (pX))) && ((pX - 1) >= 0)) {
            legal.add("move#"+(pX-1) +"#"+(pY));
        }

        if (this.board.getPiece((pY), (pX)).contains("block") && !this.board.getPiece((pY + 1), (pX)).equals(this.board.getPiece((pY), (pX))) && ((pY + 1) <= 8)) {
            legal.add("move#"+(pX) +"#"+(pY+1));
        }

        if (this.board.getPiece((pY), (pX)).contains("block") && !this.board.getPiece((pY - 1), (pX)).equals(this.board.getPiece((pY), (pX))) && ((pX - 1) >= 0)) {
            legal.add("move#"+(pX) +"#"+(pY-1));
        }

        Set<String> how = new HashSet<String>();
        how.add("vertical");
        how.add("horizontal");
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                for (String s : how) {
                    String command = "wall#" + i + "#" + j + "#" + s;
                    if (!this.board.getPiece((j), (i)).contains("block")) {
                        put_wall(command);
                        if (is_reachable()) {
                            legal.add(command);
                        }
                        remove_wall(command);
                    }
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


    public String[] get_neighbors(int x, int y){


        int pX = x;

        int pY = y;
        Set<String> neighbors = new HashSet<String>();

        if (pX + 1 <= 8 && !this.board.getPiece((pY), (pX + 1)).contains("block")) {
            neighbors.add((pX + 1) + "," + pY);
        }
        if (pY + 1 <= 8 && !this.board.getPiece((pY + 1), (pX)).contains("block")) {
            neighbors.add(pX + "," + (pY + 1));
        }
        if (pX - 1 >= 0 && !this.board.getPiece((pY), (pX - 1)).contains("block")) {
            neighbors.add((pX - 1) + "," + pY);
        }
        if (pY - 1 >= 0 && !this.board.getPiece((pY - 1), (pX)).contains("block")) {
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

    public boolean is_reachable(){
        int pX = this.player_x;
        int pY = this.player_y;

        LinkedList<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();

        queue.add(pX + "," + pY);
        visited.add(pX + "," + pY);
        boolean reachable = false;
        while (queue.size() != 0){
            String location = queue.poll();
            if (this.player_name.equals("P1") && location.split(",")[0].equals("8")) {

                reachable = true;
                break;
            }
            else if (this.player_name.equals("P2") && location.split(",")[0].equals("0")){
                reachable = true;
                break;
            }


            String[] neighbors = get_neighbors(Integer.parseInt(location.split(",")[0]), Integer.parseInt(location.split(",")[1]));

            for (int i = 0; i < neighbors.length; i++) {
                if (!visited.contains(neighbors[i])){
                    queue.add(neighbors[i]);
                    visited.add(neighbors[i]);
                }
            }
        }
        return reachable;

    }


    public void play(String command, boolean is_evaluating){

        if (is_evaluating) {
            movie_count += 1;
        }
        String[] split_cmd = command.split("#");

        if (split_cmd[0].equals("move")) {
            action_logs.add("move#" + this.getPlayer_x() + "#" + this.getPlayer_y() + "#" + Integer.parseInt(split_cmd[1]) + "#" + Integer.parseInt(split_cmd[2]));
            move(command);
        }
        else {
            action_logs.add(command);
            put_wall(command);
        }
    }


    public int bfs(Player self_player, Player opponent_player) {
        return 0;
    }
    public String get_best_action(Player self_player, Player opponent_player){
        return null;
    }


    public void undo_last_action(){
        String temp = ((LinkedList<String>) action_logs).removeLast();
        if (temp == null) {
            return;
        }
        else {
            String[] split_cmd = temp.split("#");
            if (split_cmd[0].equals("wall")) {

               remove_wall(temp);
            }
            else {
                int x = Integer.parseInt(split_cmd[1]);
                int y = Integer.parseInt(split_cmd[2]);

                move("move#" + x + "#" + y);
            }
        }
    }




}
