from typing import Dict
from Player import Player
from copy import deepcopy


class MiniMaxPlayer(Player):
    MAX_NODE_EXPAN = 20
    MAX_DEPTH = 2
    INFINITY = 9999

    bestAction = None

    transpositionTable = {}

    forwardPruningTable = {}

    def getHashTable(self , depth):
        hashString = ""

        for y in range(self.board.ROWS_NUM):
            for x in range(self.board.COLS_NUM):
                piece = self.board.get_piece(x , y)
                
                if piece.state == "empty":
                    hashString += "e"

                elif piece.state == "white":
                    hashString += "white"
                else:
                    hashString += "black"

                if piece.r_side == "block" and x != self.board.COLS_NUM - 1:
                    hashString += "vw"

                elif piece.d_side == "block" and not y >= self.board.ROWS_NUM - 1:
                    hashString += "hw"

        hashString += str(depth)
                        
        return hashString

    def evaluateMINMAX(self, opponent , isMax):
        self_distance, opponent_distance = self.UpdateBoardScore(opponent)

        total_score = opponent_distance - self_distance

        return(
            total_score 
            if isMax
            else -total_score
            )
        
    def MiniMax(self , opponent : Player , depth , alpha , beta , MaxPlayer):
        if depth == 0:
            score = self.evaluateMINMAX(opponent , MaxPlayer)
       
            return score
        
        self.forwardPruningTable[depth] = 0

        if MaxPlayer:
            maxValue = -self.INFINITY
                
            actionsMax = self.get_legal_actions(opponent)
            

            for action in actionsMax:

                self.play(action, is_evaluating = True)

                _hashString = self.getHashTable(depth)

                newValue = -self.INFINITY
            
                canExpand = True

                if self.transpositionTable.get(_hashString) != None :
                    newValue = self.transpositionTable.get(_hashString)
                    #print("Key exist : " , action , " Depth " , depth)

                else :
                
                    self.forwardPruningTable[depth] += 1

                    if(self.forwardPruningTable[depth] > self.MAX_NODE_EXPAN):
                        canExpand = False

                    if(canExpand):
                        newValue = self.MiniMax(opponent , depth - 1 , alpha , beta , False)
                        self.transpositionTable[_hashString] = newValue

                self.undo_last_action()

                if(not canExpand):
                    break

                if(newValue > maxValue):
                    self.bestAction = action
                    #print(self.bestAction , " Value  " , newValue)

                maxValue = max(newValue , maxValue)

                if(maxValue >= beta):
                    break

                alpha = max(alpha , maxValue)

                # else : 
                #     self.undo_last_action()
            
            return maxValue

        else:
            minValue = self.INFINITY

            actionsMin = opponent.get_legal_actions(self)

            for action in actionsMin:

                opponent.play(action, is_evaluating = True)

                newValue = self.INFINITY

                _hashString = self.getHashTable(depth)

                canExpand = True

                if self.transpositionTable.get(_hashString) != None :
                    newValue = self.transpositionTable.get(_hashString)
                    #print("Key exist : " , action , " Depth " , depth)

                else :
                    self.forwardPruningTable[depth] += 1

                    if(self.forwardPruningTable[depth] > self.MAX_NODE_EXPAN):
                        canExpand = False

                    if(canExpand):
                        newValue = self.MiniMax(opponent , depth - 1 , alpha , beta , True )
                        self.transpositionTable[_hashString] = newValue

                opponent.undo_last_action()

                if(not canExpand):
                    break

                minValue = min(minValue , newValue)

                if minValue <= alpha:
                    break

                beta = min(beta , minValue) 

            return minValue

    def get_best_actionP_MinMax(self, opponent):

        action_value = self.MiniMax(opponent , self.MAX_DEPTH , -self.INFINITY , self.INFINITY , True)
        print(action_value)
        print("Best Action " , self.bestAction)

        return self.bestAction

    def UpdateBoardScore(self , opponent : Player):
        return self.bfs(opponent)


    def bfs(self, opponent: Player):
        for player in [self, opponent]:
            destination = (
                self.board.get_white_goal_pieces()

                if player.color == "white"
                else self.board.get_black_goal_pieces()
            )

            visited = {}
            distances = {}

            for row in self.board.map:
                for piece in row:
                    visited[piece] = False
                    distances[piece] = self.INFINITY

            player_piece = self.board.get_piece(*player.get_position())

            queue = []
            queue.append(player_piece)
            visited[player_piece] = True
            distances[player_piece] = 0

            while queue:
                piece = queue.pop(0)

                for i in self.board.get_piece_neighbors(piece):
                    if visited[i] == False:
                        distances[i] = distances[piece] + 1
                        visited[i] = True
                        queue.append(i)

            min_distance = self.INFINITY

            for piece, dist in distances.items():
                if piece in destination:
                    if dist < min_distance:
                        min_distance = dist

            if player == self:
                self_distance = min_distance
            else:
                opponent_distance = min_distance

        return self_distance, opponent_distance

    def evaluate(self, opponent):
        self_distance, opponent_distance = self.bfs(opponent)

        total_score = (5 * opponent_distance - self_distance) * (
            1 + self.walls_count / 2
        )

        return total_score

    def get_best_action(self, opponent):
        best_action_value = -self.INFINITY
        best_action = None

        for action in self.get_legal_actions(opponent):
            self.play(action, is_evaluating = True)

            if self.is_winner():
                self.undo_last_action()
                return action

            action_value = self.evaluate(opponent)

            if action_value > best_action_value:
                best_action_value = action_value
                best_action = action

            self.undo_last_action()

        return best_action

