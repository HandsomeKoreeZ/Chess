package GameSource;
import java.util.ArrayList;
import Figures.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Vector;

/**
 * Created by KoreeZ on 22.04.2017.
 */
public class GameRules {

    public static Figures.Figura[][] initGame(){
        Figura[][] newGame = new Figura[8][8];

        //PEO
        for(int i=0;i<8;i++){
            newGame[1][i]=new Peo('N',1,i);
            newGame[6][i]=new Peo('B',6,i);
        }
        //TORRE
        newGame[0][0]= new Torre('N',0,0); newGame[0][7]= new Torre('N',0,7);
        newGame[7][0]= new Torre('B',7,0); newGame[7][7]= new Torre('B',7,7);
        //CAVALL
        newGame[0][1] = new Cavall('N',0,1); newGame[0][6] = new Cavall('N',0,6);
        newGame[7][1] = new Cavall('B',7,1); newGame[7][6] = new Cavall('B',7,6);
        //ALFIL
        newGame[0][2] = new Alfil('N',0,2); newGame[0][5] = new Alfil('N',0,5);
        newGame[7][2] = new Alfil('B',7,2); newGame[7][5] = new Alfil('B',7,5);
        //DAMA
        newGame[0][3]=new Dama('N',0,3); newGame[7][3]=new Dama('B',7,3);
        //REY
        newGame[0][4]=new Rey('N',0,4); newGame[7][4]=new Rey('B',7,4);
        return newGame;
    }

     public static void printTable(Figura[][] table){
		 Figura rey,dama,torre,alfil,cavall,peo;
		 String figuraNom, cell="==";
		 char color;
		 for(int y=0;y<8;y++){
			 for(int x=0;x<8;x++){
				 color = (ControlErrores.isEmpty(table,y,x))?' ':(table[y][x].getColor());
				 //si campo esta buido - retornar nada, si no, retornar color
				 //si color blanco - dibujar figures blanco
				 if (color=='B'){
					figuraNom =table[y][x].getClass().getName();
					figuraNom = figuraNom.substring(8, figuraNom.length());
					switch(figuraNom){
						case "Rey": System.out.print("Rb"); break;
						case "Dama": System.out.print("Db"); break;
						case "Torre": System.out.print("Tb"); break;
						case "Alfil": System.out.print("Ab"); break;
						case "Cavall": System.out.print("Cb"); break;
						case "Peo": System.out.print("Pb"); break;
						}
					}else if (color=='N'){
						figuraNom = table[y][x].getClass().getName();
						figuraNom = figuraNom.substring(8, figuraNom.length());
						switch(figuraNom){
						case "Rey": System.out.print("Rn"); break;
						case "Dama": System.out.print("Dn"); break;
						case "Torre": System.out.print("Tn"); break;
						case "Alfil": System.out.print("An"); break;
						case "Cavall": System.out.print("Cn"); break;
						case "Peo": System.out.print("Pn"); break;
						}
					}else System.out.print(cell);
				 cell = (cell.equals("=="))?"__":"==";
			 }
			 cell = (cell.equals("=="))?"__":"==";
			 System.out.println();
		 }
		 
	 }

    public static String dig2Str(int numero){
        String lletra="";
        switch (numero){
            case 0: lletra="A"; break;
            case 1: lletra="B"; break;
            case 2: lletra="C"; break;
            case 3: lletra="D"; break;
            case 4: lletra="E"; break;
            case 5: lletra="F"; break;
            case 6: lletra="G"; break;
            case 7: lletra="H"; break;
        }
        return lletra;
    }

    public static boolean isEnemy(char color, Figura target){
        return color!=target.getColor();
    }


