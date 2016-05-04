package itesm.mx.garasu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private Animation animacionCargando;
    //private Texture texturaLoading;
    private Texture texturaLoading1;
    private Texture texturaLoading2;
    private Texture texturaLoading3;
    private Texture texturaLoading4;
    private Texture texturaLoading5;
    private Texture texturaLoading6;
    private float timerAnimacion;   // tiempo para calcular el frame

    private Texture texturaLoadingFondo;
    private Sprite spriteLoadingFondo;
    private AssetManager assetManager;  // Asset manager principal



    public PantallaCargando(Principal principal) {
        this.principal = principal;
        this.assetManager = principal.getAssetManager();
    }

    @Override
    public void show() {
        // Crea la cámara/vista
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);
        //texturaLoading = new Texture(Gdx.files.internal("Loading_Tira.png"));
        texturaLoading1 = new Texture(Gdx.files.internal("Loading_Tira1.png"));
        texturaLoading2 = new Texture(Gdx.files.internal("Loading_Tira2.png"));
        texturaLoading3 = new Texture(Gdx.files.internal("Loading_Tira3.png"));
        texturaLoading4 = new Texture(Gdx.files.internal("Loading_Tira4.png"));
        texturaLoading5 = new Texture(Gdx.files.internal("Loading_Tira5.png"));
        texturaLoading6 = new Texture(Gdx.files.internal("Loading_Tira6.png"));
        //TextureRegion texturaCompleta = new TextureRegion(texturaLoading);
        texturaLoadingFondo = new Texture(Gdx.files.internal("Fondo loading.jpg"));
        //TextureRegion[][] texturaCargandoLuna = texturaCompleta.split(700,700);
        TextureRegion t1 = new TextureRegion(texturaLoading1,700,700);
        TextureRegion t2 = new TextureRegion(texturaLoading2,700,700);
        TextureRegion t3 = new TextureRegion(texturaLoading3,700,700);
        TextureRegion t4 = new TextureRegion(texturaLoading4,700,700);
        TextureRegion t5 = new TextureRegion(texturaLoading5,700,700);
        TextureRegion t6 = new TextureRegion(texturaLoading6,700,700);
       /* animacionCargando = new Animation(0.10f,texturaCargandoLuna[0][0],
                texturaCargandoLuna[0][1], texturaCargandoLuna[0][2],texturaCargandoLuna[0][3],texturaCargandoLuna[0][4],texturaCargandoLuna[0][5]);
       */
        animacionCargando = new Animation(0.10f,t1,
                t2, t3,t4,t5,t6);

        animacionCargando.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        spriteLoadingFondo = new Sprite(texturaLoadingFondo);
        spriteLoadingFondo.setPosition(0,0);
        batch = new SpriteBatch();

        // Cargar recursos


        cargarRecursos();
    }

    // Carga los recursos a través del administrador de assets (siguiente pantalla)
    private void cargarRecursos() {
        assetManager.load("Nivel_1_LargeMap.tmx", TiledMap.class);
        assetManager.load("Nivel2_Mapa.tmx", TiledMap.class);
        assetManager.load("Nivel3_Mapa.tmx", TiledMap.class);
        assetManager.load("izquierda.png", Texture.class);
        assetManager.load("derecha.png", Texture.class);
        assetManager.load("tiraGarasu.png", Texture.class);
        assetManager.load("tiraEnemigo.png", Texture.class);
        assetManager.load("menuMusica.wav", Music.class);
        assetManager.load("Nivel1.wav", Music.class);
        assetManager.load("Nivel2.wav", Music.class);
        assetManager.load("Nivel3.wav", Music.class);
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
        assetManager.load("pregunta2.png", Texture.class);
        assetManager.load("pregunta3.png", Texture.class);
        assetManager.load("boton_decisiones.png",Texture.class);
        assetManager.load("NivelCompletado.png",Texture.class);
        assetManager.load("TiraGisbar.png",Texture.class);

    }

    @Override
    public void render(float delta) {

        // Actualizar carga
        actualizar();
        timerAnimacion += Gdx.graphics.getDeltaTime();
        // Dibujar
        borrarPantalla();

        batch.setProjectionMatrix(camara.combined);
        TextureRegion region = animacionCargando.getKeyFrame(timerAnimacion);
        // Entre begin-end dibujamos nuestros objetos en pantalla
        batch.begin();
        spriteLoadingFondo.draw(batch);
        batch.draw(region, Principal.ANCHO_MUNDO/2-region.getRegionWidth()/2,Principal.ALTO_MUNDO/2-region.getRegionHeight()/2);
        batch.end();
    }

    private void actualizar() {

        if (assetManager.update()) {
            // Terminó la carga, cambiar de pantalla
            principal.setScreen(new PantallaHistoria(principal));

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
        dispose();
    }

    @Override
    public void dispose() {
      //  texturaLoading.dispose();
        texturaLoading1.dispose();
        texturaLoading2.dispose();
        texturaLoading3.dispose();
        texturaLoading4.dispose();
        texturaLoading5.dispose();
        texturaLoading6.dispose();
        //TextureRegion texturaCompleta = new TextureRegion(texturaLoading);
        texturaLoadingFondo.dispose();
        // Los assets de PantallaJuego se liberan en el método dispose de PantallaJuego
    }
}
