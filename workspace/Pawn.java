
import java.util.ArrayList;

public class Pawn extends Piece{
public Pawn(boolean isWhite, String img_file) {
super (isWhite, img_file);
}

public ArrayList<Square> getControlledSquares(Square [][] board, Square start)
{
ArrayList<Square> tile = new ArrayList<Square>();
if (color == true){
if(start.getCol() != 7){
tile.add(board[start.getRow() - 1][start.getCol() + 1]);
}
if (start.getCol() != 0){
tile.add(board[start.getRow() - 1][start.getCol() - 1]);
}
}
if (color == false){
if(start.getCol() != 7){
tile.add(board[start.getRow() + 1][start.getCol() + 1]);
}
if (start.getCol() != 0){
tile.add(board [start.getRow() + 1][start.getCol() - 1]);
}
}
return tile;
}

public ArrayList<Square> getLegalMoves(Board b, Square start){
ArrayList<Square> tile = new ArrayList<Square>();
if(color == true){
//if moving for the first time
int potential = start.getRow();
potential = potential - 1;
if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
tile.add(b.getSquareArray()[potential][start.getCol()]);
}
if (start.getRow() == 6){
potential = potential - 1;
if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
tile.add(b.getSquareArray()[potential][start.getCol()]);
}
}
if (start.getCol() != 0 && b.getSquareArray()[start.getRow() - 1]
[start.getCol() - 1].isOccupied() && b.getSquareArray()[start.getRow() - 1]
[start.getCol() - 1].getOccupyingPiece().getColor() == false) {
tile.add(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]);
}
if (start.getCol() != 7 && b.getSquareArray()[start.getRow() - 1]
[start.getCol() + 1].isOccupied() && b.getSquareArray()[start.getRow() - 1]
[start.getCol() + 1].getOccupyingPiece().getColor() == false) {
tile.add(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]);
}
}
if(color == false){
//if moving for the first time
int potential = start.getRow();
potential = potential + 1;
if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
tile.add(b.getSquareArray()[potential][start.getCol()]);
}
if (start.getRow() == 1){
potential = potential + 1;
if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
tile.add(b.getSquareArray()[potential][start.getCol()]);
}
}
if (start.getCol() != 0 && b.getSquareArray()[start.getRow() + 1]
[start.getCol() - 1].isOccupied() && b.getSquareArray()[start.getRow() + 1]
[start.getCol()- 1].getOccupyingPiece().getColor() == true) {
tile.add(b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]);
}
if (start.getCol() != 7 && b.getSquareArray()[start.getRow() + 1]
[start.getCol() + 1].isOccupied() && b.getSquareArray()[start.getRow() + 1]
[start.getCol() + 1].getOccupyingPiece().getColor() == true) {
tile.add(b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]);
}
}
return tile;
}

public String toString(){
    if(color){
        return "A white Pawn";
    } 
    return "A black Pawn";
}
}