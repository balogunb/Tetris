import acm.program.*;
import acm.graphics.*;
import java.awt.*;

/**
 * This class creates the pieces used in the tetris game class. 
 * Basit Balogun
 * 
 */
public class Piece extends GCompound implements Runnable{

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
        //each piece shape is represented in boolean 
        if(type == 'L') {
            size =3;
            color = Color.RED;
            cubes = new boolean[][]{{false,false,true},
                {true, true, true},
                {false,false ,false }};
        }

        if(type == 'I') {
            size = 4;
            color = Color.BLUE;
            cubes = new boolean[][]{{false,false,false,false},
                {true,true, true, true},
                {false,false,false,false},
                {false,false,false,false}};
        }

        if(type == 'O') {
            size = 2;
            color = Color.GREEN;
            cubes = new boolean[][]{{true,true},
                {true, true}};
        }

        if(type == 'T') {
            size = 3;
            color = Color.PINK;
            cubes = new boolean[][]{{false,true,false},
                {true,true,true},
                {false,false ,false }};
        }        

        if(type == 'S') {
            size = 3;
            color = Color.YELLOW;
            cubes = new boolean[][]{{false,true,true},
                {true, true, false},
                {false,false ,false }};
        }

        if(type == 'Z') {
            size = 3;
            color = Color.ORANGE;
            cubes = new boolean[][]{{true,true,false},
                {false, true, true},
                {false,false ,false }};
        }

        if(type == 'J') {
            size =3;
            color = Color.CYAN;
            cubes = new boolean[][]{{true,false,false},
                {true, true, true},
                {false,false ,false }};

        }

        //used the boolean to create GRectangles that would look like the intended shape 
        for(int r = 0; r < this.size; r++){
            for(int  c = 0; c < this.size; c++){
                if(cubes[r][c] == true){
                    shape = new GRect(cSize,cSize);
                    shape.setColor(color);
                    shape.setFilled(true);
                    shape.setFillColor(color);
                    add(shape,WALL_W + cSize*c,WALL_W + cSize*r);

                }

            }
        }

    }

    /** the run method */
    public void run() {
        while (isAlive){//if the current piece ia alive run animation 
            oneTimeStep();
            pause(DELAY); 

        }

    }

    /** in each timestep check if the piece collided and move down if it did not */
    public void oneTimeStep(){
        //if(!game.checkCollision(game.currentPiece, 1,2)){
            moveDown();

        //}
        if(game.checkCollision(game.currentPiece, 1,2)){

            isAlive = false; //used to stop animation if a piece collides
            game.addPiece();//add new piece every time the previous piece collides with sometime 

        }


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
        // Only allow move down when there is space at the bottom 
        if(!game.checkCollision(game.currentPiece, 1,2)){
            game.show(game.currentPiece,false);
            row++;
            game.show(game.currentPiece,true);
        }
    }

    /** moves the piece left by decreasing the column number   */
    public void moveLeft(){
        game.show(game.currentPiece,false);
        col--;
        game.show(game.currentPiece,true);

    }

    /** moves the piece right by increasing the column number   */
    public void moveRight(){
        game.show(game.currentPiece,false);
        col++;
        game.show(game.currentPiece,true);

    }

    /** rotates the piece by 180 degrees */
    public void rotate(){
        game.show(game.currentPiece,false);
        boolean[][] rotatedCubes = new boolean [size][size];

        for(int r = 0; r < size ; r++){
            for(int c = 0; c < size ; c++){
                rotatedCubes[r][c] = cubes [size-1-c][r];
            }
        }
        cubes = rotatedCubes; // rotated cube show be seen as the current cube 
        game.show(game.currentPiece,true);

    }
}
