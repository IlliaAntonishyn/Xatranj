public class Ruhk extends Piece {

    public Ruhk(int x, int y, boolean white, Table table) {
        super(x, y, white, (white ? Piece.RUHK_WHITE : Piece.RUHK_BLACK), table);
        super.nom = Piece.RUHK_NAME;
    }

    @Override
    protected boolean checkMove() {
        boolean blockedPath = false;

        if (x1 == x2 && y1 != y2 || x1 != x2 && y1 == y2) {
            if (y1 > y2) {
                for (int i = y1 - 1; i > y2 && !blockedPath; i--) {
                    blockedPath = !isEmptyCell(x1, i);
                }
            } else if (y1 < y2) {
                for (int i = y1 + 1; i < y2 && !blockedPath; i++) {
                    blockedPath = !isEmptyCell(x1, i);
                }
            } else if (x1 > x2) {
                for (int i = x1 - 1; i > x2 && !blockedPath; i--) {
                    blockedPath = !isEmptyCell(i, y1);
                }
            } else {
                for (int i = x1 + 1; i < x2 && !blockedPath; i++) {
                    blockedPath = !isEmptyCell(i, y1);
                }
            }
            if (blockedPath) {
                declareBlockedCellMove();
                return false;
            } else if (isMyPiece()) {
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
