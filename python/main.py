from Player import Player
from utils import Board
from time import sleep
from random import choice

if __name__ == "__main__":
    board = Board()

    white_player = Player("white", 4, 8)
    black_player = Player("black", 4, 0)

    while True:
        action = choice(white_player.get_legal_moves(black_player, board))
        white_player.take_action(action, board)
        board.print_map()
        print("white: " + action)
        input()
        action = choice(black_player.get_legal_moves(white_player, board))
        black_player.take_action(action, board)
        board.print_map()
        print("black: " + action)
        input()
