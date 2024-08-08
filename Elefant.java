public class Elefant extends Piece{

    public Elefant(int x, int y, boolean white, Table table) {
        super(x, y, white, (white ? Piece.ELEFANT_WHITE : Piece.ELEFANT_BLACK), table);
        super.nom = Piece.ELEFANT_NAME;
    }

    @Override
    protected boolean checkMove() {
        if (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 2) {
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
