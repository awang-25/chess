import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }

    @Override
    public String toString() {
        return "A " + super.toString() + " Queen";
    }

    @Override
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        ArrayList<Square> legalSquares = new ArrayList<>();
        int posRow = start.getRow();
        int posCol = start.getCol();
        //Right (east)
        for(int i = 1; posCol + i <= 7; i++){
            if(board[posRow][posCol+i].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow][posCol+i]);
                break;
            } else {
                legalSquares.add(board[posRow][posCol+i]);
            }
        }
        //Left (west)
        for(int i = 1; posCol - i >= 0; i++){
            if(board[posRow][posCol-i].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow][posCol-i]);
                break;
            } else {
                legalSquares.add(board[posRow][posCol-i]);
            }
        }
        //Down (South)
        for(int i = 1; posRow + i <= 7; i++){
            if(board[posRow+i][posCol].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow+i][posCol]);
                break;
            } else {
                legalSquares.add(board[posRow+i][posCol]);
            }
        }
        //Up (north)
        for(int i = 1; posRow - i >= 0; i++){
            if(board[posRow-i][posCol].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow-i][posCol]);
                break;
            } else {
                legalSquares.add(board[posRow-i][posCol]);
            }
        }
        //South-East
        for(int i = 1; (posRow + i <= 7)&&(posCol + i <= 7); i++){
            if(board[posRow+i][posCol+i].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow+i][posCol+i]);
                break;
            } else {
                legalSquares.add(board[posRow+i][posCol+i]);
            }
        }
        //South-West
        for(int i = 1; (posRow + i <= 7)&&(posCol - i >= 0); i++){
            if(board[posRow+i][posCol-i].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow+i][posCol-i]);
                break;
            } else {
                legalSquares.add(board[posRow+i][posCol-i]);
            }
        }
        //North-West
        for(int i = 1; (posRow - i >= 0)&&(posCol - i >= 0); i++){
            if(board[posRow-i][posCol-i].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow-i][posCol-i]);
                break;
            } else {
                legalSquares.add(board[posRow-i][posCol-i]);
            }
        }
        //North-East
        for(int i = 1; (posRow - i >= 0)&&(posCol + i <= 7); i++){
            if(board[posRow-i][posCol+i].isOccupied()) {
                System.out.println("occupied");
                legalSquares.add(board[posRow-i][posCol+i]);
                break;
            } else {
                legalSquares.add(board[posRow-i][posCol+i]);
            }   
        }
        return legalSquares;
    }

    @Override
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        ArrayList<Square> legalSquares = new ArrayList<>();
        int posRow = start.getRow();
        int posCol = start.getCol();
        //Right (east)
        for(int i = 1; posCol + i <= 7; i++){
            if(b.getSquareArray()[posRow][posCol+i].isOccupied()) {
                System.out.println("occupied");
                if(b.getSquareArray()[posRow][posCol+i].getOccupyingPiece().getColor() != getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow][posCol+i]);
                    System.out.println("Test");
                    break;
                    } else {
                        break;
                    }
                } else {
                    legalSquares.add(b.getSquareArray()[posRow][posCol+i]);
                }
            }
        //Left (west)
        for(int i = 1; posCol - i >= 0; i++){
            if(b.getSquareArray()[posRow][posCol-i].isOccupied()) {
                if(b.getSquareArray()[posRow][posCol-i].getOccupyingPiece().getColor() == !getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow][posCol-i]);
                    break;
                } else {
                    break;
                }
            } else {
                legalSquares.add(b.getSquareArray()[posRow][posCol-i]);
            }
        }
        //Down (South)
        for(int i = 1; posRow + i <= 7; i++){
            if(b.getSquareArray()[posRow+i][posCol].isOccupied()) {
                if(b.getSquareArray()[posRow+i][posCol].getOccupyingPiece().getColor() == !getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow+i][posCol]);
                    break;
                } else {
                    break;
                }
            } else {
                legalSquares.add(b.getSquareArray()[posRow+i][posCol]);
            }
        }
        //Up (north)
        for(int i = 1; posRow - i >= 0; i++){
            if(b.getSquareArray()[posRow-i][posCol].isOccupied()) {
                if(b.getSquareArray()[posRow-i][posCol].getOccupyingPiece().getColor() == !getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow-i][posCol]);
                    break;
                } else {
                    break;
                }
            } else {
                legalSquares.add(b.getSquareArray()[posRow-i][posCol]);
            }
        }
        //South-East
        for(int i = 1; (posRow + i <= 7)&&(posCol + i <= 7); i++){
            if(b.getSquareArray()[posRow+i][posCol+i].isOccupied()) {
                if(b.getSquareArray()[posRow+i][posCol+i].getOccupyingPiece().getColor() == !getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow+i][posCol+i]);
                    break;
                } else {
                    break;
                }
            } else {
                legalSquares.add(b.getSquareArray()[posRow+i][posCol+i]);
            }
        }
        //South-West
        for(int i = 1; (posRow + i <= 7)&&(posCol - i >= 0); i++){
            if(b.getSquareArray()[posRow+i][posCol-i].isOccupied()) {
                if(b.getSquareArray()[posRow+i][posCol-i].getOccupyingPiece().getColor() == !getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow+i][posCol-i]);
                    break;
                } else {
                    break;
                }
            } else {
                legalSquares.add(b.getSquareArray()[posRow+i][posCol-i]);
            }
        }
        //North-West
        for(int i = 1; (posRow - i >= 0)&&(posCol - i >= 0); i++){
            if(b.getSquareArray()[posRow-i][posCol-i].isOccupied()) {
                if(b.getSquareArray()[posRow-i][posCol-i].getOccupyingPiece().getColor() == !getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow-i][posCol-i]);
                    break;
                } else {
                    break;
                }
            } else {
                legalSquares.add(b.getSquareArray()[posRow-i][posCol-i]);
            }
        }
        //North-East
        for(int i = 1; (posRow - i >= 0)&&(posCol + i <= 7); i++){
            if(b.getSquareArray()[posRow-i][posCol+i].isOccupied()) {
                if(b.getSquareArray()[posRow-i][posCol+i].getOccupyingPiece().getColor() == !getColor()) {
                    legalSquares.add(b.getSquareArray()[posRow-i][posCol+i]);
                    break;
                } else {
                    break;
                }
            } else {
                legalSquares.add(b.getSquareArray()[posRow-i][posCol+i]);
            }
        }
        System.out.println(legalSquares);
        return legalSquares;
    }
}