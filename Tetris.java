import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.*;
import acm.util.*;
import java.awt.event.*;

/**
 * Tetris Game
 * The main class of the game, which shows animations and handles controls of the tetris peices 
 * My design did not fully accomodate for the fact that we are not moving graphical objects in this  project. This lead to so many problems as i could not
 * really visualise how the game was supposed to work. The most challegining part was checkcollision because it was diifuclt to check to collision of abstract 
 * objects. I did not use the get size method because I found that i could just call for the length of the piece.I could not really fix the check collison problems 
 * because i did not fully understand how it worked. I learned how abstracts can be animated and how to use 
 * nested for loops and arrays to create object by converting them from boolean to whatever is desired.
 * 
 * 
 * Basit Balogun
 * 1.00
 */
public class Tetris extends GraphicsProgram{
    // initial size of the window 
    public static int 
    APPLICATION_WIDTH = 550,
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
    private GRect restart;

    /** the run method, draw the inital graphics */
    public void run() {
        drawGraphics();
        addKeyListeners();//allows the program to see key imputs 
        addMouseListeners();//allows the program to see mouse activities
    }

    /** draws the graphics */
    public void drawGraphics(){

        GRect frame = new GRect(N_COLUMN*SIZE+2*WALL_W,APPLICATION_HEIGHT);
        frame.setColor(Color.GRAY);
        frame.setFilled(true);
        add(frame,0,0);

        GRect screen = new GRect(N_COLUMN*SIZE,N_ROW*SIZE);
        screen.setColor(Color.WHITE);
        screen.setFilled(true);
        add(screen,WALL_W,WALL_W);
        drawGrid();

        score = new GLabel("Score = " + gameScore );
        score.setColor(Color.BLUE);
        score.setFont(new Font("SANS_SERIF", Font.BOLD,25));
        score.setVisible(true);
        add(score,frame.getWidth()/2 - score.getWidth()/2,850);

        GLabel title = new GLabel(" TETRIS GAME");
        title.setColor(Color.RED);
        title.setFont(new Font("Papyrus", Font.BOLD,25));
        title.setVisible(true);
        add(title, frame.getWidth()/2-title.getWidth()/2, 50);

        startGame = new GLabel("Click to start new game ");
        startGame.setColor(Color.BLUE);
        startGame.setFont(new Font("SANS_SERIF", Font.BOLD,20));
        startGame.setVisible(true);
        add(startGame,frame.getWidth()/2-startGame.getWidth()/2 ,getHeight()/2);

        //Restart Button 
        restart = new GRect(0,0,50,50);
        restart.setFillColor(Color.BLUE);
        add(restart);
    }

    /**draws a random tetris piece when called */
    public void addPiece(){
        String letter = "LIOTSZ";
        int lNumber = letter.length();
        
        //Use random generator to generate random piece
        //currentPiece = new Piece(letter.charAt(rand.nextInt(lNumber)),1 ,(N_COLUMN/2) , this);

        currentPiece = new Piece('O',1 ,(N_COLUMN/2) , this);

        
        new Thread(currentPiece).start();//animate the tetris piece
    }

    /** handles the mouse event when mouse is pressed */
    public void mousePressed(GPoint point) {
        boolean visible = startGame.isVisible();
        if (visible) {
            addPiece();//draws a tetris piece when the game is false
        }

        startGame.setVisible(false);

        
        //restart option currently used for trouble shooting 
        if(restart.getBounds().contains(point)){
            
            run();
        }
    }

    /** handles the key event when a key is pressed */
    public void keyPressed(KeyEvent e){
        // press up botton, rotate
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            currentPiece.rotate();
        }
        // press left botton, shift left
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            currentPiece.moveLeft(); 
        }
        // press right button, shift right
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            currentPiece.moveRight();
        }
        // press down button, add speed 
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            currentPiece.moveDown();

        }
    }

    /**draws the grid in which the tetris piece would be contained  */
    public void drawGrid(){
        grid =  new GRect[N_ROW][N_COLUMN];
        for(int  i = 0; i < N_ROW; i++){
            for(int j = 0; j < N_COLUMN; j++){
                grid[i][j]= new GRect(SIZE,SIZE);
                grid[i][j].setColor(Color.GRAY);
                grid[i][j].setFilled(true);
                grid[i][j].setFillColor(Color.GRAY);
                add(grid[i][j],WALL_W + j * SIZE,WALL_W + i * SIZE);
                if(i > 0 && i<N_ROW-1 && j > 0 && j < N_COLUMN -1) grid[i][j].setVisible(false);
            }

        }

    }

    /**Allows the piece to be seen to be moving my hiding and showing pieces while factors like row and column are being changed */
    public void show(Piece aPiece, boolean on){
        cubes = aPiece.getCubes();
        for(int i = 0;i<cubes.length;i++){
            for(int j = 0; j <cubes.length;j++){
                if(cubes[i][j] == true){
                    grid[i + aPiece.getRow()][j + aPiece.getColumn()].setVisible(on);
                    grid[i + aPiece.getRow()][j + aPiece.getColumn()].setFilled(true);
                    grid[i + aPiece.getRow()][j + aPiece.getColumn()].setColor(aPiece.getColor());
                    grid[i + aPiece.getRow()][j + aPiece.getColumn()].setFillColor(aPiece.getColor());
                }
            }
        }

    }

    /**Checks Collision between pieces and boundaries and between pieces */
    public boolean checkCollision(Piece p, int leftRight, int topBottom){
        boolean [][] c = p.getCubes();
        int size = c.length;
        for(int i = 0;i < size;i++){
            for(int j = 0; j < size;j++){
                int iTwo = i  + leftRight;
                int jTwo = j + topBottom;

                if(c[i][j] && (iTwo < 0|| iTwo >= size|| jTwo >= size|| jTwo< 0 || !c[iTwo][jTwo]) && grid[p.getRow() + iTwo][p.getColumn() + jTwo].isVisible()){
                    gameScore = gameScore + 1;//increase score by one after each collision
                    score.setLabel("Score = " + gameScore );
                    return true;
                }
            }
        }
        return false;

    }
    
    /**Checks Collision between wall */
    public boolean checkWall(Piece p, int leftRight, int topBottom){
        boolean [][] c = p.getCubes();
        int size = c.length;
        for(int i = 0;i < size;i++){
            for(int j = 0; j < size;j++){
                int iTwo = i  + leftRight;
                int jTwo = j + topBottom;

                if(c[i][j] && (iTwo < 0|| iTwo >= size|| jTwo >= size|| jTwo< 0 || !c[iTwo][jTwo]) && grid[p.getRow() + iTwo][p.getColumn() + jTwo].isVisible()){
                    gameScore = gameScore + 1;//increase score by one after each collision
                    score.setLabel("Score = " + gameScore );
                    return true;
                }
            }
        }
        return false;
    }
    
    

}
