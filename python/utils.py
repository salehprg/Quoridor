from Player import Player


class Piece:
    def __init__(
        self, x, y, state, r_side, u_side, l_side, d_side, is_white_goal, is_black_goal
    ):
        self.x = x
        self.y = y
        self.state = state
        self.r_side = r_side
        self.u_side = u_side
        self.l_side = l_side
        self.d_side = d_side
        self.is_white_goal = is_white_goal
        self.is_black_goal = is_black_goal

    def get_position(self):
        return self.x, self.y


class Board:
    ROWS_NUM = 9
    COLS_NUM = 9
    map = []
    origin_pieces_walls = []

    def __init__(self):
        for y in range(self.ROWS_NUM):
            row = []
            for x in range(self.COLS_NUM):
                state = "empty"
                right_side = "free"
                up_side = "free"
                left_side = "free"
                down_side = "free"
                is_white_goal = False
                is_black_goal = False
                if y == 0:
                    is_white_goal = True
                    up_side = "block"
                    if x == 4:
                        state = "black"
                elif y == self.ROWS_NUM - 1:
                    is_black_goal = True
                    down_side = "block"
                    if x == 4:
                        state = "white"
                if x == 0:
                    left_side = "block"
                elif x == self.COLS_NUM - 1:
                    right_side = "block"

                row.append(
                    Piece(
                        x,
                        y,
                        state,
                        right_side,
                        up_side,
                        left_side,
                        down_side,
                        is_white_goal,
                        is_black_goal,
                    )
                )
            self.map.append(row)

    def get_piece(self, x, y):
        return self.map[y][x]

    def get_white_goal_pieces(self):
        return self.map[0]

    def get_black_goal_pieces(self):
        return self.map[self.ROWS_NUM - 1]

    def get_piece_neighbors(self, piece: Piece):
        neighbors = []
        x, y = piece.get_position()

        if x + 1 < self.COLS_NUM and piece.r_side != "block":
            neighbors.append(self.get_piece(x + 1, y))
        if y + 1 < self.ROWS_NUM and piece.d_side != "block":
            neighbors.append(self.get_piece(x, y + 1))
        if x - 1 >= 0 and piece.l_side != "block":
            neighbors.append(self.get_piece(x - 1, y))
        if y - 1 >= 0 and piece.u_side != "block":
            neighbors.append(self.get_piece(x, y - 1))

        return neighbors

    def is_reachable(self, white_player_piece: Player, black_player_piece: Player):
        for player in [white_player_piece, black_player_piece]:
            destination = (
                self.get_white_goal_pieces()
                if player.color == "black"
                else self.get_black_goal_pieces()
            )
            visited = [False] * (self.ROWS_NUM * self.COLS_NUM)
            queue = []

            queue.append(player)
            visited[player] = True
            can_be_reached = False
            while queue:
                piece = queue.pop(0)

                if piece in destination:
                    can_be_reached = True
                    break

                for i in self.get_piece_neighbors(piece):
                    if visited[i] == False:
                        queue.append(i)
                        visited[i] = True

            if not can_be_reached:
                return False

        return True

