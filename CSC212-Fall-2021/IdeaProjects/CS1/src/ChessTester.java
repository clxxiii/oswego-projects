/*
 * Tester for the chess class
 */
public class ChessTester {

    public static void main(String[] args) {
        // Tester classes
        ChessPiece p1 = new ChessPiece("rook", 1, 'a');
        ChessPiece p2 = new ChessPiece("king", 2, 'h');
        ChessPiece p3 = new ChessPiece("pawn", 4, 'e');
        ChessPiece p4 = new ChessPiece("bishop", 3, 'd');
        ChessPiece p5 = new ChessPiece("queen", 6, 'f');

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
    }
}
