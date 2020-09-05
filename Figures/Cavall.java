package Figures;

import GameSource.*;

public class Cavall extends Figura {
    public Cavall(char color, int y,int x){
    	this.setColor(color);
        this.setCoordinats(y, x);
    }

    @Override
    public String toString() {
        return this.getColor()+" Cavall:"+ GameRules.dig2Str(this.getX())+(8-this.getY());
    }

    public void calcMooves(Figura[][] table) {
        int x = this.getX();
        int y = this.getY(); //coordinats
        char color = this.getColor();

        //8 direcciones
        //arriba
        if(ControlErrores.inBorder(y-2,x-1) && (ControlErrores.isEmpty(table,y-2,x-1) || GameRules.isEnemy(color,table[y-2][x-1]))) this.addMoure(y-2,x-1);
        if(ControlErrores.inBorder(y-2,x+1) && (ControlErrores.isEmpty(table,y-2,x+1) || GameRules.isEnemy(color,table[y-2][x+1]))) this.addMoure(y-2,x+1);
        //abajo
        if(ControlErrores.inBorder(y+2,x-1) && (ControlErrores.isEmpty(table,y+2,x-1) || GameRules.isEnemy(color,table[y+2][x-1]))) this.addMoure(y+2,x-1);
        if(ControlErrores.inBorder(y+2,x+1) && (ControlErrores.isEmpty(table,y+2,x+1) || GameRules.isEnemy(color,table[y+2][x+1]))) this.addMoure(y+2,x+1);
        //derecha
        if(ControlErrores.inBorder(y-1,x+2) && (ControlErrores.isEmpty(table,y-1,x+2) || GameRules.isEnemy(color,table[y-1][x+2]))) this.addMoure(y-1,x+2);
        if(ControlErrores.inBorder(y+1,x+2) && (ControlErrores.isEmpty(table,y+1,x+2) || GameRules.isEnemy(color,table[y+1][x+2]))) this.addMoure(y+1,x+2);
        //esquierda
        if(ControlErrores.inBorder(y-1,x-2) && (ControlErrores.isEmpty(table,y-1,x-2) || GameRules.isEnemy(color,table[y-1][x-2]))) this.addMoure(y-1,x-2);
        if(ControlErrores.inBorder(y+1,x-2) && (ControlErrores.isEmpty(table,y+1,x-2) || GameRules.isEnemy(color,table[y+1][x-2]))) this.addMoure(y+1,x-2);
    }
}
