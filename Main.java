import Figures.*;
import GameSource.*;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
	private static Figura[][] table = GameRules.initGame();
	private static Grafica grafon = new Grafica();

	public Main(){
		setLayout(new BorderLayout()); //plantilla
		setSize(640,360); //tamaño
		setTitle("Escacs"); //titulo
		add(grafon.getButton());//dibujar los boton y labels
		add(grafon.getLabel());
		add("Center",grafon); //poner el Canvas al centre del finestra

		setLocationRelativeTo(null); //poner al centre del pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //como tancar applicacion

	}

	public static void main(String[] args) {
		Main escacs = new Main();
		escacs.setVisible(true);







	}

}
