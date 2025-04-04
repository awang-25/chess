// Name: Adam Wang

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
	// Resource location constants for piece images
    private static final String RESOURCES_WBISHOP_PNG = "wbishop.png";
	private static final String RESOURCES_BBISHOP_PNG = "bbishop.png";
	private static final String RESOURCES_WKNIGHT_PNG = "wknight.png";
	private static final String RESOURCES_BKNIGHT_PNG = "bknight.png";
	private static final String RESOURCES_WROOK_PNG = "wrook.png";
	private static final String RESOURCES_BROOK_PNG = "brook.png";
	private static final String RESOURCES_WKING_PNG = "wking.png";
	private static final String RESOURCES_BKING_PNG = "bking.png";
	private static final String RESOURCES_BQUEEN_PNG = "bqueen.png";
	private static final String RESOURCES_WQUEEN_PNG = "wqueen.png";
	private static final String RESOURCES_WPAWN_PNG = "wpawn.png";
	private static final String RESOURCES_BPAWN_PNG = "bpawn.png";

    private static final String RESOURCES_WJONKLER_PNG = "wjoker.png";
    private static final String RESOURCES_BJONKLER_PNG = "bjoker.png";
	
	// Logical and graphical representations of board
	private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;

    //if the player is currently dragging a piece this variable contains it.
    private Piece currPiece;
    private Square fromMoveSquare;
    
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
    
    private Square sq;
    
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        //TO BE IMPLEMENTED FIRST
     
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i % 2 == 0){
                    if(j % 2 == 0){
                        board[i][j] = new Square(this, true, i, j);
                        this.add(board[i][j]);
                    } else {
                        board[i][j] = new Square(this, false, i, j);
                        this.add(board[i][j]);
                    }
                } else {
                    if(j % 2 == 0){
                        board[i][j] = new Square(this, false, i, j);
                        this.add(board[i][j]);
                    } else {
                        board[i][j] = new Square(this, true, i, j);
                        this.add(board[i][j]);
                    }
                }
            }
        }  
//        	populate the board with squares here. Note that the board is composed of 64 squares alternating from 
//        	white to black.

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.
    // preconditions: row and columb values must be greater than or equal to 0 and less than 8
    // postcondition: all pieces are shown on the board
    private void initializePieces() {
    	board[0][0].put(new Rook(false, RESOURCES_BROOK_PNG));
        board[0][1].put(new Knight(false, RESOURCES_BKNIGHT_PNG));
        board[0][2].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
        board[0][3].put(new Queen(false, RESOURCES_BQUEEN_PNG));
        board[0][4].put(new King(false, RESOURCES_BKING_PNG));
        board[0][5].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
        board[0][6].put(new Knight(false, RESOURCES_BKNIGHT_PNG));
        board[0][7].put(new Rook(false, RESOURCES_BROOK_PNG));
        for(int i = 0; i < 8; i++){
            board[1][i].put(new Joker(false, RESOURCES_BJONKLER_PNG));
        }

        board[7][0].put(new Rook(true, RESOURCES_WROOK_PNG));
        board[7][1].put(new Knight(true, RESOURCES_WKNIGHT_PNG));
        board[7][2].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
        board[7][3].put(new Queen(true, RESOURCES_WQUEEN_PNG));
        board[7][4].put(new King(true, RESOURCES_WKING_PNG));
        board[7][5].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
        board[7][6].put(new Knight(true, RESOURCES_WKNIGHT_PNG));
        board[7][7].put(new Rook(true, RESOURCES_WROOK_PNG));
        for(int j = 0; j < 8; j++){
            board[6][j].put(new Joker(true, RESOURCES_WJONKLER_PNG));
        }
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
     
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if(sq == fromMoveSquare)
                	 sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                
            }
        }
    	if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
       
            currX = e.getX();
            currY = e.getY();

            sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
            
            if (sq.isOccupied()) {
                currPiece = sq.getOccupyingPiece();
                fromMoveSquare = sq;
                if (currPiece != null && currPiece.getColor() != whiteTurn){
                    currPiece = null;
                    return;
                }
               
                sq.setDisplay(false);
            }
            repaint();
        
    }

    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to its new board location. 
    @Override
    // Called when user lets go of piece
    // preconditoins: piece has been picked up, endquare is on a square with row and columb greater than or equal to 0 and less than 8
    // postcondition: if piece has been moved to a legal square, then it is dropped there; if not it goes back to original position
    public void mouseReleased(MouseEvent e) {
        if(currPiece != null && whiteTurn == currPiece.getColor()){ // only allow moves which do not put your own king in check
            
            Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
            Piece takenPiece = null;

            //using currPiece
            for(Square [] row: board) {
        	    for(Square s: row) {
        		    s.setBorder(null);
                    if(currPiece.getLegalMoves(this,fromMoveSquare).contains(endSquare)){
                        if(endSquare.getOccupyingPiece() != null){
                            takenPiece = endSquare.getOccupyingPiece();
                        }
                        endSquare.put(currPiece);
                        currPiece.changeType();
                        fromMoveSquare.removePiece();
                        if(isInCheck(whiteTurn)){
                           // System.out.println("I'm in check");
                            fromMoveSquare.put(currPiece);
                            endSquare.removePiece();
                            if(takenPiece != null){
                                endSquare.put(takenPiece);
                            }
                        }else{
                         whiteTurn = !whiteTurn;
                        }
                    } //else {
                        //sq = fromMoveSquare;
                    //}
        	    }
            }
            fromMoveSquare.setDisplay(true);
            currPiece = null;
            repaint();
        }
    }

    @Override
    // Called when user is holding the piece and moving the mouse
    public void mouseDragged(MouseEvent e) {
        if(currPiece != null){
        currX = e.getX() - 24;
        currY = e.getY() - 24;
        if(currPiece!= null) {
        	for(Square s: currPiece.getLegalMoves(this, fromMoveSquare)) {
        		s.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        	}
        	
        }
        
    }
        repaint();
    }

    //precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    //postcondition - returns true of the king is in check and false otherwise.
    // You’ll need to traverse the board one more time looking for all the pieces of the opposite color of the king you care about 
    // to find out if your king is in check. Don’t forget that each piece has a getControlledSquares function and lastly that you 
    // can identify the king using the instanceof key word.

    public boolean isInCheck(boolean kingColor){
        for(Square[] row : board){
            for(Square s : row){
                if(s.getOccupyingPiece() != null && s.getOccupyingPiece().getColor() != kingColor){
                    for(Square sq: s.getOccupyingPiece().getControlledSquares(board, s)){
                        if(sq.getOccupyingPiece() != null && sq.getOccupyingPiece() instanceof King && sq.getOccupyingPiece().getColor()== kingColor){
                            System.out.println("true");
                            return true;
                        }
                }
                }   
            }
        }
      
        System.out.println("false");
        return false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}