package Figures;
import GameSource.*;
public class Peo extends Figura{

	public Peo(char color, int y,int x){
		this.setColor(color);
		this.setCoordinats(y, x);
	}
	
	@Override
	public String toString() {
		return this.getColor()+" Peo:"+ GameRules.dig2Str(this.getX())+(8-this.getY());
	}

	public void calcMooves(Figura[][] table){
		int x=this.getX();
		int y=this.getY();
		char me = this.getColor();
		int peoStart = (me=='B')?6:1;
		int direccion = (me=='B')?-1:1; //diversos peons se mueve al direccion contrario

		if(ControlErrores.inBorder(y+direccion,x) && ControlErrores.isEmpty(table,y+direccion,x)){
			//similar
			this.addMoure(y+direccion,x);
			//salta 2
			if (y==peoStart && ControlErrores.isEmpty(table,y+(direccion*2),x)){
				this.addMoure(y+(direccion*2),x);
			}
		}
		//matar
		//derecha
		if(ControlErrores.inBorder(y+direccion,x+1) && !ControlErrores.isEmpty(table,y+direccion,x+1) && GameRules.isEnemy(me,table[y+direccion][x+1])){
			this.addMoure(y+direccion,x+1);
		}
		//esquierda
		if(ControlErrores.inBorder(y+direccion,x-1) &&  !ControlErrores.isEmpty(table,y+direccion,x-1) && GameRules.isEnemy(me,table[y+direccion][x-1])){
			this.addMoure(y+direccion,x-1);
		}
	}





			


}
	

