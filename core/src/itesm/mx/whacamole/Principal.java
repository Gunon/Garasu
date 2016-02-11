package itesm.mx.whacamole;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Principal extends Game {
	public static final int ALTO_MUNDO = 720;
	public static final int ANCHO_MUNDO = 1280;


	@Override
	public void create () {

		setScreen(new PantallaMenu());
	}



}
