import acm.program.*;
import acm.graphics.*;
//import acm.util.*;
import java.awt.*;
//import java.util.*;

/**
 * This class creates the pieces used in the tetris game class. 
 * Basit Balogun
 * 
 */
public class PieceDesign extends GCompound implements Runnable{

    //constants 
    double DELAY = 500; 
    double WALL_W = 50;

    //instance variables
    int row;
    int col;
    int cSize = 30;
    int size;
    boolean [][] cubes;
    char type; //'L','I','O','T','S','Z', 'J'
    Color color;
    Tetris game;
    GRect shape;
    //int r;
    //int c;
    boolean isAlive = true;
    /** constructor to create the a tetris Peice*/
    public Piece(char type, int row, int col, Tetris game){
        this.type = type;
        this.row = row;
        this.col = col;
        this.game = game;
        //represent each piece using a boolean array of array 
        
        //convert the boolean array to create GRectangles that would look like the intended shape 
       

    }
    
    /** the run method */
    public void run() {
        while (isAlive){//if the current piece ia alive run animation 
            oneTimeStep();
            pause(DELAY); 
            
        }
        

    }
    
    /** return the size of the piece */
    public int pSize(){
        return size;
    }
    
    /** in each timestep check if the piece collided and move down if it did not */
    public void oneTimeStep(){
        //if check collision returns false run moveDown()
        //if check collision is true set isalive to true and draw a new piece 
        
    }

    /** returns the current row of the piece  */
    public int getRow(){
        return row ;
    }

    /** returns the current column of the piece  */
    public int getColumn(){
        return col;
    }

    /** returns the piece as a boolean  */
    public boolean[][] getCubes(){
        return cubes;
    }
    
    /** returns the color of the piece*/
    public Color getColor(){
        return color;
    }

    /** moves the piece down by increasing the row number **/
    public void moveDown(){
        //hide the piece 
        //increase the row no
        //show the piece 

    }

    /** moves the piece left by decreasing the column number   */
    public void moveLeft(){
        //hide the piece 
        //decrease the col no 
        //show the piece

    }
    
    
    /** moves the piece right by increasing the column number   */
    public void moveRight(){
        //hide the piece 
        //increase the col no
        //show the piece

    }
    
    
    /** rotates the piece by 180 degrees */
    public void rotate(){
       //hide the piece 
        //see the boolean piece as a matrix and convert it to its inverse to rotate 
        //show the piece

    }
}
