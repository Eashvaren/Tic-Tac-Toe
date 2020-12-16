import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class ticTacToe{

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        boolean noWinner;
        
        char[][] gameBoard = {
            {' ','|',' ','|',' '}, 
            {'-','+','-','+','-'}, 
            {' ','|',' ','|',' '}, 
            {'-','+','-','+','-'}, 
            {' ','|',' ','|',' '}};
        
            printGameBoard(gameBoard);    

        while(noWinner = true){
            System.out.print("Enter your placement (1-9) :");

            int playerPos = input.nextInt();
            while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
                System.out.println("Position is taken! Enter a correct position.");
                playerPos = input.nextInt();
            }

            placePiece(gameBoard, playerPos, "Player");

            String result = checkWinner(gameBoard,noWinner);
            if(result.length() > 0){
                System.out.println(result);    
            }
            System.out.println(result);  

            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
                cpuPos = rand.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPos, "CPU");
            printGameBoard(gameBoard);

            result = checkWinner(gameBoard,noWinner);
            if(result.length() > 0){
                System.out.println(result);    
            }
            System.out.println(result);
            
        }
    } 

    public static void printGameBoard(char[][] gameBoard){
        
        System.out.println("Tic-Tac-Toe");
        System.out.println();

        for(char[] row : gameBoard) {
            for(char column : row) {
                System.out.print(column);
            }
            System.out.println();
        }
    }

    public static void placePiece (char[][] gameBoard, int position, String user){

        char symbol = ' ';        
        if(user.equals("Player")){
            symbol = 'X';
            playerPositions.add(position);}
        else if(user.equals("CPU")){
            symbol = 'O';
            cpuPositions.add(position);}
        
        switch(position){
            case 1:
                gameBoard[0][0] = symbol; break;
            case 2:
                gameBoard[0][2] = symbol; break;
            case 3:
                gameBoard[0][4] = symbol; break;
            case 4:
                gameBoard[2][0] = symbol; break;
            case 5:
                gameBoard[2][2] = symbol; break;
            case 6:
                gameBoard[2][4] = symbol; break;
            case 7:
                gameBoard[4][0] = symbol; break;
            case 8:
                gameBoard[4][2] = symbol; break;
            case 9:
                gameBoard[4][4] = symbol; break;
            default:
                break;
                
        }
    }

    public static String checkWinner(char[][] gameBoard, boolean noWinner){
        List topRow     = Arrays.asList(1,2,3);
        List midRow     = Arrays.asList(4,5,6);
        List botRow     = Arrays.asList(7,8,9);
        List leftCol    = Arrays.asList(1,4,7);
        List midCol     = Arrays.asList(2,5,8);
        List rightCol   = Arrays.asList(3,6,9);
        List crossRight = Arrays.asList(1,5,9);
        List crossLeft  = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(crossRight);
        winning.add(crossLeft);

        for(List all: winning){
            if(playerPositions.containsAll(all)){
                noWinner = false;
                return"Congrats! You won.";
            } else if(cpuPositions.containsAll(all)){
                noWinner = false;
                return"Opps! You lost.";
            }else if (playerPositions.size() + cpuPositions.size() == 9){
                noWinner = false;
                printGameBoard(gameBoard);
                return "Close call ! It's a tie.";
            }            
        }
        return "";
    }

    
}

