package itesm.mx.whacamole;


import com.badlogic.gdx.Game;


public class Principal extends Game {
	public static final int ALTO_MUNDO = 720;
	public static final int ANCHO_MUNDO = 1280;


	@Override
	public void create () {

		setScreen(new PantallaMenu(this));
	}



}
