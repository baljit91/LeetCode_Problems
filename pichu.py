#! /usr/bin/python

from copy import deepcopy
import sys

"""
Pawn = Parakeet
Knight = Nighthawk
Bishop = Blue Jay
Rook = Robin
Queen = Quetzal
King = Kingfisher
Initial Board:
[['R' 'N' 'B' 'Q' 'K' 'B' 'N' 'R']
 ['P' 'P' 'P' 'P' 'P' 'P' 'P' 'P']
 ['.' '.' '.' '.' '.' '.' '.' '.']
 ['.' '.' '.' '.' '.' '.' '.' '.']
 ['.' '.' '.' '.' '.' '.' '.' '.']
 ['.' '.' '.' '.' '.' '.' '.' '.']
 ['p' 'p' 'p' 'p' 'p' 'p' 'p' 'p']
 ['r' 'n' 'b' 'q' 'k' 'b' 'n' 'r']]
 Heuristic:
	There are many factors to be considered when calculating the heuristics of a chessboard. As we developed our heuristic formula to consider more factors, the computations required to calculate the best move increased exponentially. So, we came up with a decent solution – Piece square tables.
	We started programming Pichu with Material heuristics - comparing the value of one's pieces with the opponent's pieces. It intends to capture pieces and make favourable trades. We used the standard values for each piece:
	Pawn: 1
	Bishop/Knight: 3
	Rook: 5
	Queen: 9
	Then we switched to “Number of moves” heuristic, which basically calculates the number of legal moves a player can make. It encourages the AI to develop its pieces to exert more control over the board. It prioritizes controlling the centre where pieces will have more options to influence the game. For example, a queen near the centre of the board can move in 8 directions and thus control more squares, whereas a queen on a corner square can only move in 3 directions.
	However, the initial evaluation functions was quite naive as we only considered the material that is found on the board. To improve this, we add to the evaluation a factor that takes in account the position of the pieces. For example, a knight on the center of the board is better (because it has more options and is thus more active) than a knight on the edge of the board.
	Finally, we arrived at piece-square tables heuristic. The idea is to use tables for each type of piece. For example, for pawns, we simply encourage the pawns to advance. Additionally, we try to discourage the engine from leaving central pawns unmoved. With knights we simply encourage them to go to the center. Standing on the edge is a bad idea. Standing in the corner is a terrible idea.
"""




parakeetEvalWhite =[
        [0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0],
        [0.5,  1.0, 1.0,  -2.0, -2.0,  1.0,  1.0,  0.5],
        [0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5,  0.5],
        [0.0,  0.0,  0.0,  2.0,  2.0,  0.0,  0.0,  0.0],
        [0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5],
        [1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0,  1.0],
        [5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0],
        [0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0]
    ]

parakeetEvalBlack = [
        [0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0],
        [5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0,  5.0],
        [1.0,  1.0,  2.0,  3.0,  3.0,  2.0,  1.0,  1.0],
        [0.5,  0.5,  1.0,  2.5,  2.5,  1.0,  0.5,  0.5],
        [0.0,  0.0,  0.0,  2.0,  2.0,  0.0,  0.0,  0.0],
        [0.5, -0.5, -1.0,  0.0,  0.0, -1.0, -0.5,  0.5],
        [0.5,  1.0, 1.0,  -2.0, -2.0,  1.0,  1.0,  0.5],
        [0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0]
    ]

knightEval =[
        [-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0],
        [-4.0, -2.0,  0.0,  0.5,  0.5,  0.0, -2.0, -4.0],
        [-3.0,  0.5,  1.0,  1.5,  1.5,  1.0,  0.5, -3.0],
        [-3.0,  0.0,  1.5,  2.0,  2.0,  1.5,  0.0, -3.0],
        [-3.0,  0.5,  1.5,  2.0,  2.0,  1.5,  0.5, -3.0],
        [-3.0,  0.0,  1.0,  1.5,  1.5,  1.0,  0.0, -3.0],
        [-4.0, -2.0,  0.0,  0.0,  0.0,  0.0, -2.0, -4.0],
        [-5.0, -4.0, -3.0, -3.0, -3.0, -3.0, -4.0, -5.0]
    ]


blueJayEvalWhite = [
    [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0],
    [ -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0],
    [ -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0],
    [ -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0],
    [ -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0],
    [-1.0,    0.0, 0.5,  1.0,  1.0,  0.5,  0.0, -1.0],
    [ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0],
    [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0]
]


blueJayEvalBlack = [
    [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0],
    [ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0],
    [ -1.0,  0.0,  0.5,  1.0,  1.0,  0.5,  0.0, -1.0],
    [ -1.0,  0.5,  0.5,  1.0,  1.0,  0.5,  0.5, -1.0],
    [ -1.0,  0.0,  1.0,  1.0,  1.0,  1.0,  0.0, -1.0],
    [ -1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0, -1.0],
    [ -1.0,  0.5,  0.0,  0.0,  0.0,  0.0,  0.5, -1.0],
    [ -2.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -2.0]
]

