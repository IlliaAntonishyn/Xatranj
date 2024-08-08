public class Baidaq extends Piece {

    public Baidaq(int x, int y, boolean white, Table table) {
        super(x, y, white, (white ? Piece.BAIDAQ_WHITE : Piece.BAIDAQ_BLACK), table);
        super.nom = Piece.BAIDAQ_NAME;
    }

    @Override
    public boolean move(int x, int y) {
        x2 = x;
        y2 = y;
        if (checkMove()) {
            Piece[][] table = this.table.getTable();

            table[y2][x2] = this;
            table[y1][x1] = null;
            x1 = x2;
            y1 = y2;

            if (white && y1 == 0 || !white && y1 == 7) {
                table[y2][x2] = new Minister(x2, y2, white, this.table);
                System.out.println(Piece.BAIDAQ_NAME + " s'ha transformat en " + Piece.MINISTER_NAME);
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected boolean checkMove() {
        if (x1 == x2 && (y1 - 1 == y2 && white || y1 + 1 == y2 && !white)) {
            if (isEmptyCell()) {
                declarePerformedMove();
                return true;
            } else {
                declareOccupiedCellMove();
                return false;
            }
        } else if ((x1 - 1 == x2 || x1 + 1 == x2) && (y1 - 1 == y2 && white || y1 + 1 == y2 && !white)) {
            if (isEnemyPiece()) {
                declarePerformedTake();
                return true;
            } else if (isMyPiece()) {
                declareYourCellTake();
                return false;
            } else {
                declareEmptyCellTake();
                return false;
            }
        } else {
            declareInvalidMove();
            return false;
        }
    }

}
