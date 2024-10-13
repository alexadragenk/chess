public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        ChessPiece newCell = chessBoard.board[toLine][toColumn];
        if (toLine == line || !checkPosition(line) || !checkPosition(toLine)) {
            return false;
        }
        if (toColumn == column || !checkPosition(column) || !checkPosition(toColumn)) {
            return false;
        }
        if (newCell != null && newCell.getColor().equals(this.getColor())) {
            return false;
        }
        if (!checkPath(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }
        return Math.abs(line - toLine) == Math.abs(column - toColumn);
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
