package itesm.mx.whacamole;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;


public class Principal extends Game {
	public static final int ALTO_MUNDO = 720;
	public static final int ANCHO_MUNDO = 1280;

	private final AssetManager assetManager = new AssetManager();

	@Override
	public void create () {

		setScreen(new PantallaMenu(this));
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

}
