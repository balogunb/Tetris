import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.*;
import acm.util.*;
import java.awt.event.*;

/**
 * Tetris Game
 *
 * Basit Balogun
 * 1.00
 */
public class TetrisDesign extends GraphicsProgram{
    // initial size of the window 
    public static int 
    APPLICATION_WIDTH = 800,
    APPLICATION_HEIGHT = 900;

    //constants 
    int N_ROW = 26;
    int N_COLUMN = 15;
    double SIZE = 30;
    double WALL_W = 50;
    double DELAY = 100; 

    //instance variables 
    private GRect[][] grid ;
    private boolean [][] cubes;
    private RandomGenerator rand = new RandomGenerator();
    public Piece currentPiece;
    private GLabel startGame,score;
    private int gameScore;
    boolean gameOver;

    /** the run method, draw the inital graphics */
    public void run() {
        //draw graphics 
        //allows the program to see key imputs 
        //allows the program to see mouse activities
    }

    /** draws the initial graphics */
    public void drawGraphics(){
        //draw background
        //draw score label , start game label and title label
        //call the drawGrid method to draw the grid
        
        
        


    }
    
    /**draws a random tetris piece when called */
    public void addPiece(){
        //create a random tetris piece using random generator 
        //animate the tetris piece
    }

    /** handles the mouse event when mouse is pressed */
    public void mousePressed(GPoint point) {
        //turn the start game label to not visible
        //add a piece when clicks at the beginning of the game 
        //if game is over redraw graphics 
    }
    
    /** handles the key event when a key is pressed */
    public void keyPressed(KeyEvent e){
        // press up botton to  rotate the piece 
        
        // press left botton to move the piece left 
        
        // press right button to move piece right 
        
        // press down button to move piece down faster 
        
    }
    
    
    /**draws the grid in which the tetris piece would be contained  */
    public void drawGrid(){
        //use loops and arrays to draw a grid of GRect rectangles

    }
    
    
    /**Allows the piece to be seen to be moving my hiding and showing pieces while factors like row and column are being changed */
    public void show(Piece aPiece, boolean on){
        //allow piece to be show and hidden using arrays and loops

    }
    
    
    /**Checks Collision between pieces and boundaries and between pieces */
    public boolean checkCollision(Piece p, int leftRight, int topBottom){
        //check if piece collides with boundaries 
        //if piece collides increase score by 1 and return true 
        //check if piece collides with each other return true 
        //check if a row is filled with piece part
        //if a row is filled with piece parts remove piece and reduce the row number of all objects above the line by 1
        //if piece collides increase score by 1
        //if row is filled increase score by 10
        //if top most row contains a non animated square and a new piece collides set game over to true 

    }

}
