package itesm.mx.garasu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
class PantallaMenu implements Screen {

    private final Principal principal;


    private OrthographicCamera camara;
    private Viewport vista;
    private Texture texturaFondo;
    private Sprite spriteFondo;

    private Texture texturaBtnPlay;
    private Sprite spriteBtnPlay;

    private Texture texturaBtnInstrucciones;
    private Sprite spriteBtnInstrucciones;

    private Texture texturaBtnOpciones;
    private Sprite spriteBtnOpciones;

    private Texture texturaTitulo;
    private Sprite spriteTitulo;


    private Texture texturaBtnPausa;
    private Sprite spriteBtnPausa;

    private SpriteBatch batch;

    public PantallaMenu(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {

        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        Principal.musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("menuMusica.wav"));
        Principal.musicaMenu.setLooping(true);
        System.out.println(Principal.musicaT);
        if(Principal.musicaT) {
            Principal.musicaMenu.play();
        }



        batch = new SpriteBatch();

        cargarTeturasSprites();
    }

    private void cargarTeturasSprites() {
        texturaFondo = new Texture(Gdx.files.internal("Pantalla_Inicio_copy.jpg"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnPausa = new Texture(Gdx.files.internal("Btn_Creditos.png"));
        spriteBtnPausa = new Sprite(texturaBtnPausa);
        spriteBtnPausa.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPausa.getWidth() / 2 + 550, Principal.ALTO_MUNDO / 2 - spriteBtnPausa.getRegionHeight() / 2 + 300);

        texturaBtnPlay = new Texture(Gdx.files.internal("Btn_Jugar.png"));
        spriteBtnPlay = new Sprite(texturaBtnPlay);

        texturaTitulo = new Texture(Gdx.files.internal("T_Garasu_copy.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth()/2, Principal.ALTO_MUNDO / 2 - spriteBtnPlay.getRegionHeight() / 2 +240);


        spriteBtnPlay.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth()/2, Principal.ALTO_MUNDO / 2 - spriteBtnPlay.getRegionHeight() / 2 - 140);

        texturaBtnInstrucciones = new Texture(Gdx.files.internal("Btn_Instrucciones.png"));
        spriteBtnInstrucciones = new Sprite(texturaBtnInstrucciones);
        spriteBtnInstrucciones.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth()-200 / 2, Principal.ALTO_MUNDO/2-100);

        texturaBtnOpciones = new Texture(Gdx.files.internal("Btn_Opciones.png"));
        spriteBtnOpciones = new Sprite(texturaBtnOpciones);
        spriteBtnOpciones.setPosition(Principal.ANCHO_MUNDO / 2 + 100, Principal.ALTO_MUNDO / 2 - 100);



    }


    @Override
        public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        leerEntrada();


        batch.begin();
        spriteFondo.draw(batch);
        spriteBtnPlay.draw(batch);
        spriteBtnInstrucciones.draw(batch);
        spriteBtnOpciones.draw(batch);
        spriteTitulo.draw(batch);
        spriteBtnPausa.draw(batch);
        batch.end();

    }


    public void resize(int width, int height) {
        vista.update(width, height);
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
        texturaFondo.dispose();
        texturaBtnPlay.dispose();
        texturaBtnInstrucciones.dispose();
        texturaBtnOpciones.dispose();
        texturaTitulo.dispose();
        texturaBtnPausa.dispose();

    }

    private  void leerEntrada(){
        if(Gdx.input.justTouched()){
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if(touchX>=spriteBtnPlay.getX()&&
                    touchX<spriteBtnPlay.getX()+spriteBtnPlay.getWidth()
                    && touchY>=spriteBtnPlay.getY()
                    && touchY<=spriteBtnPlay.getY()+spriteBtnPlay.getHeight()){
                Principal.firstm = false;
                principal.setScreen(new itesm.mx.garasu.PantallaCargando(principal));
                dispose();

            }
            if(touchX>=spriteBtnInstrucciones.getX()&&
                    touchX<spriteBtnInstrucciones.getX()+spriteBtnInstrucciones.getWidth()
                    && touchY>=spriteBtnInstrucciones.getY()
                    && touchY<=spriteBtnInstrucciones.getY()+spriteBtnInstrucciones.getHeight()){
                principal.setScreen(new itesm.mx.garasu.PantallaInstrucciones(principal));
            }
            if(touchX>=spriteBtnOpciones.getX()&&
                    touchX<spriteBtnOpciones.getX()+spriteBtnOpciones.getWidth()
                    && touchY>=spriteBtnOpciones.getY()
                    && touchY<=spriteBtnOpciones.getY()+spriteBtnOpciones.getHeight()){
                principal.setScreen(new itesm.mx.garasu.PantallaOpciones(principal));
            }
            if(touchX>=spriteBtnPausa.getX()&&
                    touchX<spriteBtnPausa.getX()+spriteBtnPausa.getWidth()
                    && touchY>=spriteBtnPausa.getY()
                    && touchY<=spriteBtnPausa.getY()+spriteBtnPausa.getHeight()){
                principal.setScreen(new itesm.mx.garasu.PantallaCreditos(principal));
            }
        }

    }

}
