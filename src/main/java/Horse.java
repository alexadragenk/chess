public class Horse extends ChessPiece {

    public Horse(String color) {
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

        return Math.abs(column - toColumn) == 2 && Math.abs(line - toLine) == 1
                || Math.abs(line - toLine) == 2 && Math.abs(column - toColumn) == 1;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
