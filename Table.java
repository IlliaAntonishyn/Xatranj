public class Table {

    private final String[][] layout = {
        {Piece.RUHK_BLACK, Piece.FARAS_BLACK, Piece.ELEFANT_BLACK, Piece.XA_BLACK, Piece.MINISTER_BLACK, Piece.ELEFANT_BLACK, Piece.FARAS_BLACK, Piece.RUHK_BLACK},
        {Piece.BAIDAQ_BLACK, Piece.BAIDAQ_BLACK, Piece.BAIDAQ_BLACK, Piece.BAIDAQ_BLACK, Piece.BAIDAQ_BLACK, Piece.BAIDAQ_BLACK, Piece.BAIDAQ_BLACK, Piece.BAIDAQ_BLACK},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null, null},
        {Piece.BAIDAQ_WHITE, Piece.BAIDAQ_WHITE, Piece.BAIDAQ_WHITE, Piece.BAIDAQ_WHITE, Piece.BAIDAQ_WHITE, Piece.BAIDAQ_WHITE, Piece.BAIDAQ_WHITE, Piece.BAIDAQ_WHITE},
        {Piece.RUHK_WHITE, Piece.FARAS_WHITE, Piece.ELEFANT_WHITE, Piece.XA_WHITE, Piece.MINISTER_WHITE, Piece.ELEFANT_WHITE, Piece.FARAS_WHITE, Piece.RUHK_WHITE}
    };
    private Piece[][] table;

    public Table() {
        table = new Piece[8][8];

        String symbol;
        Piece piece;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                try{
                    symbol = layout[i][j];
                }catch(IndexOutOfBoundsException e){
                    symbol = null;
                }

                if(symbol == null){
                    piece = null;
                }else if(symbol.toLowerCase().equals(Piece.BAIDAQ_BLACK)){
                    piece = new Baidaq(j, i, symbol.equals(Piece.BAIDAQ_WHITE), this);
                }else if(symbol.toLowerCase().equals(Piece.RUHK_BLACK)){
                    piece = new Ruhk(j, i, symbol.equals(Piece.RUHK_WHITE), this);
                }else if(symbol.toLowerCase().equals(Piece.FARAS_BLACK)){
                    piece = new Faras(j, i, symbol.equals(Piece.FARAS_WHITE), this);
                }else if(symbol.toLowerCase().equals(Piece.ELEFANT_BLACK)){
                    piece = new Elefant(j, i, symbol.equals(Piece.ELEFANT_WHITE), this);
                }else if(symbol.toLowerCase().equals(Piece.XA_BLACK)){
                    piece = new Xa(j, i, symbol.equals(Piece.XA_WHITE), this);
                }else if(symbol.toLowerCase().equals(Piece.MINISTER_BLACK)){
                    piece = new Minister(j, i, symbol.equals(Piece.MINISTER_WHITE), this);
                }else{
                    piece = null;
                }

                table[i][j] = piece;
            }
        }
    }

    public Piece[][] getTable(){
        return table;
    }

    public Piece getPiece(int x, int y){
        return table[y][x];
    }

    public boolean checkVictory(){
        boolean whiteXa = false;
        boolean blackXa = false;
        int countWhitePieces = 0;
        int countBlackPieces = 0;

        for(Piece[] row: table){
            for(Piece cell: row){
                if(cell != null){
                    if(cell.white){
                        countWhitePieces++;
                        if(cell.nom.equals("Xa")){
                            whiteXa = true;
                        }
                    }else{
                        countBlackPieces++;
                        if(cell.nom.equals("Xa")){
                            blackXa = true;
                        }
                    }
                }
            }
        }

        if(!blackXa){
            System.out.println("El " + Piece.XA_NAME + " Negre ha mort");
            return true;
        }else if(!whiteXa){
            System.out.println("El " + Piece.XA_NAME + " Blanc ha mort");
            return true;
        }else if(countBlackPieces == 1){
            System.out.println("El " + Piece.XA_NAME + " Negre s'ha quedat sol");
            return true;
        }else if(countWhitePieces == 1){
            System.out.println("El " + Piece.XA_NAME + " Blanc s'ha quedat sol");
            return true;
        }else{
            return false;
        }
    }

    public void printTable() {
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " | ");
            Piece[] row = table[i];
            for (Piece cell : row) {
                if (cell != null) {
                    System.out.print(cell + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("  -----------------");
        System.out.println("    a b c d e f g h");
    }

    public boolean executeMove(String move) {

        int x1, y1, x2, y2;

        if (move.length() != 5 || move.charAt(2) != ' ' || !Character.isLetter(move.charAt(0))
                || !Character.isLetter(move.charAt(3)) || !Character.isDigit(move.charAt(1))
                || !Character.isDigit(move.charAt(4))) {
            System.out.println("ERROR: Format incorrecte");
            return false;
        }else{
            x1 = calcX(move.charAt(0));
            y1 = calcY(move.charAt(1));
            x2 = calcX(move.charAt(3));
            y2 = calcY(move.charAt(4));

            if (x1 == -1 || y1 == -1 || x2 == -1 || y2 == -1) {
                System.out.println("ERROR: Fora del tauler");
                return false;
            } else if(x1 == x2 && y1 == y2){
                System.out.println("ERROR: No hi ha moviment");
                return false;
            }else if(table[y1][x1] == null){
                System.out.println("ERROR: Casella buida");
                return false;                
            }else if(table[y1][x1].white != Main.isWhiteTurn()){
                System.out.println("ERROR: No es la teva figura");
                return false;  
            }else{
                return table[y1][x1].move(x2, y2);
            }
        }
    }

    private int calcY(char y) {
        switch (y) {
            case '1':
                return 7;
            case '2':
                return 6;
            case '3':
                return 5;
            case '4':
                return 4;
            case '5':
                return 3;
            case '6':
                return 2;
            case '7':
                return 1;
            case '8':
                return 0;
            default:
                return -1;
        }
    }

    private int calcX(char x) {
        switch (x) {
            case 'h':
                return 7;
            case 'g':
                return 6;
            case 'f':
                return 5;
            case 'e':
                return 4;
            case 'd':
                return 3;
            case 'c':
                return 2;
            case 'b':
                return 1;
            case 'a':
                return 0;
            default:
                return -1;
        }
    }

}