
import java.util.Scanner;
/**
 *
 * @author Neethan
 */

public class TicTacToe {

   private int  nRows; // default value of 3
   private int nColumns;// default value of 3    
   private int numToWin;// default value of 3
   private char grid [][];// either ' ' or 'X' or 'O'
   private char turn;// alternate between 'X' or 'O'
   private TicTacToeEnum gameState = TicTacToeEnum.IN_PROGRESS;// Initially IN_PROGRESS
   private int nMarks ;// increment after each turn
   // used to detect DRAW
   
   /**
    * Single argument constructor used to initialize the default values, The method
    * takes a character input.
    * @param initialTurn 
    */
   public TicTacToe(char initialTurn){
       this.nRows =3;
       this.nColumns = 3;
       this.numToWin = 3;
       this.turn = turn;
       this.nMarks = 0;
       reset(initialTurn);
   }
   //TicTacToe built with default values
   /** 
    * Four argument constructor that takes in 3 integer inputs and a character input
    * Used to create a grid larger then a default grid.
    * @param nRows
    * @param nColumns
    * @param numToWin
    * @param initialTurn 
    */
   public TicTacToe(int nRows,int nColumns,int numToWin,char initialTurn){
       if ( nRows < 2|| nColumns < 2) 
            throw new IllegalArgumentException("Please enter valid values for your game"); 
        
       if(numToWin > nRows || numToWin > nColumns)
           throw new IllegalArgumentException("Please enter valid values for your game");
       
       this.nRows = nRows;
       this.nColumns = nColumns;
       this.numToWin=numToWin;
       this.turn = initialTurn;
       reset(initialTurn);
   }
   //TicTacToe built with given rows, columns, the number to win and the inital turn
   // Checks the desried rows and columns are in the acceptable range
   
   /**
    * The reset method sets all constructors back to there default values.
    * @param initialTurn 
    */
   public void reset(char initialTurn){
       
       this.turn = initialTurn;
       this.nMarks = 0;
       this.grid = new char [this.nRows][this.nColumns];
       
       for (int i=0;i< nRows;i++){
           for(int j=0;j<nColumns;j++){
               this.grid[i][j] = ' ';
           }
       }
   }
   // resets the players turn, all the spaces to blank and the mark is set to 0
   
   /**
    *The method getTurn in TicTacToe class is used to receive either players turn.
    *@return 'X' or 'O'
    */
   public int getTurn(){ 
       return this.turn;
   }
   // returns whoever's turn it is; either O or X
   /**
    * The TicTacToeEnum class method getGameState is used to say who won the game.
    * 
    * 
    * @return IN_PROGRESS,O_WIN,X_WIN,DRAW
    */
   public TicTacToeEnum getGameState(){
       return this.gameState;
   }
   // returns the state of the game, from the four options in TicTacToeEnum
   
   /** 
    * The method charToEnum() is a utility method that converts a player
    * either 'X' or 'O' into the corresponding value in the ENUM.
    * @param player
    * @return A character representation of the ENUM value.
    */
   private TicTacToeEnum charToEnum (char player){
      TicTacToeEnum[] decision = {TicTacToeEnum.IN_PROGRESS,TicTacToeEnum.X_WON,TicTacToeEnum.O_WON,TicTacToeEnum.DRAW};
      if(player == 'X'){
          this.gameState = decision[1];
          return this.getGameState();
      }
      else if(player == 'O'){
          this.gameState = decision[2];
          return this.getGameState();
      }
      else if(player == 'D'){
          this.gameState = decision[3];
          return this.getGameState();
      }else{
          this.gameState = decision[0];
          return this.gameState;
      }
      
   
   }
   // gamte state is set based on result by findWinner()
   
   /**
    * The public method takeTurn() is used to put appropriate character in the
    * requested grid space and to find the winner of the game
    * @param row
    * @param column
    * @throws IllegalArgumentException the value 
    * inputed is out of bounds or is already taken
    * @return The game state if a winner is not found. If winner is found it 
    */
  public TicTacToeEnum takeTurn (int row, int column){
      if (column >= this.nColumns || row>= this.nRows){
          throw new IllegalArgumentException ("The spot is out of bound");
      }
      if(grid[row][column] != ' '){
          throw new IllegalArgumentException ("The spot is filled");
      }
      // checks if the input is out of the grid
      // checks if the input is in a space that is filled
      grid[row][column] = this.turn;
      // sets the grid space with either O or X
      this.nMarks = nMarks + 1;
      // keeps track of how many turns have happened
      if(this.turn == 'O'){
          this.turn = 'X';
      }
      else{
          this.turn ='O';
      }
      // switches the turn from O to X or X to O
      if(this.numToWin <= this.nMarks){
          return findWinner();
      }
      // does not call findWinner() unless a couple turns have passed by
      return this.gameState;
     //returns the game state calculated after that turn
  }
  
  /**
   * The private method findWinner() is used to determine the winner
   * of the game. This is done buy checking the horizontal 
   * axis of the grid and vertical axis of the grid.
   * @return IN_PROGRESS,X_WON,O_WON,DRAW
   */
  private TicTacToeEnum findWinner(){
        char[] leftToright = new char[this.nRows];
        char[] topTobot = new char[this.nColumns];
        char win = ' ';
        int ltrCount;
        int ttbCount;
    // created an array to store values being check from across and below
    // created a winner character to store the player win either X or O
    // created a count to check the value in the columns and rows if there the same
    // the for statements check all the spaces in the entire grid
        for(int i = 0; i < this.nColumns; i++){
            ltrCount = 1;
            ttbCount= 1;
            for(int j = 0; j < this.nRows; j++){
                leftToright[j] = this.grid[i][j];
                topTobot[j]= this.grid[j][i];
            }
           // the for look abve stores a value ofr the row and column
            for(int k = 1; k < this.numToWin; k++){
                if(leftToright[0] == leftToright[k]){
                    ltrCount++;
                }
                if(topTobot[0] == topTobot[k]){
                    ttbCount++;
                }
            }
           // increases the count if the square in the column or row matches the array first index
            if(ltrCount == this.numToWin){
                    win = leftToright[0];
            }
            if(ttbCount == this.numToWin){
                    win = topTobot[0];
            }  
            // if the counter value matches the number needed to win, the winner is set to 
            // the value being checked
            if (win == 'X'){
            return charToEnum('X');
        }
        if (win == 'O'){
            return charToEnum('O');
        }if(this.nMarks == this.nColumns * this.nRows){
            return charToEnum('D');
        }
        else{
           return charToEnum('P');
        }
        
     }
        // if winner is the X player, the winner will be restured
        // if all squres filled, then a tie game is returned
        // if neither of these happen then the IN_PROGRESS is returned
        return charToEnum('P');
  }
  
   @Override
   public String toString(){
       String space = "";
       for (int i =0;i<nRows;i++){
           for(int j=0;j<nColumns;j++){
               space = space + grid[i][j] + " | ";
           }
           space = space + "\n";
   }
       return space;
}
   // return the grid
   
  
      public static void main(String args[]) {
        TicTacToe game = new TicTacToe('X');
        Scanner scanner = new Scanner(System.in);

        do { 
            System.out.println(game.toString());
            System.out.println(game.getTurn() + 
                ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            scanner.nextLine();
            game.takeTurn(row, column);
            
        } while (game.getGameState() == TicTacToeEnum.IN_PROGRESS);
        System.out.println(game.getGameState());
       
    }


}
