package GameSource;
import Figures.*;
import java.util.*;

public class ControlErrores {

	public static boolean isEmpty(Figura[][] board,int y,int x){
		return board[y][x]==null;
	}
	
	public static boolean inBorder(int y,int x){
		return (y>=0 && y<8) && (x>=0 && x<8);
	}
	
	public static void printMoves(ArrayList<int[]> moves){
		for (int[] ar: moves){
			System.out.print("("+ar[0]+":"+ar[1]+")");
		}
	}
}
