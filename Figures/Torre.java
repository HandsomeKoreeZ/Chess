package Figures;
import GameSource.*;

public class Torre extends Figura {
    public Torre(char color, int y,int x){
    	this.setColor(color);
        this.setCoordinats(y, x);
    }

    @Override
    public String toString() {
        return this.getColor()+" Torre:"+ GameRules.dig2Str(this.getX())+(8-this.getY());
    }

    public void calcMooves(Figura[][] table) {
        int x, y; //coordinats
        int nextX, nextY; //multiplicador
        char color = this.getColor();

        //4 direcciones
        for (int i = 0; i < 4; i++) {
            x = this.getX();
            y = this.getY();
            //0 - arriba
            //1 - abajo
            //2 - derecha
            //3 - esquerda
            switch (i){
                case 0: {
                    nextX = 0;
                    nextY = -1;
                } break;
                case 1: {
                    nextX = 0;
                    nextY = 1;
                }break;
                case 2: {
                    nextX = 1;
                    nextY = 0;
                } break;
                default: {
                    nextX = -1;
                    nextY = 0;
                } break;
            }
            x += nextX;
            y += nextY;

            //añador campo como movimiento possible si el campo en tauler y esta buido
            while(ControlErrores.inBorder(y,x) && ControlErrores.isEmpty(table,y,x)){
                this.addMoure(y,x);
                x += nextX;
                y += nextY;
            }
            //añador campo como movimiento possible si el campo tiene peca de otro color
            if (ControlErrores.inBorder(y,x) && GameRules.isEnemy(color,table[y][x])){
                this.addMoure(y,x);
            }

        }
    }

}
