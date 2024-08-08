public class Minister extends Piece{

    public Minister(int x, int y, boolean white, Table table) {
        super(x, y, white, (white ? Piece.MINISTER_WHITE : Piece.MINISTER_BLACK), table);
        super.nom = Piece.MINISTER_NAME;
    }

    @Override
    protected boolean checkMove() {
        if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) {
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
