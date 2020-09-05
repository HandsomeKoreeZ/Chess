package Figures;

import java.util.ArrayList;
import GameSource.*;

public class Figura {
	private char color;
	private int x,y;
	private ArrayList<int[]> mouresPossible = new ArrayList<>();
	
	//****************GET/SET*********************************************
	public char getColor() {
		return color;
	}
	public void setColor(char color) {
		this.color = color;
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int x){this.x=x;}
	public void setY(int y){this.y=y;}
	public void setCoordinats(int y,int x) {
		if(ControlErrores.inBorder(y,x)){
			this.x = x;
			this.y = y;
		}
	}

	public ArrayList<int[]> getMouresPossible() {
		return mouresPossible;
	}
	public void setMouresPossible(ArrayList<int[]> mouresPossible){
		this.mouresPossible = mouresPossible;
	}
	
	//****************METODES*********************************************
	public boolean possible(int y1, int x1){
		//retorna true si coordinats en la llista de moures possibles
		int x2,y2;
		for(int[] comparador: this.mouresPossible){
			y2=comparador[0];
			x2=comparador[1];
			if (x1==x2 && y1==y2) {
				return true;
			}
		}
		return false;
	}

    public void addMoure(int y, int x){
		this.mouresPossible.add(new int[]{y,x});
	}

	public void borrarMoures(){
		this.mouresPossible.clear();
	}



}
