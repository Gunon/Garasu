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

/**
 * Created by user on 25/01/2016.
 */
//update
public class PantallaOpciones implements Screen {

    private final Principal principal;

    private OrthographicCamera camara;
    private Viewport vista;

    //Fondo


    //Musica
    private Music musicaMenu;
    public static boolean musicaT = true;

    private Texture texturaFondo;
    private Sprite spriteFondo;


    //Boton Play
    private Texture texturaBtnInicio;
    private Sprite spriteBtnInicio;

    //Boton Salir
    private Texture texturaBtnMusica;
    private Sprite spriteBtnMusica;

    //Boton Opciones
    private Texture texturaBtnMP;
    private Sprite spriteBtnMP;

    //Titulo
    private Texture texturaTitulo;
    private Sprite spriteTitulo;

    //Fondo Juego
    private Texture texturaFondoJuego;
    private Sprite spriteFondoJuego;


    private EstadoOpciones estado;

    //Dibujar
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
        //Fondos
        texturaFondo = new Texture(Gdx.files.internal("marco_menus_copy.png"));
        spriteFondo = new Sprite(texturaFondo);

        //Fondo Juego
        texturaFondoJuego = new Texture(Gdx.files.internal("Pantalla_Inicio_copy.jpg"));
        spriteFondoJuego = new Sprite(texturaFondoJuego);

        // BotonPlay
        texturaBtnInicio = new Texture(Gdx.files.internal("Btn_InicioP.png"));
        spriteBtnInicio = new Sprite(texturaBtnInicio);

        //Titulo
        texturaTitulo = new Texture(Gdx.files.internal("T_Opciones.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth() / 2 - 130, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 + 240);


        spriteBtnInicio.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth() / 2, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 - 140);

        //Boton Instrucciones
        texturaBtnMusica = new Texture(Gdx.files.internal("Btn_Musica.png"));
        spriteBtnMusica = new Sprite(texturaBtnMusica);
        spriteBtnMusica.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnMusica.getWidth() / 2, Principal.ALTO_MUNDO / 2 - 100);

        //Boton Opciones
        texturaBtnMP = new Texture(Gdx.files.internal("Btn_MusicaPaloma.png"));
        spriteBtnMP = new Sprite(texturaBtnMP);
        spriteBtnMP.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnMusica.getRegionWidth() / 2+500, Principal.ALTO_MUNDO / 2 - 100);

        estado = EstadoOpciones.MUSICON;
        //TextoOpciones


    }


    @Override
    public void render(float delta) {
        //Borrar la pantalla

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        leerEntrada();


        leerEntrada();
        batch.begin();
        spriteFondoJuego.draw(batch);
        spriteFondo.draw(batch);
        spriteBtnInicio.draw(batch);

        if(estado == EstadoOpciones.MUSICON){
            spriteBtnMP.draw(batch);
        } else {
            if(estado == EstadoOpciones.MUSICOFF){
                spriteBtnMusica.draw(batch);
            }
        }

        spriteTitulo.draw(batch);

        batch.end();

    }


    public void create() {

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
        //Cuando lo que usamos sale de memoria
        texturaFondo.dispose();
        texturaBtnInicio.dispose();
        texturaBtnMusica.dispose();
        texturaBtnMP.dispose();
        texturaTitulo.dispose();
        texturaFondoJuego.dispose();

    }

    private  void leerEntrada(){
        if(Gdx.input.justTouched()==true){
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);//Transforma las coordenadas
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
                    && estado == EstadoOpciones.MUSICOFF){
                estado = EstadoOpciones.MUSICON;
                musicaT=true;
            }
            if(touchX>=spriteBtnMP.getX()&&
                    touchX<spriteBtnMP.getX()+spriteBtnMP.getWidth()
                    && touchY>=spriteBtnMP.getY()
                    && touchY<=spriteBtnMP.getY()+spriteBtnMP.getHeight()
                    && estado == EstadoOpciones.MUSICON){
                estado = EstadoOpciones.MUSICOFF;
                musicaT=false;
            }

        }

    }

    public enum EstadoOpciones {
        MUSICON,
        MUSICOFF
    }

}
