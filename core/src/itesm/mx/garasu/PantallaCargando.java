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

class PantallaCargando implements Screen
{
    private final Principal principal;

    private OrthographicCamera camara;
    private Viewport vista;
    private SpriteBatch batch;

    private Animation animacionCargando;
    private Texture texturaLoading1;
    private Texture texturaLoading2;
    private Texture texturaLoading3;
    private Texture texturaLoading4;
    private Texture texturaLoading5;
    private Texture texturaLoading6;
    private float timerAnimacion;

    private Texture texturaLoadingFondo;
    private Sprite spriteLoadingFondo;
    private final AssetManager assetManager;



    public PantallaCargando(Principal principal) {
        this.principal = principal;
        this.assetManager = principal.getAssetManager();
    }

    @Override
    public void show() {
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO, camara);
        texturaLoading1 = new Texture(Gdx.files.internal("Loading_Tira1.png"));
        texturaLoading2 = new Texture(Gdx.files.internal("Loading_Tira2.png"));
        texturaLoading3 = new Texture(Gdx.files.internal("Loading_Tira3.png"));
        texturaLoading4 = new Texture(Gdx.files.internal("Loading_Tira4.png"));
        texturaLoading5 = new Texture(Gdx.files.internal("Loading_Tira5.png"));
        texturaLoading6 = new Texture(Gdx.files.internal("Loading_Tira6.png"));
        texturaLoadingFondo = new Texture(Gdx.files.internal("Fondo loading.jpg"));
        TextureRegion t1 = new TextureRegion(texturaLoading1,700,700);
        TextureRegion t2 = new TextureRegion(texturaLoading2,700,700);
        TextureRegion t3 = new TextureRegion(texturaLoading3,700,700);
        TextureRegion t4 = new TextureRegion(texturaLoading4,700,700);
        TextureRegion t5 = new TextureRegion(texturaLoading5,700,700);
        TextureRegion t6 = new TextureRegion(texturaLoading6,700,700);
        animacionCargando = new Animation(0.10f,t1,
                t2, t3,t4,t5,t6);

        animacionCargando.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        spriteLoadingFondo = new Sprite(texturaLoadingFondo);
        spriteLoadingFondo.setPosition(0,0);
        batch = new SpriteBatch();



        cargarRecursos();
    }

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

        actualizar();
        timerAnimacion += Gdx.graphics.getDeltaTime();
        borrarPantalla();

        batch.setProjectionMatrix(camara.combined);
        TextureRegion region = animacionCargando.getKeyFrame(timerAnimacion);
        batch.begin();
        spriteLoadingFondo.draw(batch);
        batch.draw(region, Principal.ANCHO_MUNDO/2-region.getRegionWidth()/2,Principal.ALTO_MUNDO/2-region.getRegionHeight()/2);
        batch.end();
    }

    private void actualizar() {

        if (assetManager.update()) {
            principal.setScreen(new PantallaHistoria(principal));

        }
    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(0.42f, 0.55f, 1, 1);
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
        texturaLoading1.dispose();
        texturaLoading2.dispose();
        texturaLoading3.dispose();
        texturaLoading4.dispose();
        texturaLoading5.dispose();
        texturaLoading6.dispose();
        texturaLoadingFondo.dispose();
    }
}
