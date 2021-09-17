class Piece:
    def __init__(
        self, x, y, r_side, u_side, l_side, d_side, is_white_goal, is_black_goal
    ):
        self.x = x
        self.y = y
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

    def __init__(self):
        for y in range(self.ROWS_NUM):
            row = []
            for x in range(self.COLS_NUM):
                right_side = "free"
                up_side = "free"
                left_side = "free"
                down_side = "free"
                is_white_goal = False
                is_black_goal = False
                if y == 0:
                    is_white_goal = True
                    up_side = "block"
                elif y == self.ROWS_NUM - 1:
                    is_black_goal = True
                    down_side = "block"
                if x == 0:
                    left_side = "block"
                elif x == self.COLS_NUM - 1:
                    right_side = "block"

                row.append(
                    Piece(
                        x,
                        y,
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

