package GameSource;
import java.awt.*;
import java.awt.event.*;
import Figures.*;

import javax.swing.*;

/**
 * Created by KoreeZ on 27.04.2017.
 */
public class Grafica extends Canvas implements ActionListener, MouseListener {

    private JButton newGameButton = new JButton("Renovar"); //renovar
    private JLabel message = new JLabel("Turno blanco");  //mostrar mensages de informacion
    private char player;    //jugador actual
    private int y=-1,x=-1;  //coordinates selectionades
    private Figura[][] table; //tauler
    private Figura fAct; // figura actual



    public Grafica(){
        setBackground(Color.white);
        addMouseListener(this);
        setFont(new Font("Serif", Font.PLAIN,16));
        newGameButton.setBounds(440,90,100,30);
        message.setBounds(425,200,140,30);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        newGameButton.addActionListener(this);
        this.table = GameRules.initGame();
        player = 'B';
    }

    public void paint(Graphics g) {
        boolean chainColor = false;
        char color;
        String fNom;
        
        //dibujos de pecas
        Image Rb = new ImageIcon("img\\Rb.png").getImage();
        Image Db = new ImageIcon("img\\Db.png").getImage();
        Image Tb = new ImageIcon("img\\Tb.png").getImage();
        Image Ab = new ImageIcon("img\\Ab.png").getImage();
        Image Cb = new ImageIcon("img\\Cb.png").getImage();
        Image Pb = new ImageIcon("img\\Pb.png").getImage();
        Image Rn = new ImageIcon("img\\Rn.png").getImage();
        Image Dn = new ImageIcon("img\\Dn.png").getImage();
        Image Tn = new ImageIcon("img\\Tn.png").getImage();
        Image An = new ImageIcon("img\\An.png").getImage();
        Image Cn = new ImageIcon("img\\Cn.png").getImage();
        Image Pn = new ImageIcon("img\\Pn.png").getImage();

        //dibujar tauler y figures
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                //color de campo
                if (chainColor) g.setColor(Color.GRAY);
                else g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x * 40, y * 40, 40, 40);
                
                //si campo esta buido - retornar nada, si no, retornar color                
                color = (ControlErrores.isEmpty(this.table,y,x))?' ':(this.table[y][x].getColor());

                //dibujar els pecas
                if (color=='B'){
                    fNom =table[y][x].getClass().getName(); //recibir el nombre de peca
                    fNom = fNom.substring(8, fNom.length()); //cortar l'inici "Figura."
                    switch(fNom){
                        case "Rey": g.drawImage(Rb,x*40+5,y*40+5,this); break;
                        case "Dama": g.drawImage(Db,x*40+5,y*40+5,this); break;
                        case "Torre": g.drawImage(Tb,x*40+5,y*40+5,this); break;
                        case "Alfil": g.drawImage(Ab,x*40+5,y*40+5,this); break;
                        case "Cavall": g.drawImage(Cb,x*40+5,y*40+5,this); break;
                        case "Peo": g.drawImage(Pb,x*40+5,y*40+5,this); break;
                    }
                }else if (color=='N'){
                    fNom = table[y][x].getClass().getName();
                    fNom = fNom.substring(8, fNom.length());
                    switch(fNom){
                        case "Rey": g.drawImage(Rn,x*40+5,y*40+5,this); break;
                        case "Dama": g.drawImage(Dn,x*40+5,y*40+5,this); break;
                        case "Torre": g.drawImage(Tn,x*40+5,y*40+5,this); break;
                        case "Alfil": g.drawImage(An,x*40+5,y*40+5,this); break;
                        case "Cavall": g.drawImage(Cn,x*40+5,y*40+5,this); break;
                        case "Peo": g.drawImage(Pn,x*40+5,y*40+5,this); break;
                    }
                }

              //dibujar el campo seleccionado
                if((this.x>=0 && this.x<8) && (this.y>=0 && this.y<8) && table[this.y][this.x]!=null){
                	g.setColor(Color.yellow);
                	g.drawRect(this.x*40+2, this.y*40+2, 36, 36);
                }

                chainColor = !chainColor;
            }
            chainColor= !chainColor;
        }

        //dibujar els movimientos potencial
        if(this.fAct!=null){
            for(int[] ar: this.fAct.getMouresPossible()){
                g.setColor(Color.BLUE);
                g.drawRect(ar[1]*40+2, ar[0]*40+2, 36, 36);

            }
        }
    }
    //*************FINAL DEL PARTIDA*************************************************
   public void pintarMensage(){
        //si no hay movimientos potencial
        if(GameRules.gameOver(this.table,this.player)){
            //si el rey esta en jaque - jugador perdido
            if(GameRules.isCheck(this.table, this.player)){
                this.message.setText("Mate! "+((this.player=='B')?"Negro ":"Blanco ")+"ganador");
            }else this.message.setText("Empate!");
            //si los hay - escribir de quien turno
        }else this.message.setText("Turno "+((this.player=='B')?"Blanco":"Negro"));
    }

    //*************ACTUALIZA FIGURA ELEJIDA Y COORDINATS*************************************************
   public void setActuals(int y,int x){
       /**
        *   actualiza movimientos de peca elejida
        */

       this.fAct = GameRules.calculate(this.table,y,x,this.player); //figura actual
       this.fAct.setMouresPossible(GameRules.remWrongMoves(this.table,this.fAct)); //borrar imposible coordinates
       this.x = x;
       this.y = y;
   }

	//****************NEWGAME*************************************************************************
    public void newGame(){
        //reiniciar la partida
        this.table = GameRules.initGame();
        this.fAct=null;
        this.y=-1;
        this.x=-1;
        this.player = 'B';
        this.message.setText("Turno Blanco");
        repaint();
    }
    
  //************MOOVETO******************************************************************************
	public void mooveTo(int toY, int toX){
        /**
         * mueve peça y borra los moures potencial
         */
		table[toY][toX] = table[this.y][this.x];
		table[this.y][this.x] = null;
        table[toY][toX].setCoordinats(toY,toX);
		this.fAct = null;
		this.x = -1;
		this.y = -1;
		this.player=(this.player=='B')?'N':'B';

        //esctibir de quien turno o mensage final
        pintarMensage();
	}
	//***********READMOVES****************************************************************************
	public void readMoves(int y, int x){
		/**
		 *compueve si movimiento possible - mueve,
         * si esta elejido otra figura - recalcular movimientos
		 */
		if(table[y][x]!=null && table[y][x].getColor()==player) this.setActuals(y, x);
		else if (this.fAct.possible(y,x)) this.mooveTo(y, x);
					
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        //responde por newGameButton click
        Object src = e.getSource();
        if(src == newGameButton) newGame();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    	/**lee coordinates y envia resultat a
    	 * calculate() si no hay figura seleccionada
    	 * readMoves() si hay
    	 */
        int fila = e.getY()/40;
        int columna = e.getX()/40;
        if(ControlErrores.inBorder(fila,columna)){
        	if(this.fAct==null){
        	    if(table[fila][columna]!=null) this.setActuals(fila,columna);
        	}
        	else this.readMoves(fila,columna);
        }
    }

    @Override
    public void update(Graphics g){paint(g);}
    public void mouseReleased(MouseEvent e) {repaint();}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public JButton getButton(){
        return this.newGameButton;
    }
    public JLabel getLabel(){return this.message;}
}
