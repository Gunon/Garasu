package itesm.mx.garasu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Pantala intermedia entre el menú y el juego
 */
public class PantallaCargando implements Screen
{
    private Principal principal;

    // La cámara y vista principal
    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;

    // Imagen cargando
    private Texture texturaCargando;
    private Sprite spriteCargando;

    private AssetManager assetManager;  // Asset manager principal

    public PantallaCargando(Principal principal) {
        this.principal = principal;
        this.assetManager = principal.getAssetManager();
    }

    @Override
    public void show() {
        // Crea la cámara/vista
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, principal.ALTO_MUNDO);
        camara.position.set(principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);

        batch = new SpriteBatch();

        // Cargar recursos
        assetManager.load("luna.gif", Texture.class);
        assetManager.finishLoading();
        texturaCargando = assetManager.get("luna.gif");

        spriteCargando = new Sprite(texturaCargando);
        spriteCargando.setPosition(principal.ANCHO_MUNDO / 2 - spriteCargando.getWidth() / 2,
                Principal.ALTO_MUNDO / 2 - spriteCargando.getHeight() / 2);

        cargarRecursos();
    }

    // Carga los recursos a través del administrador de assets (siguiente pantalla)
    private void cargarRecursos() {
        Gdx.app.log("CargarRecursos","Iniciando");
        // Carga los recursos de la siguiente pantalla (PantallaJuego)
        assetManager.load("Nivel_1_LargeMap.tmx", TiledMap.class);
        assetManager.load("izquierda.png", Texture.class);
        assetManager.load("derecha.png", Texture.class);
        assetManager.load("tiraGarasu.png", Texture.class);
        assetManager.load("tiraEnemigo.png", Texture.class);
        assetManager.load("POL-evil-throne-short.ogg", Music.class);
        assetManager.load("Btn_Pausa.png", Texture.class);
        assetManager.load("salto.png", Texture.class);
        assetManager.load("Boton_atacar.png", Texture.class);
        assetManager.load("Corazon_lleno.png", Texture.class);
        assetManager.load("Corazon_medio.png", Texture.class);
        assetManager.load("Corazon_vacio.png", Texture.class);
        assetManager.load("MarcoPausa.png", Texture.class);
        assetManager.load("Btn_InicioP.png", Texture.class);
        assetManager.load("Btn_continuar.png", Texture.class);
        assetManager.load("GameOver.png", Texture.class);
        assetManager.load("pregunta.png", Texture.class);
        assetManager.load("boton_decisiones.png",Texture.class);
        assetManager.load("NivelCompletado.png",Texture.class);
        Gdx.app.log("CargarRecursos", "Terminando");
    }

    @Override
    public void render(float delta) {

        // Actualizar carga
        actualizar();

        // Dibujar
        borrarPantalla();
        spriteCargando.setRotation(spriteCargando.getRotation() + 15);

        batch.setProjectionMatrix(camara.combined);

        // Entre begin-end dibujamos nuestros objetos en pantalla
        batch.begin();
        spriteCargando.draw(batch);
        batch.end();
    }

    private void actualizar() {

        if (assetManager.update()) {
            // Terminó la carga, cambiar de pantalla
            principal.setScreen(new PantallaNivel1(principal));
        } else {
            // Aún no termina la carga de assets, leer el avance
            float avance = assetManager.getProgress()*100;
            Gdx.app.log("Cargando",avance+"%");
        }
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(0.42f, 0.55f, 1, 1);    // r, g, b, alpha
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        texturaCargando.dispose();
        // Los assets de PantallaJuego se liberan en el método dispose de PantallaJuego
    }
}
