public class Player {

    private int player_x;
    private int player_y;
    private String player_name;
    private int player_wall = 10;

    public Player(int x, int y, String name){
        this.player_x = x;
        this.player_y = y;
        this.player_name = name;
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
        map[this.player_y][this.player_x] = ".";
        map[Integer.parseInt(split_cmd[2])][Integer.parseInt(split_cmd[1])] = this.player_name;
        setPlayer_x(Integer.parseInt(split_cmd[1]));
        setPlayer_y(Integer.parseInt(split_cmd[2]));
        return map;
    }

    public String[][] put_wall(String[][] map, String command){
        String[] split_cmd = command.split("#");
        String x = split_cmd[1];
        String y = split_cmd[2];
        String orientation = split_cmd[3];

        if (orientation.equals("horizontal")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "◼";
            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "◼";

            map[Integer.parseInt(y)+ 1][Integer.parseInt(x)] = "◼";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "◼";
        }
        else if (orientation.equals("vertical")) {
            map[Integer.parseInt(y)][Integer.parseInt(x)] = "◼";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x)] = "◼";

            map[Integer.parseInt(y)][Integer.parseInt(x) + 1] = "◼";
            map[Integer.parseInt(y) + 1][Integer.parseInt(x) + 1] = "◼";
        }

        return map;
    }

    public String[] get_legal_move(String[][] map){
        int pX = this.player_x;
        int pY = this.player_y;
        System.out.println(pX + " , " + pY);
        String[] legal_move = new String[8];
        int i = 0;
        if ((pY+1) <= 8) {
            if ( (map[pX][pY + 1].equals(".")) || (map[pX][pY].equals(".")) && map[pX][pY + 1].equals("◼")){
                legal_move[i] = "move#"+pX +"#"+(pY+1);
                i++;
            }
        }
        if((pY + 2) <= 8){
            if (!map[pX][pY + 1].equals(".") && !map[pX][pY + 1].equals("◼")) {
                legal_move[i] = "move#"+pX +"#"+(pY+2);
                i++;
            }
        }
        if ((pY - 1) >= 0){
            if ((map[pX][pY - 1].equals(".")) || (map[pX][pY].equals(".")) && map[pX][pY - 1].equals("◼")) {
                legal_move[i] = "move#"+pX +"#"+(pY-1);
                i++;
            }
        }
        if ((pY - 2) >= 0){
            if (!map[pX][pY - 1].equals(".") && !map[pX][pY - 1].equals("◼")) {
                legal_move[i] = "move#"+pX +"#"+(pY-2);
                i++;
            }
        }
        if ((pX + 1) <= 8){
            if ((map[pX + 1][pY].equals(".")) || (map[pX][pY].equals(".")) && map[pX + 1][pY].equals("◼")) {
                legal_move[i] = "move#"+(pX+1) +"#"+(pY);
                i++;
            }

        }
        if ((pX + 2) <= 8){
            if (!map[pX + 1][pY].equals(".") && !map[pX + 1][pY].equals("◼")) {
                legal_move[i] = "move#"+(pX+2) +"#"+(pY);
                i++;
            }
        }
        if ((pX - 1) >= 0){
            if ((map[pX - 1][pY].equals(".")) || (map[pX][pY].equals(".")) && map[pX - 1][pY].equals("◼")) {
                legal_move[i] = "move#"+(pX-1) +"#"+(pY);
                i++;
            }
        }

        if ((pX - 2) >= 0) {
            if (!map[pX - 1][pY].equals(".") && !map[pX - 1][pY].equals("◼")){
                legal_move[i] = "move#"+(pX-2) +"#"+(pY);
                i++;
            }

        }

        String[] temp = new String[i];
        for (int j = 0; j < legal_move.length; j++) {
            if (legal_move[j] != null) {
                temp[j] = legal_move[j];
            }
        }

        return temp;
    }

}
