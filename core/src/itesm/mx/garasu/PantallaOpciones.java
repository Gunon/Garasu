package itesm.mx.garasu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by user on 25/01/2016.
 */
//update
public class PantallaOpciones implements Screen {

    private final Principal principal;

    private OrthographicCamera camara;
    private Viewport vista;
    private Texture texturaFondo;
    private Sprite spriteFondo;
    private Texture texturaBtnInicio;
    private Sprite spriteBtnInicio;
    private Texture texturaBtnMusica;
    private Sprite spriteBtnMusica;
    private Texture texturaBtnMP;
    private Sprite spriteBtnMP;
    private Texture texturaTitulo;
    private Sprite spriteTitulo;
    private Texture texturaFondoJuego;
    private Sprite spriteFondoJuego;


    private EstadoOpciones estado;
    private SpriteBatch batch;

    public PantallaOpciones(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {

        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        batch = new SpriteBatch();



        cargarTeturasSprites();
    }

    private void cargarTeturasSprites() {
        texturaFondo = new Texture(Gdx.files.internal("marco_menus_copy.png"));
        spriteFondo = new Sprite(texturaFondo);
        texturaFondoJuego = new Texture(Gdx.files.internal("Pantalla_Inicio_copy.jpg"));
        spriteFondoJuego = new Sprite(texturaFondoJuego);
        texturaBtnInicio = new Texture(Gdx.files.internal("Btn_InicioP.png"));
        spriteBtnInicio = new Sprite(texturaBtnInicio);
        texturaTitulo = new Texture(Gdx.files.internal("T_Opciones.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth() / 2 - 130, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 + 240);


        spriteBtnInicio.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth() / 2, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 - 140);

        texturaBtnMusica = new Texture(Gdx.files.internal("Btn_Musica.png"));
        spriteBtnMusica = new Sprite(texturaBtnMusica);
        spriteBtnMusica.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnMusica.getWidth() / 2+300, Principal.ALTO_MUNDO / 2 - 100);

        texturaBtnMP = new Texture(Gdx.files.internal("Btn_MusicaPaloma.png"));
        spriteBtnMP = new Sprite(texturaBtnMP);
        spriteBtnMP.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnMusica.getRegionWidth() / 2-300 , Principal.ALTO_MUNDO / 2 - 100);

        estado = EstadoOpciones.MUSICON;


    }


    @Override
    public void render(float delta) {
        if(Principal.musicaT && !Principal.playing){
            PantallaMenu.musicaMenu.play();
            Principal.playing=true;
        }
        if(!Principal.musicaT && Principal.playing){
            PantallaMenu.musicaMenu.stop();
            Principal.playing=false;
        }
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);



        leerEntrada();
        batch.begin();
        spriteFondoJuego.draw(batch);
        spriteFondo.draw(batch);
        spriteBtnInicio.draw(batch);


            spriteBtnMP.draw(batch);

                spriteBtnMusica.draw(batch);


        spriteTitulo.draw(batch);

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
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
        texturaBtnInicio.dispose();
        texturaBtnMusica.dispose();
        texturaBtnMP.dispose();
        texturaTitulo.dispose();
        texturaFondoJuego.dispose();

    }

    private  void leerEntrada(){
        if(Gdx.input.justTouched()){
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if(touchX>=spriteBtnInicio.getX()&&
                    touchX<spriteBtnInicio.getX()+spriteBtnInicio.getWidth()
                    && touchY>=spriteBtnInicio.getY()
                    && touchY<=spriteBtnInicio.getY()+spriteBtnInicio.getHeight()){
                principal.setScreen(new PantallaMenu(principal));
            }
            if(touchX>=spriteBtnMusica.getX()&&
                    touchX<spriteBtnMusica.getX()+spriteBtnMusica.getWidth()
                    && touchY>=spriteBtnMusica.getY()
                    && touchY<=spriteBtnMusica.getY()+spriteBtnMusica.getHeight()
                    && estado == EstadoOpciones.MUSICON){
                estado = EstadoOpciones.MUSICOFF;
                Principal.musicaT=false;
            }
            if(touchX>=spriteBtnMP.getX()&&
                    touchX<spriteBtnMP.getX()+spriteBtnMP.getWidth()
                    && touchY>=spriteBtnMP.getY()
                    && touchY<=spriteBtnMP.getY()+spriteBtnMP.getHeight()
                    && estado == EstadoOpciones.MUSICOFF){
                estado = EstadoOpciones.MUSICON;
                Principal.musicaT=true;
            }

        }

    }

    public enum EstadoOpciones {
        MUSICON,
        MUSICOFF
    }

}
