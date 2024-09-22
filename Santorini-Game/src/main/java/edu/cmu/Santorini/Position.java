package edu.cmu.Santorini;

public class Position {

    private int positionX;
    private int positionY;
    private static final int BOUNDARY = 4; // since Santorini plays on 5*5 grid board, grid index will range from 0-4
    
    private static final int UPLEFT = 0;
    private static final int UP = 1;
    private static final int UPRIGHT = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;
    private static final int DOWNLEFT = 5;
    private static final int DOWN = 6;
    private static final int DOWNRIGHT = 7;


    /**
     * Constructor sets the initial position of an object
     * @param posX position of obj on x axis
     * @param posY position of obj on y axis
     */
    public Position(int posX, int posY) throws Exception{
        if (!positionInBounds(posX)){
            throw new Exception("Please input valid x value in range of 0-4");
        }
        if (!positionInBounds(posY)){
            throw new Exception("Please input valid y value in range of 0-4");
        }
        this.positionX = posX;
        this.positionY = posY;
        
    }

    //Default constructor
    public Position(){

    }

    /**
     * 
     * @return a list with horizontal and vertical position in index 0 and 1 
     */
    public int[] getPosition(){
        return new int[]{this.positionX, this.positionY};
     }
     
    /**
     * Modify the obj position based on user argument
     * @param posX position of obj to be set on x axis
     * @param posY position of obj to be set on y axis
     * @return the method returns false if position is set out of bounds, otherwise, it returns true
     */
    public boolean setPosition(int posX, int posY){
        if (!positionInBounds(posX) || !positionInBounds(posY)){
            return false;
        }
        this.positionX = posX;
        this.positionY = posY;
        return true;
    }

    /**
     * The method analyzes the position on board and returns whether it is within boundary
     * @param pos one of the x or y axis argument
     * @return return a boolean suggesting if the location specified in argument is within bound
     */
    public boolean positionInBounds(int pos){
        if (0 <= pos && pos <= BOUNDARY){
            return true;
        }
        return false;
    }

    public boolean positionInBounds(){
        if (positionInBounds(positionX) && positionInBounds(positionY)){
            return true;
        }
        return false;
    }

    /**
     * The method compares two positions and return whether they are identical
     * @param position position of obj to compare
     * @return true and false depending on the comparison result
     */
    public boolean samePosition(Position position){
        if (this.positionX == position.getPosition()[0]
            && this.positionY == position.getPosition()[1]){
                return true;
            }
        return false;
    }

    /**
     * The method compares two positions and return a boolean to indicate whether
     * they are one-grid move to the selected worker orthogonally or diagonally
     * @param position position of the obj to compare
     * @return false if two positions are beyond one grid move distance or the same
     */
    public boolean positionInProximity(Position position){

        if (this.samePosition(position)){
                return false;
        }

        int delX = Math.abs(this.positionX - position.getPosition()[0]);
        int delY = Math.abs(this.positionY - position.getPosition()[1]);

        return (delX <= 1 && delY <= 1);
    }
    
    /**
     * Takes an integer ranging from 0-7 inclusive and output a new position based on the current position
     * @param direction number from 0-7 which indcates the direction of movement as shown below:
     *                  0 1 2
     *                  3   4
     *                  5 6 7
     * @return the new position 
     */
    public Position transformer(int direction){
        int[] pos = this.getPosition();
        switch(direction){
            case UPLEFT:   pos[0]--; pos[1]--; break;
            case UP:       pos[1]--; break;
            case UPRIGHT:  pos[0]++; pos[1]--; break;
            case LEFT:     pos[0]--; break;
            case RIGHT:    pos[0]++; break;
            case DOWNLEFT: pos[0]--; pos[1]++; break;
            case DOWN:     pos[1]++; break;
            case DOWNRIGHT:pos[0]++; pos[1]++; break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        try{
            return new Position(pos[0],pos[1]);
        }catch(Exception e){
            System.err.println("Transformed location out of bounds");
            return null;
        }
        
    }
}
