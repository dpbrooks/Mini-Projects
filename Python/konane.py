from konane import *

class MinimaxPlayer(Konane, Player):
    def __init__(self, size, depthLimit):
        Konane.__init__(self, size)
        self.limit = depthLimit

    def initialize(self, side):
        self.side = side
        self.name = "DylanKonane"

    # Acts as the root of the minimax tree
    def getMove(self, board):
        moves = self.generateMoves(board, self.side)
        n = len(moves)
        if n == 0:
            return []
        else:
            # Initialize alpha and beta
            alphaVal = -float("inf")
            betaVal = float("inf")
            # Create a variable to store the utility value of the current move,
            # and a variable to store the current index and the index of the best move
            util = alphaVal
            bestmove = 0
            index = 0
            for move in moves:
                # Create a board to test moves
                testBoard = self.nextBoard(board, self.side, move)
                # Test the utility of the move and store
                # Final param will track whether to maximize or minimize (start with minimizing)
                util = max(util, self.minimax(testBoard, 1, alphaVal, betaVal, False))
                # If util is greater than the current max update max, 
                # bestMove and alphaVal
                if (util > alphaVal):
                    # Update alphaVal to be passed in the next brach of moves
                    alphaVal = util
                    bestmove = index
                index += 1
            # Return the best possible move
            return moves[bestmove]

    # Eval based on dif between # of player moves available and # of opponent moves
    # plus dif between # of player's moveable pieces and # of opponent's moveable pieces
    # times 2 
    def eval(self, board):
        # Check how many pieces player and opponent can move in the given state
        selfmoveable = self.moveable(board, self.side)
        oppmoveable = self.moveable(board, self.opponent(self.side))
               
        # Calculate the number of possible moves from the current board
        selfmoves = len(self.generateMoves(board, self.side))
        # Calcultae the number of possible opponent moves
        opponentmoves = len(self.generateMoves(board, self.opponent(self.side)))
        # If move would make opponent have no moves, force it
        if (opponentmoves == 0):
            return float("inf")
        else:
            # Return the sum of the dif between player moves and opponent moves and
            # the dif between player moveable pieces and opponent moveable pieces
            # Add extra weight to reducing opponent's moveable pieces b/c
            # of win condition being when opponent cannot make any moves
            """
            When oppmoveable was not weighted MinimaxDepth1 won when it
            went second
            """
            return (selfmoves - opponentmoves) + (selfmoveable - (2 * oppmoveable))
    
    def minimax(self, board, depth, alphaVal, betaVal, isMax):
        if(depth >=  self.limit):
            # At depth limit eval the current board
            return self.eval(board)
        if (isMax):
            # Maximizing Function (Generate player boards)
            testBoards = self.generateBoards(board, self.side)
        else:
            # Minimizing Function (Generate opponent boards)
            testBoards = self.generateBoards(board, self.opponent(self.side))
        n = len(testBoards)
        # No moves
        if n == 0:
            if (isMax):
                return -float("inf")
            else:
                return float("inf")
        # depth limit not reached and moves still possible
        else:
            # Create an array to store all values created in current set of nodes
            # being tested
            values = []
            # Initialize alpha and beta for this node
            curAlpha = alphaVal
            curBeta = betaVal
            # Check every board that results from every move in current board
            for nextBoard in testBoards:
                # if values has any values stored evaluate
                if (len(values) > 0):
                    if isMax:
                        # Maximizing Function
                        if (max(values) >= betaVal):
                            # Prune when the largest value of the current
                            # branch is greater than betaVal
                            break
                        if (max(values) > alphaVal):
                            # Update curAlpha
                            curAlpha = max(values)
                    else:
                        # Minimizing Function
                        if (min(values) <= alphaVal):
                            # Prune when the smallest value of the current
                            # branch is less than alphaVal
                            break
                        if (min(values) < betaVal):
                            # Update curBeta
                            curBeta = min(values)
                # Recursive call minimax and append result to values
                values.append(self.minimax(nextBoard, depth + 1, curAlpha, curBeta, not(isMax)))
            if isMax:
                return max(values)
            else:
                return min(values)

    # Function to find the number of moveable pieces a player has
    def moveable(self, board, player):
        moves = self.generateMoves(board, player)
        # Use i to 'move' through moves
        i = 0
        # Keep an array of pieces that can move in order to return
        # the total number at the end
        pieces = []
        for move in moves:
            # Check if the current row and column combo is already in pieces
            # Need to do this because moves will have multiple entries for the
            # same piece if it can move multiple ways
            if (moves[i][0], moves[i][1]) not in pieces:
                # If not add it
                pieces.append((moves[i][0], moves[i][1]))
            # Increment i to check the next move
            i += 1
        # Return the number of moveable pieces
        return len(pieces)

    # Create the boards that result from every possible move
    # Given the current state
    def generateBoards(self, board, player):
        moves = self.generateMoves(board, player)
        # Create an array to store every board created
        boards = []
        for move in moves:
            # Generate the board that results from player performing
            # current move and append to the array of boards
            boards.append(self.nextBoard(board, player, move))
        # Return the array of boards
        return boards