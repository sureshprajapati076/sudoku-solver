package sudoku;
import java.util.Scanner;

public class Sudoku {
    public static int TABLE_SIZE=9;
    public static void main(String []args){

        /*
ENTER 1th row
    68 3
ENTER 2th row
19     7
ENTER 3th row
8 31  2 5
ENTER 4th row
4  851 6
ENTER 5th row
78  2  54
ENTER 6th row
    7 8
ENTER 7th row
 1   5  7
ENTER 8th row
  4
ENTER 9th row
 5  3 1

  0  0  0  0  6  8  0  3  0
  1  9  0  0  0  0  0  7  0
  8  0  3  1  0  0  2  0  5
  4  0  0  8  5  1  0  6  0
  7  8  0  0  2  0  0  5  4
  0  0  0  0  7  0  8  0  0
  0  1  0  0  0  5  0  0  7
  0  0  4  0  0  0  0  0  0
  0  5  0  0  3  0  1  0  0

  2  4  7  5  6  8  9  3  1
  1  9  5  3  4  2  6  7  8
  8  6  3  1  9  7  2  4  5
  4  3  9  8  5  1  7  6  2
  7  8  1  9  2  6  3  5  4
  5  2  6  4  7  3  8  1  9
  3  1  2  6  8  5  4  9  7
  6  7  4  2  1  9  5  8  3
  9  5  8  7  3  4  1  2  6
         */

//        int puzzle[][]={
//                {5,0,0,0,7,0,0,0,0},
//                {0,0,0,0,6,4,5,3,0},
//                {0,4,6,2,0,0,8,0,0},
//                {8,0,5,0,0,0,0,2,6},
//                {9,2,0,6,0,3,0,4,8},
//                {6,1,0,0,0,0,3,0,5},
//                {0,0,1,0,0,6,9,8,0},
//                {0,7,9,8,4,0,0,0,0},
//                {0,0,0,0,9,0,0,0,2}
//        };
        int puzzle[][]=new int[TABLE_SIZE][TABLE_SIZE];

        Scanner myObj = new Scanner(System.in);
        for(int i=0;i<9;i++) {
            System.out.println("ENTER "+(i+1)+"th row");
            String rowInput = myObj.nextLine();
            if(rowInput.length()!=9){
                System.out.println("Re-enter Row with 9 numbers");
                i--;
                continue;
            }
            char [] row=rowInput.toCharArray();
            for(int j=0;j<9;j++){

                if(row[j]==' '){
                    puzzle[i][j]=0;
                }else {
                    puzzle[i][j] = Integer.parseInt(String.valueOf(row[j]));
                }
            }

        }



        printPuzzle(puzzle);

        //System.out.println(checkIfNumFits(puzzle,5,2,4));

        solvePuzzle(puzzle);

        printPuzzle(puzzle);


    }

    private static boolean solvePuzzle(int[][] puzzle) {
        for(int row=0;row<TABLE_SIZE;row++){
            for(int col=0;col<TABLE_SIZE;col++){
                if(puzzle[row][col]==0){
                    for(int numTocheck=1;numTocheck<=9;numTocheck++){
                        if(checkIfNumFits(puzzle,row,col,numTocheck)){
                            puzzle[row][col]=numTocheck;
                            if(solvePuzzle(puzzle)){
                                return true;
                            }else{
                                puzzle[row][col]=0;
                            }
                        }
                    }
                    return false;
                }
            }
        }

        return true;




    }

    public static boolean checkRow(int[][]puzzle,int row,int numToCheck){
        for(int i=0;i<TABLE_SIZE;i++){
            if(puzzle[row][i]==numToCheck){
                return false;
            }
        }
        return true;
    }

    public static boolean checkCol(int[][]puzzle,int col,int numToCheck){
        for(int i=0;i<TABLE_SIZE;i++){
            if(puzzle[i][col]==numToCheck){
                return false;
            }
        }
        return true;
    }

    public static boolean checkSmallSquare(int[][]puzzle,int row,int col,int numToCheck){
        int boxRow=row-row%3;
        int boxCol=col-col%3;
        for(int i=boxRow;i<boxRow+3;i++){
            for(int j=boxCol;j<boxCol+3;j++){
                if(puzzle[i][j]==numToCheck){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkIfNumFits(int[][] puzzle,int row,int col,int numTOCheck){
        return checkRow(puzzle,row,numTOCheck)&&checkCol(puzzle,col,numTOCheck)&&checkSmallSquare(puzzle,row,col,numTOCheck);
    }

    public static void printPuzzle(int [][] puzzle){
        for (int row=0;row<TABLE_SIZE;row++){
            for(int col=0;col< TABLE_SIZE;col++){
                System.out.printf("%3d",puzzle[row][col]);
            }
            System.out.println();
        }
    }
}
