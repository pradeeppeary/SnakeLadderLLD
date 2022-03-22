import java.util.*;
import java.io.*;
class Player{
    String name;
    String type;
    public Player(String name, String type){
        this.name=name;
        this.type=type;
    }
}
public class Game {
    public static void main(String[] args) {
        String[][] grid = new String[9][9];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                grid[i][j]="-";
            }
        }
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String[] arr1 = s1.split(" ");
        String[] arr2 = s2.split(" ");
        Player p1 = new Player(arr1[1], arr1[0]);
        Player p2 = new Player(arr2[1], arr2[0]);
        gameInitializer(grid, p1, p2,sc);
    }
    public static void printBoard(String[][] grid){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void gameInitializer(String[][] grid,Player p1, Player p2, Scanner sc){
        System.out.println("Game Started ---- Initial Grid state is below");
        printBoard(grid);
        Player turnP = p1;
        if(!p1.type.equals("X")){
            turnP=p2;
        }

        while(true){
            String inp = sc.nextLine();
            int x=0;
            int y=0;
            if(inp.equals("exit")){
                System.out.println("Game Over");
                break;
            }else{
                x = Integer.parseInt(inp.substring(0,1));
                x--;
                y = Integer.parseInt(inp.substring(2,3));
                y--;
            }
            boolean valid = makeMove(grid, x, y, turnP.type);
            if(valid){
                if(checkConfig(grid)){
                    System.out.println("Hurray "+turnP.name+" WON");
                    break;
                }else{
                    if(validGrid(grid)){   
                        printBoard(grid); 
                        if(turnP==p1){
                            turnP=p2;
                        }else{
                            turnP=p1;
                        }
                    }else{
                        System.out.println("Board is full --- Match is tie");
                        break;
                    }
                }

            }else{
                System.out.println("Invalid Move");
            }
        }

    }
    public static boolean validGrid(String[][] grid){
        boolean boo=false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(grid[i][j].equals("-")){
                    boo=true;
                    break;
                }
            }
            if(boo){
                return boo;
            }
        }
        return boo;
    }
    public static boolean checkConfig(String[][] grid){
        if(grid[0][0].equals("X") && grid[0][1].equals("X") && grid[0][2].equals("X") || grid[0][0].equals("O") && grid[0][1].equals("O") && grid[0][2].equals("O")){
            return true;
        }else if(grid[1][0].equals("X") && grid[1][1].equals("X") && grid[1][2].equals("X") || grid[1][0].equals("O") && grid[1][1].equals("O") && grid[1][2].equals("O")){
            return true;
        }else if(grid[2][0].equals("X") && grid[2][1].equals("X") && grid[2][2].equals("X") || grid[2][0].equals("O") && grid[2][1].equals("O") && grid[2][2].equals("O")){
            return true;
        }else if(grid[0][0].equals("X") && grid[1][0].equals("X") && grid[2][0].equals("X") || grid[0][0].equals("O") && grid[1][0].equals("O") && grid[2][0].equals("O")){
            return true;
        }else if(grid[0][1].equals("X") && grid[1][1].equals("X") && grid[2][1].equals("X") || grid[0][0].equals("O") && grid[0][1].equals("O") && grid[0][2].equals("O")){
            return true;
        }else if(grid[0][2].equals("X") && grid[1][2].equals("X") && grid[2][2].equals("X") || grid[0][2].equals("O") && grid[1][2].equals("O") && grid[2][2].equals("O")){
            return true;
        }else if(grid[0][0].equals("X") && grid[1][1].equals("X") && grid[2][2].equals("X") || grid[0][0].equals("O") && grid[1][1].equals("O") && grid[2][2].equals("O")){
            return true;
        }else if(grid[0][2].equals("X") && grid[1][1].equals("X") && grid[2][0].equals("X") || grid[0][2].equals("O") && grid[1][1].equals("O") && grid[2][0].equals("O")){
            return true;
        }else{
            return false;
        }
    }
    public static boolean makeMove(String[][] grid,int x,int y,String type){
        if(grid[x][y].equals("-")){
            grid[x][y]=type;
            return true;
        }else{
            return false;
        }
    }

}
