import java.util.Scanner;

public class Main {

    private static Scanner scan = new Scanner(System.in);
    private static Table table;

    private static int turn;

    public static void main(String[] args) {
        table = new Table();
        turn = 0;

        System.out.println();

        gameLoop();

        if(turn % 2 == 1){
            System.out.println("GUANYEN LES BLANQUES");
        }else{
            System.out.println("GUANYEN LES NEGRES");
        }
        System.out.println();
    }

    public static void gameLoop(){
        boolean moveSuccessful;
        while (!table.checkVictory()) {
            turn++;

            System.out.println("(jugades en estil \"cela1 cela2\", p.e. \"e1 d2\")");
            System.out.println();
            table.printTable();
            System.out.println();
            System.out.println("Torn " + turn + ". Juguen les " + (turn % 2 == 0 ? "Negres" : "Blanques"));

            do{
                System.out.print("Jugada: ");

                moveSuccessful = table.executeMove(scan.nextLine());

                System.out.println();
            }while(!moveSuccessful);

            System.out.println();
        }
    }

    public static Table getTable(){
        return table;
    }

    public static boolean isWhiteTurn(){
        return turn % 2 == 1;
    }

}