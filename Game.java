import java.util.*;

class Snake{
	int head;
	int tail;
	public Snake(int starting, int ending){
		this.head=starting;
		this.tail=ending;
	} 
}

class Ladder{
	int startPoint;
	int endPoint;
	public Ladder(int starting,int ending){
		this.startPoint=starting;
		this.endPoint=ending;
	}
}

class Player{
	String name;
	int posn;
	public Player(String nameVar, int starting){
		this.name=nameVar;
		this.posn=starting;
	}
}

public class Game{
	public static void snakeInput(HashMap<Integer,Snake> snakeMap,int noSnake, Scanner sc){
		for(int i=0;i<noSnake;i++){
			int head = sc.nextInt();
			int tail = sc.nextInt();
			snakeMap.put(head,new Snake(head,tail));
		}
	}
	public static void ladderInput(HashMap<Integer,Ladder> ladderMap, int noLadder, Scanner sc){
		for(int i=0;i<noLadder;i++){
			int starting = sc.nextInt();
			int ending = sc.nextInt();
			ladderMap.put(starting,new Ladder(starting,ending));
		}
	}
	public static void playerInput(Player[] players, int noPlayer, Scanner sc){
		for(int i=0;i<noPlayer;i++){
			String playerName = sc.next();
			players[i]=new Player(playerName,0);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<Integer,Snake> snakeMap = new HashMap<Integer,Snake>();
		HashMap<Integer,Ladder> ladderMap = new HashMap<Integer,Ladder>();
		int noSnake = sc.nextInt();
		Random rand = new Random();
		snakeInput(snakeMap,noSnake,sc);
		int noLadder = sc.nextInt();
		ladderInput(ladderMap,noLadder,sc);
		int noPlayer = sc.nextInt();
		Player[] players = new Player[noPlayer];
		playerInput(players,noPlayer,sc);
		gameInitiator(snakeMap,ladderMap,players,noPlayer);
	}
	public static void gameInitiator(HashMap<Integer,Snake> snakeMap, HashMap<Integer,Ladder> ladderMap, Player[] players, int noPlayer){
		System.out.println("---------------Game Started------------------");
		Random rand = new Random();
		int turn = 0;
		while(true){
			Player pl = players[turn];
			int currPosn = pl.posn;
			int diceVal = rand.nextInt(0,6)+1;
			int nextPosn = pl.posn+diceVal;
			System.out.println(pl.posn+" "+nextPosn);
			if(nextPosn<=100){
				if(snakeMap.containsKey(nextPosn)){
					pl.posn=snakeMap.get(nextPosn).tail;
				}
				else if(ladderMap.containsKey(nextPosn)){
					pl.posn=ladderMap.get(nextPosn).endPoint;
				}else{
					pl.posn=nextPosn;
				}
				System.out.println(pl.name+" moved from "+currPosn+" to "+pl.posn);
			}else{
				System.out.println(pl.name+" is throwing out of bound");
			}
			if(pl.posn==100){
				System.out.println("---------********* HURRAY *********------------");
				System.out.println("               "+pl.name+" "+"WON            ");
				System.out.println("----------******** CONGRATULATIONS ********------------");
				break;
			}
			turn++;
			if(turn==noPlayer){
				turn=0;
			}
		}
	}
}