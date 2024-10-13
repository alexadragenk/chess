import java.util.Objects;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        ChessPiece newCell = chessBoard.board[toLine][toColumn];
        if (!checkPosition(line) || !checkPosition(toLine)) {
            return false;
        }
        if (!checkPosition(column) || !checkPosition(toColumn)) {
            return false;
        }
        if (toColumn == column && toLine == line) {
            return false;
        }
        if (newCell != null && newCell.getColor().equals(this.getColor())) {
            return false;
        }
        if (!checkPath(chessBoard, line, column, toLine, toColumn)) {
            return false;
        }
        return Math.abs(line - toLine) == 1 || Math.abs(column - toColumn) == 1 || Math.abs(line - toLine) == 1 && Math.abs(column - toColumn) == 1;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece figure = board.board[i][j];
                if (figure != null && !Objects.equals(figure.getColor(), this.getColor()) && figure.canMoveToPosition(board, i, j, line, column)) {
                    return true;
                }
            }
        }
        return false;
    }
}
