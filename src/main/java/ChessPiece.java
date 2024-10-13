public abstract class ChessPiece {
    public String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    public boolean checkPosition(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean checkPath(ChessBoard board, int line, int column, int toLine, int toColumn) {
        int steps = Math.max((Math.abs(line - toLine)), (Math.abs(column - toColumn)));
        int dx = (int) Math.signum(toColumn - column);
        int dy = (int) Math.signum(toLine - line);
        for (int i = 1; i < steps; i++) {
            int l = line + dy * i;
            int k = column + dx * i;
            if (!checkPosition(l) || !checkPosition(k)) {
                return false;
            }

            ChessPiece cell = board.board[l][k];
            if (cell != null)
                return false;
        }
        return true;
    }
}
