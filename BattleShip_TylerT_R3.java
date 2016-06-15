import java.util.Arrays; 
import java.util.Scanner;

public class BattleShip_TylerT_R3 {
	
	public static void main (String[] args){
		int COLUMN, ROW;
		int quit = 0;
		
		Scanner scanIntData = new Scanner(System.in);
		while (quit == 0){
			COLUMN = scanIntData.nextInt(); //Column is Y
			ROW = scanIntData.nextInt();
		
			System.out.println(hitDetect(ROW, COLUMN));
		}
	}

	private static int [][] LAYOUT = {//this is outside everything
			{0,0,0,0,0,3,0},
			{1,0,0,0,0,3,0},
			{1,0,0,0,0,3,0},
			{1,0,0,0,0,3,0},
			{1,0,0,0,0,0,0},
			{1,0,2,2,2,0,0},
			{0,0,0,0,0,0,0},
	};
	//variables
	private static int hitB1 = 0, hitB2 = 0, hitB3 = 0;
	
	public static Boolean[] hitDetect (int X, int Y){ //hit detect method.
		
		//boolean HIT = false; //starts out at false
		//boolean SUNK = false;
		
		Boolean[] STATUS = {false, false};
				//Hit detect and sink detect for boat with 5 spaces
				if (LAYOUT[X][Y] == 1){//Number used for this boat is 1
					STATUS[0] = true;
					hitB3 ++;
					}
					if (hitB3 == 5){
						STATUS[1] = true;
					}
				//Hit detect and sink detect for boat with 4 spaces
				if (LAYOUT[X][Y] == 3){ //Number used for this boat is 3
					STATUS[0] = true;
					hitB2 ++;
					}if (hitB2 == 4){
						STATUS[1] = true;
					}
				//Hit detect and sink detect for boat with 3 spaces
				if (LAYOUT[X][Y] == 2){//Number used for this boat is 2
					STATUS[0] = true;
					hitB1 ++;
					}if (hitB1 == 3){
						STATUS[1] = true;
					}
		System.out.println(STATUS[0]);
		//System.out.println(STATUS[1]);
		return STATUS;
	}
}

