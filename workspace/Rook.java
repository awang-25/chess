
import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }
    public String toString(){
        boolean checkColor = this.getColor();
        String result;
        if (checkColor){
            result = "A White Rook";
        } else {
            result = "A Black Rook";
        }
        return result;
    }

    //pre condition: board is 2d array of squares. start is a square and been initialized.
    //post condition: returns arrayList of squares that the piece could theorhetically capture into on the next move
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        ArrayList<Square> controlledSquares = new ArrayList<>();
        int boardSize = board.length;
        int startRow = start.getRow();
        int startCol = start.getCol();
        for (int row = startRow - 1; row>=0; row--){
            controlledSquares.add(board[row][startCol]);
            if (board[row][startCol].getOccupyingPiece() != null){
                break;
            }
        }
        for (int row = startRow + 1; row<boardSize; row++){
            controlledSquares.add(board[row][startCol]);
            if (board[row][startCol].getOccupyingPiece() != null){
                break;
            }
        }
        for (int col = startCol - 1; col>=0; col--){
            controlledSquares.add(board[startRow][col]);
            if (board[startRow][col].getOccupyingPiece() != null){
                break;
            }
        }
        for (int col = startCol + 1; col < boardSize; col++){
            controlledSquares.add(board[startRow][col]);
            if (board[startRow][col].getOccupyingPiece() != null){
                break;
            }
        }
        return controlledSquares;
    }

    //pre condition: b is of type board and has been properly initialized. start is of type square and has been properly initialized.
    //post condition: returns arrayList of possible moves such that the piece moves like a rook. moves straight in all 4 directions and cannot pass over other pieces.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        ArrayList<Square> legalMoves = new ArrayList<>();
        int currentRow = start.getRow();
        int currentCol = start.getCol();
        Square[][] board = b.getSquareArray();
        int boardSize = board.length;
        boolean isWhite = this.getColor();
        for(int col = currentCol + 1; col < boardSize; col++){
            Square tempSquare = board[currentRow][col];
            if (tempSquare.getOccupyingPiece() == null){
                legalMoves.add(tempSquare);
            } else {
                if(tempSquare.isOccupied() && tempSquare.getOccupyingPiece().getColor() != isWhite){
                    legalMoves.add(tempSquare);
                }
            break;
            }
        }
        for (int col = currentCol - 1; col >=0; col--){
            Square tempSquare = board[currentRow][col];
            if (tempSquare.getOccupyingPiece() == null){
                legalMoves.add(tempSquare);
            } else {
                if(tempSquare.isOccupied() && tempSquare.getOccupyingPiece().getColor() != isWhite){
                    legalMoves.add(tempSquare);
                }
                break;
            }
        }
        for (int row = currentRow - 1; row >= 0; row--){
            Square tempSquare = board[row][currentCol];
            if (tempSquare.getOccupyingPiece() == null){
                legalMoves.add(tempSquare);
            } else{
                if (tempSquare.isOccupied() &&
                    tempSquare.getOccupyingPiece().getColor() != isWhite){
                    legalMoves.add(tempSquare);
                }
                break;
            }
        }
        for (int row = currentRow + 1; row < boardSize; row++){
            Square tempSquare = board[row][currentCol];
            if (tempSquare.getOccupyingPiece() == null){
                legalMoves.add(tempSquare);
            } else {
                if (tempSquare.isOccupied() &&
                    tempSquare.getOccupyingPiece().getColor() != isWhite){
                    legalMoves.add(tempSquare);
                }
                break;
            }
        }
        return legalMoves;
    }
}