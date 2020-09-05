package Figures;

import GameSource.*;

public class Dama extends Figura{
    public Dama(char color, int y,int x){
        if(color=='B') this.setColor('B');
        else this.setColor('N');
        this.setCoordinats(y, x);
    }

    @Override
    public String toString() {
        return this.getColor()+" Dama:"+ GameRules.dig2Str(this.getX())+(8-this.getY());    }

    public void calcMooves(Figura[][] table) {
        int x, y; //coordinats
        int nextX, nextY; //multiplicador
        char color = this.getColor();

        //4 direcciones
        for (int i = 0; i < 8; i++) {
            x = this.getX();
            y = this.getY();
            //0 - arriba
            //1 - abajo
            //2 - derecha
            //3 - esquerda
            //4 - arriba derecha
            //5 - abajo derecha
            //6 - abajo esquerda
            //7 - arriba esquerda
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
                case 3: {
                    nextX = -1;
                    nextY = 0;
                } break;
                case 4: {
                    nextX = 1;
                    nextY = -1;
                } break;
                case 5: {
                    nextX = 1;
                    nextY = 1;
                }break;
                case 6: {
                    nextX = -1;
                    nextY = 1;
                } break;
                default: {
                    nextX = -1;
                    nextY = -1;
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
