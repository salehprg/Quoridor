from MiniMaxPlayer import MiniMaxPlayer
from MiniMaxPlayer import MiniMaxPlayer
from Board import Board
from time import sleep
from random import choice

from datetime import datetime
from datetime import timedelta

if __name__ == "__main__":
    board = Board()

    white_player = MiniMaxPlayer("white", board.COLS_NUM // 2 , board.ROWS_NUM - 1 , board)
    black_player = MiniMaxPlayer("black", board.COLS_NUM // 2 , 0 , board)

    walls_count = 0

    startTime = datetime.now()

    while True:
        # action = white_player.get_best_action(opponent =  black_player)
        action = white_player.get_best_actionP_MinMax(opponent =  black_player)
        
        white_player.play(action)
        board.print_map()
        print(
            f"white: {action}, evaluation: {white_player.evaluateMINMAX(opponent=black_player , isMax= True):.2f}, left walls: {white_player.walls_count}"
        )   

        if white_player.is_winner():
            print(f"White player just won with {white_player.moves_count} moves!")
            break
        if action.split("#")[0] == "wall":
            walls_count += 1

        #sleep(0.3)

        #action = black_player.get_best_action(opponent=white_player)
        action = black_player.get_best_actionP_MinMax(opponent=white_player)

        black_player.play(action)

        board.print_map()

        print(
            f"black: {action}, evaluation: {black_player.evaluateMINMAX(opponent=white_player , isMax= False):.2f}, left walls: {black_player.walls_count}"
        )

        if black_player.is_winner():
            print(f"Black player just won with {black_player.moves_count} moves!")
            break

        if action.split("#")[0] == "wall":
            walls_count += 1

        #sleep(0.3)

    difference = (datetime.now() - startTime)

    print("Elapsed time : " , difference.total_seconds() , " s")
    print(f"walls count {walls_count}")