robinEvalWhite = [

    [  0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [  0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5],
    [  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0]
]

robinEvalBlack =[
    [  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0],
    [  0.5,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [ -0.5,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -0.5],
    [  0.0,   0.0, 0.0,  0.5,  0.5,  0.0,  0.0,  0.0]
]

evalQueen =[

    [ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0],
    [ -1.0,  0.0,  0.5,  0.0,  0.0,  0.0,  0.0, -1.0],
    [ -1.0,  0.5,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0],
    [  0.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5],
    [ -0.5,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -0.5],
    [ -1.0,  0.0,  0.5,  0.5,  0.5,  0.5,  0.0, -1.0],
    [ -1.0,  0.0,  0.0,  0.0,  0.0,  0.0,  0.0, -1.0],
    [ -2.0, -1.0, -1.0, -0.5, -0.5, -1.0, -1.0, -2.0]
]

kingEvalWhite = [

    [  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 ],
    [  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 ],
    [ -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0],
    [ -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0]
]


kingEvalBlack =  [

    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -3.0, -4.0, -4.0, -5.0, -5.0, -4.0, -4.0, -3.0],
    [ -2.0, -3.0, -3.0, -4.0, -4.0, -3.0, -3.0, -2.0],
    [ -1.0, -2.0, -2.0, -2.0, -2.0, -2.0, -2.0, -1.0],
    [  2.0,  2.0,  0.0,  0.0,  0.0,  0.0,  2.0,  2.0 ],
    [  2.0,  3.0,  1.0,  0.0,  0.0,  1.0,  3.0,  2.0 ]
]







def next_moves(board, x, y, player):
    current_piecies = []
    opposite_piecies = []
    if player == 'White':
        current_piecies = ['P', 'R', 'N', 'B', 'Q', 'K']
        opposite_piecies = ['p', 'r', 'n', 'b', 'q', 'k']
    else:
        current_piecies = ['p', 'r', 'n', 'b', 'q', 'k']
        opposite_piecies = ['P', 'R', 'N', 'B', 'Q', 'K']

    moves = []
    if board[x][y] == 'P':
        moves.append((x + 1, y))
        if x == 1:
            moves.append((x + 2, y))
        if x < 7 and y < 7 and board[x + 1][y + 1].islower():
            moves.append((x + 1, y + 1))
        if x < 7 and 0 < y and board[x + 1][y - 1].islower():
            moves.append((x + 1, y - 1))

    if board[x][y] == 'p':
        moves.append((x - 1, y))
        if x == 6:
            moves.append((x - 2, y))
        if x > 0 and y > 0 and board[x - 1][y - 1].isupper():
            moves.append((x - 1, y - 1))
        if x > 0 and y < 7 and board[x - 1][y + 1].isupper():
            moves.append((x - 1, y + 1))

    #for robin and Queen
    if board[x][y] == 'R' or board[x][y] == 'r' or board[x][y] == 'Q' or board[x][y] == 'q':
        dir = [[1, 0], [-1, 0], [0, 1], [0, -1]]

        for d in dir:
            values = []
            r = d[0]
            c = d[1]
            row = x
            col = y
            while (0 <= row + r < 8 and 0 <= col + c < 8 ):

                #if it touches its own side piecies break
                if (board[row + r][col + c] in current_piecies):
                    break
                moves.append((row + r,col + c))
                #if it is at postion of opponent capture that and break
                if(board[row + r][col + c] in opposite_piecies):
                    break
                row = row + r
                col = col + c


    #For BlueJays and Queen
    if board[x][y] == 'B' or board[x][y] == 'b' or board[x][y] == 'Q' or board[x][y] == 'q':
        dir = [[1, 1], [-1, -1], [1, -1], [-1, 1]]

        for d in dir:
            values = []
            r = d[0]
            c = d[1]
            row = x
            col = y
            while (0 <= row + r < 8 and 0 <= col + c < 8):

                if (board[row + r][col + c] in current_piecies):
                    break
                moves.append((row + r, col + c))
                if (board[row + r][col + c] in opposite_piecies):
                    break
                row = row + r
                col = col + c


    #For Knight
    if board[x][y] == 'N' or board[x][y] == 'n':
        values = [(x + 2, y + 1), (x + 2, y - 1),
                  (x + 1, y + 2), (x + 1, y - 2),
                  (x - 2, y - 1), (x - 2, y + 1),
                  (x - 1, y + 2), (x - 1, y - 2)]
        moves += [(i, j) for i, j in values if 0 <= i <= 7 and 0 <= j <= 7 and board[i][j] not in current_piecies]



    #For King
    if board[x][y]  == 'K' or board[x][y]  == 'k':
        values = [(x + 1, y), (x + 1, y + 1), (x, y + 1), (x - 1, y + 1),
                  (x - 1, y), (x - 1, y - 1), (x, y - 1), (x + 1, y - 1)]
        moves += [(i, j) for i, j in values if 0 <= i <= 7 and 0 <= j <= 7 and board[i][j] not in current_piecies]

    return moves



def valid_moves(board, row, col, piece, player):
    boards = []
    curr_row = row
    curr_col = col

    succ = next_moves(board, curr_row, curr_col, player)
    for x in succ:
        if (len(x) != 0):
            new_row = x[0]
            new_col = x[1]
            new_board = deepcopy(board)
            new_board[curr_row][curr_col] = '.'
            if piece == 'P' and new_row == 7:
                new_board[new_row][new_col] = 'Q'
            elif piece == 'p' and new_row == 0:
                new_board[new_row][new_col] = 'q'
            else:
                new_board[new_row][new_col] = piece
            boards.append(new_board)

    return boards



def pieceSquareEvaluation(board):
    totalEvaluation = 1.0;
    for i in range(0,8):
        for i in range(0, 8):
            if(board[i][j] != '.'):
                totalEvaluation = totalEvaluation  + getPieceValue(board[i][j], i, j)

    return totalEvaluation


def getPieceValue(piece,x,y):
    if(piece == 'P' or piece == 'p'):
        if(piece.isupper()):
            return 1 + parakeetEvalWhite[y][x]
        else:
            return 1 + parakeetEvalBlack[y][x]

    elif (piece == 'R' or piece == 'r'):
        if (piece.isupper()):
            return 5 + robinEvalWhite[y][x]
        else:
            return 5 + robinEvalBlack[y][x]

    elif (piece == 'N' or piece == 'n'):
        return 3 + knightEval[y][x]

    elif (piece == 'B' or piece == 'b'):
        if (piece.isupper()):
            return 3 + blueJayEvalWhite[y][x]
        else:
            return 3 + blueJayEvalWhite[y][x]

    elif (piece == 'Q' or piece == 'q'):
            return 9 + evalQueen[y][x]

    elif (piece == 'K' or piece == 'k'):
        if (piece.isupper()):
            return 100 + kingEvalWhite[y][x]
        else:
            return 100 + kingEvalBlack[y][x]



def evaluate(board_state=None):
    total_points = 0
    # total piece count
    clone = deepcopy(board_state)
    total_points = pieceSquareEvaluation(clone)
    return total_points






def get_successors(board, player):
    boards = []
    PIECES = []
    if player == "White":
        PIECES = ['P', 'R', 'B', 'N', 'Q', 'K']
    else:
        PIECES = ['p', 'r', 'b', 'n', 'q', 'k']

    for i in range(0,8):
        for j in range(0,8):
            if board[i][j] in PIECES:
                boards += valid_moves(board, i, j, board[i][j], player)
    return boards


def create_output_string(board):
    print("New Board:")
    resultStr = ""
    for row in range(len(board)):
        for col in range(len(board[row])):
            resultStr += board[row][col]
    return resultStr




def minimax(board, player, height, min_or_max, alpha, beta):
    #print board
    if height == max_height:
        return evaluate(board)

    succ =  get_successors(board, player)
    for successor in succ:
        if(get_successors is not None):
            if min_or_max == "max":
                if player == 'White':
                    score = minimax(successor, "Black", height + 1, "min", alpha, beta)
                    if score > alpha:
                        if(height == 0):
                            best_succ = successor
                            print(create_output_string(successor))
                        alpha = score
                else:
                    score = minimax(successor, 'White', height + 1, "min", alpha, beta)
                    if score > alpha:
                        if (height == 0):
                            best_succ = successor
                            print(create_output_string(successor))
                        alpha = score

            else:
                if player == 'Black':
                    score = minimax(successor, 'White', height + 1, "max", alpha, beta)
                    if score < beta:
                        beta = score
                else:
                    score = minimax(successor, 'Black', height + 1, "max", alpha, beta)
                    if score < beta:
                        beta = score

            if alpha >= beta:
                break

        if min_or_max == "max":
            return alpha
        else:
            return beta


player = sys.argv[1]
input_board = sys.argv[2]
input_time = sys.argv[3]

global max_height
global best_succ
max_height = 3
board = []

x = 0

for i in range(0,8):
    row = []
    for j in range(0,8):
        row.append(input_board[x])
        x = x+1
    board.append(row)


if player == "w":
    player = "White"
else:
    player = "Black"


print("Thinking! Please wait...")
opt = minimax(board, player, 0, "max", -999, 999)
