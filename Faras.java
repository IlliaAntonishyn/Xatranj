public class Faras extends Piece{

    public Faras(int x, int y, boolean white, Table table) {
        super(x, y, white, (white ? Piece.FARAS_WHITE : Piece.FARAS_BLACK), table);
        super.nom = Piece.FARAS_NAME;
    }

    @Override
    protected boolean checkMove() {
        if (Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1 || Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2) {
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