    public static Figura calculate(Figura[][] table, int y, int x, char player){
        /**
         * calcula movimientos y retorna figura
         */
        //dades de figura actual
        Figura fAct=table[y][x];
        fAct.borrarMoures();

        //dades de movimientos possible
        char color = fAct.getColor();

        //calcula movimientos para cada figura possible
        if (color==player){
            String fName = fAct.getClass().getName();
            fName = fName.substring(8,fName.length());
            switch(fName){
                case "Rey": {
                    Rey rey = (Rey) fAct;
                    rey.calcMooves(table);
                    fAct.setMouresPossible(rey.getMouresPossible());
                } break;
                case "Dama": {
                    Dama dama = (Dama) fAct;
                    dama.calcMooves(table);
                    fAct.setMouresPossible(dama.getMouresPossible());
                } break;
                case "Torre":{
                    Torre torre = (Torre) fAct;
                    torre.calcMooves(table);
                    fAct.setMouresPossible(torre.getMouresPossible());
                } break;
                case "Alfil":{
                    Alfil alfil = (Alfil) fAct;
                    alfil.calcMooves(table);
                    fAct.setMouresPossible(alfil.getMouresPossible());
                } break;
                case "Cavall":{
                    Cavall cavall = (Cavall) fAct;
                    cavall.calcMooves(table);
                    fAct.setMouresPossible(cavall.getMouresPossible());
                } break;
                case "Peo":{
                    Peo peo = (Peo) fAct;
                    peo.calcMooves(table);
                    fAct.setMouresPossible(peo.getMouresPossible());
                } break;
            }
        }

        return fAct;
    }


    public static boolean isCheck(Figura[][] table,char jugador){
        /**
         * retorna true si rey actual esta en jaque
         */
        Figura temp; //peca temporal
        int rx=0;
        int ry=0;
        //busca coordinats del rey del jugador
        for(int y=0; y<table.length;y++){
            for(int x=0;x<table[y].length;x++){
                temp = table[y][x];
                if(temp!=null && temp.getColor()==jugador && temp.getClass().getName().equals("Figures.Rey")){
                    ry=y;
                    rx=x;
                    break;
                }
            }
        }

        char color = (jugador=='B')?'N':'B';// color del adversario
        //calcula todos movimientos de pecas de jugador contrario
        for(int y=0; y<table.length;y++){
            for(int x=0;x<table[y].length;x++){
                //buscar por campos no buidos
                if(table[y][x]!=null){
                    //buscar solo por figures del color contrario
                    if(table[y][x].getColor()!=jugador){
                        //si puede alcanzar a el rey de jugador
                        temp = calculate(table,y,x,color);
                        if(temp.possible(ry,rx)) {
                            temp.borrarMoures();
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<int[]> remWrongMoves(Figura[][] table, Figura unit){
        /**
         * borrar els coordenadas del peca si movimiento provoca el jaque
         */
        ArrayList<int[]> unitMoves = unit.getMouresPossible(); //movimientos
        char jugador = unit.getColor(); //color del jugador
        int uX = unit.getX(); //coordinates del figura
        int uY = unit.getY();
        Figura[][] tempTable; //para clone del borde
        Figura temp; //para clone del figura
        int tx,ty; //coordinats del clone
        int runner=0; //limit del cantidades dels movimientos

        //mirar todos movimientos del figura
        while(runner < unitMoves.size() ){
            //realizar movimientos en clone del borde
            tempTable = clone(table);
            temp = tempTable[uY][uX];
            ty = unitMoves.get(runner)[0];
            tx = unitMoves.get(runner)[1];

            //mover la figura temporal en el clone del taula;
            tempTable[ty][tx]=temp;
            tempTable[uY][uX]=null;

            //borra si provoca el jaque
            if (isCheck(tempTable,jugador)) {
                unitMoves.remove(runner);
            }else{
                runner++;
            }
        }
        return unitMoves;
    }

    //dublica el tauler
    public static Figura[][] clone(Figura[][] table){
        Figura[][] tClone = new Figura[8][8];
        for(int y=0; y<table.length;y++){
            for(int x=0;x<table[y].length;x++){
                tClone[y][x] = table[y][x];
            }

        }
        return tClone;
    }

    public static boolean gameOver(Figura[][] table, char player) {
        /**
         * calcula movimientos potencials total y retorna true si no lo hay
         */
        int total = 0;
        Figura temp;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                temp = table[y][x];
                if (temp != null && temp.getColor() == player) {
                    temp = GameRules.calculate(table, y, x, player);
                    temp.setMouresPossible(GameRules.remWrongMoves(table, temp)); //borrar imposible coordinates
                    total += temp.getMouresPossible().size();
                    temp.borrarMoures();
                }
            }
        }
        return total==0;
    }
}

