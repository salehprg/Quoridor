from utils import Board

if __name__ == "__main__":
    board = Board()

    for i in range(9):
        for j in range(9):
            print(board.get_piece(j, i).d_side, end=" ")
        print()
