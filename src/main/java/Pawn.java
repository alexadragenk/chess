public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        ChessPiece newCell = chessBoard.board[toLine][toColumn];
        if (toLine == line || !checkPosition(line) || !checkPosition(toLine)) {
            return false;
        }
        if (!checkPosition(column) || !checkPosition(toColumn)) {
            return false;
        }
        if (newCell != null && newCell.getColor().equals(this.getColor())) {
            return false;
        }
        if (!checkPath(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }
        if (getColor().equals("White") && line < toLine && Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 1) {
            return true;
        }
        if (getColor().equals("Black") && line > toLine && Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 1) {
            return true;
        }
        return getColor().equals("White") && line == 1 && Math.abs(line - toLine) <= 2 && column == toColumn
                || getColor().equals("Black") && line == 6 && Math.abs(line - toLine) <= 2 && column == toColumn;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
