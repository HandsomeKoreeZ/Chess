package Figures;

import GameSource.*;

public class Rey extends Figura{

	//**************CONSTRUCTOR***************
	public Rey(char color, int y, int x){
		this.setColor(color);
		this.setCoordinats(y, x);
	}
	
	@Override
	public String toString() {
		return this.getColor()+" Rey:"+ GameSource.GameRules.dig2Str(this.getX())+(8-this.getY());
	}

	//***************METODES*********************
	public void calcMooves(Figura[][] table) {
		int x = this.getX();
		int y = this.getY(); //coordinats
		char color = this.getColor();
		int modY, modX;

		//poner todos campos alrededor del Rey
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				modY = y+i;
				modX = x+j;
				if(i==0 && j==0){
					continue; //si es campo su mismo - no hacer nada
				}else if(ControlErrores.inBorder(modY,modX)){
					if((ControlErrores.isEmpty(table,modY,modX)) || GameRules.isEnemy(color,table[modY][modX])){
						this.addMoure(modY,modX);
					}
				}
			}
		}
	}

	public boolean provocaJaque(Figura[][] table){
		boolean control = false;

		return control;
	}
	
	

	
}
