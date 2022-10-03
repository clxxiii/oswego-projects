/*
 * Imitates a chess piece
 */
public class ChessPiece {

// Private Instance Variables
    private String piece;
    private int row;
    private char column;

    public ChessPiece(String piece, int row, char column) {
        this.piece = piece;
        this.row = row;
        this.column = column;
    }


    public String toString() {
        String pieceText = piece.substring(0, 1);
        return pieceText + column + row;
    }
}