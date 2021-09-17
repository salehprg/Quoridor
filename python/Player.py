from python.utils import Board


class Player:
    def __init__(self, color, x, y):
        self.color = color
        self.x = x
        self.y = y

    def get_position(self):
        return self.x, self.y

    def move(self, command: str, board: Board):
        """
        the format of command is like:
            move#X#Y
        """

        board.get_piece(self.x, self.y).status = "free"

        splitted_command = command.split("#")
        x = splitted_command[1]
        y = splitted_command[2]

        self.x = x
        self.y = y

        board.get_piece(self.x, self.y).status = self.color

    def put_wall(str, command: str, board: Board):
        """
        The wall will be put right/down side 
        of the piece with X and Y position and 
        its neighbor

        the format of command is like:
            wall#X#Y#ORIENTATION
        """
        splitted_command = command.split("#")
        x = splitted_command[1]
        y = splitted_command[2]
        orientation = splitted_command[3]

        piece = board.get_piece(x, y)
        if orientation == "horizontal":
            neighbor_piece1 = board.get_piece(x + 1, y)
            neighbor_piece2 = board.get_piece(x, y + 1)
            neighbor_piece3 = board.get_piece(x + 1, y + 1)
            piece.d_side = "block"
            neighbor_piece1.d_side = "block"
            neighbor_piece2.u_side = "block"
            neighbor_piece3.u_side = "block"
        elif orientation == "vertical":
            neighbor_piece1 = board.get_piece(x, y + 1)
            neighbor_piece2 = board.get_piece(x + 1, y)
            neighbor_piece3 = board.get_piece(x + 1, y + 1)
            piece.r_side = "block"
            neighbor_piece1.r_side = "block"
            neighbor_piece2.l_side = "block"
            neighbor_piece3.l_side = "block"

    def is_winner(self, piece):
        if self.color == "white":
            if piece.is_white_goal:
                return True

        if self.color == "black":
            if piece.is_black_goal:
                return True

        return False

    def get_legal_moves(self, opponent, board: Board):
        player_piece = board.get_piece(self.x, self.y)
        opponent_piece = board.get_piece(opponent.x, opponent.y)

        legal_moves = []

        # move right
        if player_piece.r_side != "block":
            if opponent_piece.get_position() != (self.x + 1, self.y):
                legal_moves.append(f"move#{self.x+1}#{self.y}")
            else:  # Opponent is present
                if opponent_piece.r_side == "free":
                    legal_moves.append(f"move#{self.x+2}#{self.y}")
                else:
                    if opponent_piece.u_side == "free":
                        legal_moves.append(f"move#{self.x+1}#{self.y-1}")
                    if opponent_piece.d_side == "free":
                        legal_moves.append(f"move#{self.x+1}#{self.y+1}")

        # move down
        if player_piece.d_side != "block":
            if opponent_piece.get_position() != (self.x, self.y + 1):
                legal_moves.append(f"move#{self.x}#{self.y+1}")
            else:  # Opponent is present
                if opponent_piece.d_side == "free":
                    legal_moves.append(f"move#{self.x}#{self.y+2}")
                else:
                    if opponent_piece.r_side == "free":
                        legal_moves.append(f"move#{self.x+1}#{self.y+1}")
                    if opponent_piece.l_side == "free":
                        legal_moves.append(f"move#{self.x-1}#{self.y+1}")

        # move left
        if player_piece.l_side != "block":
            if opponent_piece.get_position() != (self.x - 1, self.y):
                legal_moves.append(f"move#{self.x-1}#{self.y}")
            else:  # Opponent is present
                if opponent_piece.l_side == "free":
                    legal_moves.append(f"move#{self.x-2}#{self.y}")
                else:
                    if opponent_piece.u_side == "free":
                        legal_moves.append(f"move#{self.x-1}#{self.y-1}")
                    if opponent_piece.d_side == "free":
                        legal_moves.append(f"move#{self.x-1}#{self.y+1}")

        # move up
        if player_piece.u_side != "block":
            if opponent_piece.get_position() != (self.x, self.y - 1):
                legal_moves.append(f"move#{self.x}#{self.y-1}")
            else:  # Opponent is present
                if opponent_piece.u_side == "free":
                    legal_moves.append(f"move#{self.x}#{self.y-2}")
                else:
                    if opponent_piece.l_side == "free":
                        legal_moves.append(f"move#{self.x-1}#{self.y-1}")
                    if opponent_piece.r_side == "free":
                        legal_moves.append(f"move#{self.x+1}#{self.y-1}")

        # PUT WALL

