public class Xa extends Piece {

    public Xa(int x, int y, boolean white, Table table) {
        super(x, y, white, (white ? Piece.XA_WHITE : Piece.XA_BLACK), table);
        super.nom = Piece.XA_NAME;
    }

    @Override
    protected boolean checkMove() {
        if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1 || Math.abs(x1 - x2) == 1 && Math.abs(y1 - y1) == 0
                || Math.abs(x1 - x2) == 0 && Math.abs(y1 - y1) == 1) {
            if (isMyPiece()) {
                declareYourCellTake();
                return false;
            } else if (isEnemyPiece()) {
                declarePerformedTake();
                return true;
            } else {
                declarePerformedMove();
                return true;
            }
        } else {
            declareInvalidMove();
            return false;
        }

    }

}
