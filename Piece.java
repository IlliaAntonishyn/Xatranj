public abstract class Piece {

    public static final String BAIDAQ_NAME = "Baidaq";
    public static final String RUHK_NAME = "Ruhk";
    public static final String FARAS_NAME = "Faras";
    public static final String ELEFANT_NAME = "Elefant";
    public static final String XA_NAME = "Xa";
    public static final String MINISTER_NAME = "Ministre";

    public static final String BAIDAQ_BLACK = "b";
    public static final String RUHK_BLACK = "r";
    public static final String FARAS_BLACK = "f";
    public static final String ELEFANT_BLACK = "e";
    public static final String XA_BLACK = "x";
    public static final String MINISTER_BLACK = "m";
    public static final String BAIDAQ_WHITE = "B";
    public static final String RUHK_WHITE = "R";
    public static final String FARAS_WHITE = "F";
    public static final String ELEFANT_WHITE = "E";
    public static final String XA_WHITE = "X";
    public static final String MINISTER_WHITE = "M";

    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected boolean white;
    protected String symbol;
    protected String nom;
    protected Table table;

    public Piece(int x, int y, boolean white, String symbol, Table table) {
        this.x1 = x;
        this.y1 = y;
        this.white = white;
        this.symbol = symbol;
        this.table = table;
    }

    public boolean move(int x, int y) {
        x2 = x;
        y2 = y;
        if (checkMove()) {
            Piece[][] table = this.table.getTable();

            table[y2][x2] = this;
            table[y1][x1] = null;
            x1 = x2;
            y1 = y2;

            return true;
        } else {
            return false;
        }
    }

    protected abstract boolean checkMove();

    protected boolean isMyPiece() {
        Piece piece = table.getPiece(x2, y2);

        if (piece != null) {
            return piece.white && this.white;
        } else {
            return false;
        }
    }

    protected boolean isEnemyPiece() {
        Piece piece = table.getPiece(x2, y2);

        if (piece != null) {
            return piece.white == !this.white;
        } else {
            return false;
        }
    }

    protected boolean isEmptyCell() {
        return table.getPiece(x2, y2) == null;
    }

    protected boolean isEmptyCell(int x, int y) {
        return table.getPiece(x, y) == null;
    }

    protected void declareInvalidMove() {
        System.out.println("ERROR: Moviment invalid per " + nom);
    }

    protected void declareEmptyCellTake() {
        System.out.println("ERROR: " + nom + " no pot atacar una cella buida");
    }

    protected void declareOccupiedCellMove() {
        System.out.println("ERROR: Cella ocupada, " + nom + " no es pot moure");
    }

    protected void declareBlockedCellMove() {
        System.out.println("ERROR: Cami a la cella bloquejat, " + nom + " no es pot moure");
    }

    protected void declareYourCellTake() {
        System.out.println("ERROR: " + nom + " no pot atacar la teva propia peca");
    }

    protected void declarePerformedMove() {
        System.out.println();
        System.out.println(nom + " (" + x1 + ", " + y1 + ") s'ha mogut a " + x2 + ", " + y2);
    }

    protected void declarePerformedTake() {
        System.out.println();
        System.out.println(nom + " (" + x1 + ", " + y1 + ") ataca " + x2 + ", " + y2);
    }

    @Override
    public String toString() {
        return symbol;
    }

}