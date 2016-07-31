/* Joshua Villasenor
   Masc0431
*/
import data_structures.*;
import java.applet.Applet;

public class MazeSolver extends Applet{
    MazeGrid maze;
    private int size;
    Stack<GridCell> stackList = new Stack<GridCell>();
    Queue<GridCell> queueList = new Queue<GridCell>();
    GridCell terminal_cell;
    
    
    public MazeSolver(int dimension){
        maze = new MazeGrid(this, dimension);
        size = dimension;
        terminal_cell = maze.getCell(dimension-1, dimension-1);
    }
    
    public void mark(){
        int distance = 1, tempDistance;
        GridCell tempGridCell, tempGridCell2;
        queueList.enqueue(maze.getCell(0,0));
        tempGridCell = maze.getCell(0,0);
        tempGridCell.setDistance(0);
       
        while(!queueList.isEmpty()){
            tempGridCell = tempGridCell2 = queueList.dequeue();
            if(maze.isValidMove(maze.getCell(tempGridCell.getX()+1,tempGridCell.getY()))){
                tempGridCell = maze.getCell(tempGridCell.getX()+1,tempGridCell.getY());
                if(!tempGridCell.wasVisited()){
                    tempGridCell.setDistance(tempGridCell2.getDistance() + 1);
                    queueList.enqueue(tempGridCell);
                    maze.markDistance(tempGridCell);
                }
                tempDistance = distance;
                tempGridCell = tempGridCell2;
            }
            if(maze.isValidMove(maze.getCell(tempGridCell.getX(),tempGridCell.getY()+1))){
                tempGridCell = maze.getCell(tempGridCell.getX(),tempGridCell.getY()+1);
                if(!tempGridCell.wasVisited()){
                     tempGridCell.setDistance(tempGridCell2.getDistance() + 1);
                     queueList.enqueue(tempGridCell);
                     maze.markDistance(tempGridCell);
                }
                tempDistance = distance;
                tempGridCell = tempGridCell2;
            }
            if(maze.isValidMove(maze.getCell(tempGridCell.getX()-1,tempGridCell.getY()))){
                tempGridCell = maze.getCell(tempGridCell.getX()-1,tempGridCell.getY());
                if(!tempGridCell.wasVisited()){
                     tempGridCell.setDistance(tempGridCell2.getDistance() + 1);
                     queueList.enqueue(tempGridCell);
                     maze.markDistance(tempGridCell);
                }
                tempDistance = distance;
                tempGridCell = tempGridCell2;
            }
            if(maze.isValidMove(maze.getCell(tempGridCell.getX(),tempGridCell.getY()-1))){
                tempGridCell = maze.getCell(tempGridCell.getX(),tempGridCell.getY()-1);
                if(!tempGridCell.wasVisited() ){
                     tempGridCell.setDistance(tempGridCell2.getDistance() + 1);
                     queueList.enqueue(tempGridCell);
                     maze.markDistance(tempGridCell);
                }
                tempDistance = distance;
                tempGridCell = tempGridCell2;
            }
        }
    }
    
    public boolean move(){
        int dTerminalCell = terminal_cell.getDistance();
        int d1 =size-1,d2 = size-1;
        GridCell tempGridCell;
        
        if(dTerminalCell == -1) return false;
        
        while(dTerminalCell != 0){
            if(maze.isValidMove(maze.getCell(d1-1,d2))){
                tempGridCell = maze.getCell(d1-1,d2);
                if (tempGridCell.getDistance() ==  dTerminalCell-1){
                    stackList.push(tempGridCell);
                    dTerminalCell--;
                    d1--;
                }
        	}
            if(maze.isValidMove(maze.getCell(d1, d2-1))){
                tempGridCell = maze.getCell(d1, d2-1);
                if(tempGridCell.getDistance() == dTerminalCell-1){
                    stackList.push(tempGridCell);
                    dTerminalCell--;
                    d2--;
                }
                
            }
            if(maze.isValidMove(maze.getCell(d1+1, d2))){
                tempGridCell = maze.getCell(d1+1, d2);
                if(tempGridCell.getDistance() == dTerminalCell-1){
                    stackList.push(tempGridCell);
                    dTerminalCell--;
                    d1++;
                }
            }
            if(maze.isValidMove(maze.getCell(d1, d2+1))){
                tempGridCell = maze.getCell(d1, d2+1);
                if(tempGridCell.getDistance() == dTerminalCell-1){
                    stackList.push(tempGridCell);
                    dTerminalCell--;
                    d2++;
                }
            }
        }

        while(!stackList.isEmpty())
            maze.markMove(stackList.pop());
        
        return true;
    }
    
    public void reset(){
        queueList.makeEmpty();
    }
    
    public static void main(String[] args){
        new MazeSolver(50);
        
    }
}