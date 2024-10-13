public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (checkFigure(board[0][0], board[0][4])
                    && checkSameColor(board[0][0], board[0][4])
                    && checkEmptyBetween(0, 1, 3)
                    && board[0][0].check && board[0][4].check
                    && !new King(nowPlayer).isUnderAttack(this, 0, 4)) {
                swop(nowPlayer, 0, 0, 4, 2, 3);
                nextPlayer();
                return true;
            }
            return false;
        } else {
            if (nowPlayer.equals("Black") && checkFigure(board[7][0], board[7][4])
                    && checkSameColor(board[7][0], board[7][4])
                    && checkEmptyBetween(7, 1, 3)
                    && board[7][0].check && board[7][4].check
                    && !new King(nowPlayer).isUnderAttack(this, 7, 4)) {
                swop(nowPlayer, 7, 0, 4, 2, 3);
                nextPlayer();
                return true;
            }
            return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (checkFigure(board[0][7], board[0][4])
                    && checkSameColor(board[0][4], board[0][7])
                    && checkEmptyBetween(0, 5, 6)
                    && board[0][4].check && board[0][7].check
                    && !new King(nowPlayer).isUnderAttack(this, 0, 4)) {
                swop(nowPlayer, 0, 4, 7, 5, 6);
                nextPlayer();
                return true;
            }
            return false;
        } else {
            if (nowPlayer.equals("Black") && checkFigure(board[7][7], board[7][4])
                    && checkSameColor(board[7][7], board[7][4])
                    && checkEmptyBetween(7, 5, 6)
                    && board[7][4].check && board[7][7].check
                    && !new King(nowPlayer).isUnderAttack(this, 7, 4)) {
                swop(nowPlayer, 7, 4, 7, 5, 6);
                nextPlayer();
                return true;
            }
            return false;
        }
    }

    public boolean checkEmptyBetween(int line, int firstColumn, int secondColumn) {
        while (firstColumn < secondColumn) {
            if (board[line][firstColumn] != null) {
                return false;
            }
            firstColumn++;
        }
        return true;
    }

    public boolean checkSameColor(ChessPiece chessPiece1, ChessPiece chessPiece2) {
        return chessPiece1.getColor().equals(chessPiece2.getColor());
    }

    public boolean checkFigure(ChessPiece chessPiece1, ChessPiece chessPiece2) {
        return chessPiece1 != null && chessPiece2 != null
                && chessPiece1.getSymbol().equals("R") && chessPiece2.getSymbol().equals("K");
    }

    public void swop(String color, int line, int firstColumn, int secondColumn, int newFirstColumn, int newSecondColumn) {
        board[line][firstColumn] = null;
        board[line][secondColumn] = null;
        board[line][newFirstColumn] = new King(color);
        board[line][newSecondColumn] = new Rook(color);
        board[line][newFirstColumn].check = false;
        board[line][newSecondColumn].check = false;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) {
                return false;
            }
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                board[endLine][endColumn].check = false;
                board[startLine][startColumn] = null; // set null to previous cell
                nextPlayer();
                return true;
            }
        }
        return false;
    }

    private void nextPlayer() {
        nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}