### File: konane.py
### Classes defined: KonaneError, Konane, Player, SimplePlayer,
### RandomPlayer, HumanPlayer

import random
import copy
import abc

class KonaneError(AttributeError):
    """
    This class is used to indicate a problem in the konane game.
    """

class Konane:
    """
    This class implements Konane, the Hawaiian version of checkers.
    The board is represented as a two-dimensional list.  Each
    location on the board contains one of the following symbols:
       'B' for a black piece
       'W' for a white piece
       '.' for an empty location
    The black player always goes first.  The opening moves by both
    players are special cases and involve removing one piece from
    specific designated locations.  Subsequently, each move is a
    jump over one of the opponent's pieces into an empty location.
    The jump may continue in the same direction, if appropriate.
    The jumped pieces are removed, and then it is the opponent's
    turn.  Play continues until one player has no possible moves,
    making the other player the winner.
    """
    def __init__(self, n):
        self.size = n
        self.reset()

    def reset(self):
        """
        Resets the starting board state.
        """
        self.board = []
        value = 'B'
        for i in range(self.size):
            row = []
            for j in range(self.size):
                row.append(value)
                value = self.opponent(value)
            self.board.append(row)
            if self.size%2 == 0:
                value = self.opponent(value)

    def __str__(self):
        return self.boardToStr(self.board)

    def boardToStr(self, board):
        """
        Returns a string representation of the konane board.
        """
        result = "  "
        for i in range(self.size):
            result += str(i) + " "
        result += "\n"
        for i in range(self.size):
            result += str(i) + " "
            for j in range(self.size):
                result += str(board[i][j]) + " "
            result += "\n"
        return result

    def valid(self, row, col):
        """
        Returns true if the given row and col represent a valid location on
        the konane board.
        """
        return row >= 0 and col >= 0 and row < self.size and col < self.size

    def contains(self, board, row, col, symbol):
        """
        Returns true if the given row and col represent a valid location on
        the konane board and that lcoation contains the given symbol.
        """
        return self.valid(row,col) and board[row][col]==symbol

    def countSymbol(self, board, symbol):
        """
        Returns the number of instances of the symbol on the board.
        """
        count = 0
        for r in range(self.size):
            for c in range(self.size):
                if board[r][c] == symbol:
                    count += 1
        return count

    def opponent(self, player):
        """
        Given a player symbol, returns the opponent's symbol, 'B' for black,
        or 'W' for white.
        """
        if player == 'B':
            return 'W'
        else:
            return 'B'

    def distance(self, r1, c1, r2, c2):
        """
        Returns the distance between two points in a vertical or
        horizontal line on the konane board.
        """
        return abs(r1-r2 + c1-c2)

    def makeMove(self, player, move):
        """
        Updates the current board with the next board created by the given
        move.
        """
        self.board = self.nextBoard(self.board, player, move)

    def nextBoard(self, board, player, move):
        """
        Given a move for a particular player from (r1,c1) to (r2,c2) this
        executes the move on a copy of the current konane board.  It will
        raise a KonaneError if the move is invalid. It returns the copy of
        the board, and does not change the given board.
        """
        if len(move) != 4:
            raise KonaneError
        r1 = int (move[0])
        c1 = int (move[1])
        r2 = int (move[2])
        c2 = int (move[3])
        next = copy.deepcopy(board)
        if not (self.valid(r1, c1) and self.valid(r2, c2)):
            raise KonaneError
        if next[r1][c1] != player:
            raise KonaneError
        dist = self.distance(r1, c1, r2, c2)
        if dist == 0:
            if self.openingMove(board):
                next[r1][c1] = "."
                return next
            raise KonaneError
        if next[r2][c2] != ".":
            raise KonaneError
        jumps = int(dist//2)
        dr = int((r2 - r1)/dist)
        dc = int((c2 - c1)/dist)
        for i in range(jumps):
            if next[r1+dr][c1+dc] != self.opponent(player):
                raise KonaneError
            next[r1][c1] = "."
            next[r1+dr][c1+dc] = "."
            r1 += 2*dr
            c1 += 2*dc
            next[r1][c1] = player
        return next

    def openingMove(self, board):
        """
        Based on the number of blanks present on the konane board, determines
        whether the current move is the first or second of the game.
        """
        return self.countSymbol(board, ".") <= 1

    def generateFirstMoves(self, board):
        """
        Returns the special cases for the first move of the game.
        """
        moves = []
        moves.append([0]*4)
        moves.append([self.size-1]*4)
        moves.append([self.size//2]*4)
        moves.append([(self.size//2)-1]*4)
        return moves

    def generateSecondMoves(self, board):
        """
        Returns the special cases for the second move of the game, based
        on where the first move occurred.
        """
        moves = []
        if board[0][0] == ".":
            moves.append([0,1]*2)
            moves.append([1,0]*2)
            return moves
        elif board[self.size-1][self.size-1] == ".":
            moves.append([self.size-1,self.size-2]*2)
            moves.append([self.size-2,self.size-1]*2)
            return moves
        elif board[self.size//2-1][self.size//2-1] == ".":
            pos = self.size//2 -1
        else:
            pos = self.size//2
        moves.append([pos,pos-1]*2)
        moves.append([pos+1,pos]*2)
        moves.append([pos,pos+1]*2)
        moves.append([pos-1,pos]*2)
        return moves

    def check(self, board, r, c, rd, cd, factor, opponent):
        """
        Checks whether a jump is possible starting at (r,c) and going in the
        direction determined by the row delta, rd, and the column delta, cd.
        The factor is used to recursively check for multiple jumps in the same
        direction.  Returns all possible jumps in the given direction.
        """
        if self.contains(board,r+factor*rd,c+factor*cd,opponent) and \
           self.contains(board,r+(factor+1)*rd,c+(factor+1)*cd,'.'):
            return [[r,c,r+(factor+1)*rd,c+(factor+1)*cd]] + \
                   self.check(board,r,c,rd,cd,factor+2,opponent)
        else:
            return []

    def generateMoves(self, board, player):
        """
        Generates and returns all legal moves for the given player using the
        current board configuration.
        """
        if self.openingMove(board):
            if player=='B':
                return self.generateFirstMoves(board)
            else:
                return self.generateSecondMoves(board)
        else:
            moves = []
            rd = [-1,0,1,0]
            cd = [0,1,0,-1]
            for r in range(self.size):
                for c in range(self.size):
                    if board[r][c] == player:
                        for i in range(len(rd)):
                            moves += self.check(board,r,c,rd[i],cd[i],1,
                                                self.opponent(player))
            return moves

    def playOneGame(self, p1, p2, show):
        """
        Given two instances of players, will play out a game
        between them.  Returns 'B' if black wins, or 'W' if
        white wins. When show is true, it will display each move
        in the game.
        """
        self.reset()
        p1.initialize('B')
        p2.initialize('W')
        print (p1.name, "vs", p2.name)
        while 1:
            if show:
                print (self)
                print ("player B's turn")
            try:
                move = p1.getMove(self.board)
            except Exception as e:
                print ("player B is forfeiting because of error:", str(e))
                move = []
            if move == []:
                result = 'W'
                break
            try:
                self.makeMove('B', move)
            except KonaneError:
                print ("ERROR: invalid move by", p1.name)
                result = 'W'
                break
            if show:
                print (move)
                print
                print (self)
                print ("player W's turn")
            try:
                move = p2.getMove(self.board)
            except Exception as e:
                print ("player W is forfeiting because of error:", str(e))
                move = []
            if move == []:
                result = 'B'
                break
            try:
                self.makeMove('W', move)
            except KonaneError:
                print ("ERROR: invalid move by", p2.name)
                result = 'B'
                break
            if show:
                print (move)
                print
        if show:
            print ("Game over")
        return result

    def playNGames(self, n, p1, p2, show):
        """
        Will play out n games between player p1 and player p2.
        The players alternate going first.  Prints the total
        number of games won by each player.
        """
        first = p1
        second = p2
        for i in range(n):
            print ("Game", i)
            winner = self.playOneGame(first, second, show)
            if winner == 'B':
                first.won()
                second.lost()
                print (first.name, "wins")
            else:
                first.lost()
                second.won()
                print (second.name, "wins")
            temp = first
            first = second
            second = temp


class Player(metaclass = abc.ABCMeta):
    """
    A base class for Konane players.  All players must implement
    the the initialize and getMove methods.
    """
    name = "Player"
    wins = 0
    losses = 0
    def results(self):
        result = self.name
        result += " Wins:" + str(self.wins)
        result += " Losses:" + str(self.losses)
        result += " Score: " + str(self.score())
        return result
    def score(self):
        return self.wins - self.losses
    def lost(self):
        self.losses += 1
    def won(self):
        self.wins += 1
    def reset(self):
        self.wins = 0
        self.losses = 0

    @abc.abstractmethod
    def initialize(self, side):
        """
        Records the player's side, either 'B' for black or
        'W' for white.  Should also set the name of the player.
        """
        return

    @abc.abstractmethod
    def getMove(self, board):
        """
        Given the current board, should return a valid move.
        """
        return


class HumanPlayer(Player):
    """
    Prompts a human player for a move.
    """
    def initialize(self, side):
        self.side = side
        self.name = "Human"
    def getMove(self, board):
        inputs = list(map( int, input("Enter r1 c1 r2 c2 (or -1's to concede): ").split()))
        if inputs[1] == -1:
            return []
        return inputs

class RandomPlayer(Konane, Player):
    """
    Chooses a random move from the set of possible moves.
    """
    def initialize(self, side):
        self.side = side
        self.name = "RandomPlayer"
    def getMove(self, board):
        moves = self.generateMoves(board, self.side)
        n = len(moves)
        if n == 0:
            return []
        else:
            return moves[random.randrange(0, n)]



class SimplePlayer(Konane, Player):
    """
    Always chooses the first move from the set of possible moves.
    """
    def initialize(self, side):
        self.side = side
        self.name = "SimplePlayer"
    def getMove(self, board):
        moves = self.generateMoves(board, self.side)
        n = len(moves)
        if n == 0:
            return []
        else:
            return moves[0]


#game = Konane(8)
#game.playNGames(1, SimplePlayer(8), RandomPlayer(8), 1)



## Begin My Code ##

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
    
print("Welcome to Konane! Please enter the number for how player 1 plays\n"
"1. Random\n"
"2. Simple\n"
"3. Human\n"
"4. Minimax")
player1 = input()
if player1 == "4":
    print("What depth should minimax be carried to?")
    depth1 = int(input())
print("Please enter the number for how player 2 plays\n"
"1. Random\n"
"2. Simple\n"
"3. Human\n"
"4. Minimax")
player2 = input()
if player2 == "4":
    print("What depth should minimax be carried to?")
    depth2 = int(input())

game = Konane(8)
match(player1, player2):
    case("1", "1"):
        game.playNGames(1, RandomPlayer(8), RandomPlayer(8), 1)
    case("1", "2"):
        game.playNGames(1, RandomPlayer(8), SimplePlayer(8), 1)
    case("1", "3"):
        game.playNGames(1, RandomPlayer(8), HumanPlayer(), 1)
    case("1", "4"):
        game.playNGames(1, RandomPlayer(8), MinimaxPlayer(8, depth2), 1)
    case("2", "1"):
        game.playNGames(1, SimplePlayer(8), RandomPlayer(8), 1)
    case("2", "2"):
        game.playNGames(1, SimplePlayer(8), SimplePlayer(8), 1)
    case("2", "3"):
        game.playNGames(1, SimplePlayer(8), HumanPlayer(), 1)
    case("2", "4"):
        game.playNGames(1, SimplePlayer(8), MinimaxPlayer(8, depth2), 1)
    case("3", "1"):
        game.playNGames(1, HumanPlayer(), RandomPlayer(8), 1)
    case("3", "2"):
        game.playNGames(1, HumanPlayer(), SimplePlayer(8), 1)
    case("3", "3"):
        game.playNGames(1, HumanPlayer(), HumanPlayer(), 1)
    case("3", "4"):
        game.playNGames(1, HumanPlayer(), MinimaxPlayer(8, depth2), 1)
    case("4", "1"):
        game.playNGames(1, MinimaxPlayer(8, depth1), RandomPlayer(8), 1)
    case("4", "2"):
        game.playNGames(1, MinimaxPlayer(8, depth1), SimplePlayer(8), 1)
    case("4", "3"):
        game.playNGames(1, MinimaxPlayer(8, depth1), HumanPlayer(), 1)
    case("4", "4"):
        game.playNGames(1, MinimaxPlayer(8, depth1), MinimaxPlayer(8, depth2), 1)
    