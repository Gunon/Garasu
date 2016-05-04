package itesm.mx.garasu;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;


public class Principal extends Game {
	public static final int ALTO_MUNDO = 720;
	public static final int ANCHO_MUNDO = 1280;

	private final AssetManager assetManager = new AssetManager();
	public static boolean firstm = false;
	public static boolean playing =true;


	public static boolean musicaT = true;
	@Override
	public void create () {
		assetManager.setLoader(TiledMap.class,
				new TmxMapLoader(new InternalFileHandleResolver()));
		setScreen(new PantallaTec(this));
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

}
