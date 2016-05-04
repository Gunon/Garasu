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
public class PantallaMenu implements Screen {

    private final Principal principal;



    private OrthographicCamera camara;
    private Viewport vista;

    //Fondo


    private Texture texturaFondo;
    private Sprite spriteFondo;


    //Musica
  //  private Music musicaMenu;

    //Boton Play
    private Texture texturaBtnPlay;
    private Sprite spriteBtnPlay;

    //Boton Instrucciones
    private Texture texturaBtnInstrucciones;
    private Sprite spriteBtnInstrucciones;

    //Boton Opciones
    private Texture texturaBtnOpciones;
    private Sprite spriteBtnOpciones;



    //Titulo
    private Texture texturaTitulo;
    private Sprite spriteTitulo;

    //Creditos
    private Texture texturaBtnPausa;
    private Sprite spriteBtnPausa;



    //Dibujar
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

      /*  musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("POL-sacred-temple-short.ogg"));
        musicaMenu.setLooping(true);

            musicaMenu.play();*/


        batch = new SpriteBatch();

        cargarTeturasSprites();
    }

    private void cargarTeturasSprites() {
        //Fondos
        texturaFondo = new Texture(Gdx.files.internal("Pantalla_Inicio_copy.jpg"));
        spriteFondo = new Sprite(texturaFondo);

        //Creditos
        texturaBtnPausa = new Texture(Gdx.files.internal("Btn_Creditos.png"));
        spriteBtnPausa = new Sprite(texturaBtnPausa);
        spriteBtnPausa.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPausa.getWidth() / 2 + 550, Principal.ALTO_MUNDO / 2 - spriteBtnPausa.getRegionHeight() / 2 + 300);


        // BotonPlay
        texturaBtnPlay = new Texture(Gdx.files.internal("Btn_Jugar.png"));
        spriteBtnPlay = new Sprite(texturaBtnPlay);

        //Titulo
        texturaTitulo = new Texture(Gdx.files.internal("T_Garasu_copy.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth()/2, Principal.ALTO_MUNDO / 2 - spriteBtnPlay.getRegionHeight() / 2 +240);


        spriteBtnPlay.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth()/2, Principal.ALTO_MUNDO / 2 - spriteBtnPlay.getRegionHeight() / 2 - 140);

        //Boton Instrucciones
        texturaBtnInstrucciones = new Texture(Gdx.files.internal("Btn_Instrucciones.png"));
        spriteBtnInstrucciones = new Sprite(texturaBtnInstrucciones);
        spriteBtnInstrucciones.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth()-200 / 2, Principal.ALTO_MUNDO/2-100);

        //Boton Opciones
        texturaBtnOpciones = new Texture(Gdx.files.internal("Btn_Opciones.png"));
        spriteBtnOpciones = new Sprite(texturaBtnOpciones);
        spriteBtnOpciones.setPosition(Principal.ANCHO_MUNDO / 2 + 100, Principal.ALTO_MUNDO / 2 - 100);

        //TextoOpciones


    }


    @Override
    public void render(float delta) {
        //Borrar la pantalla

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

    }

    @Override
    public void dispose() {
        //Cuando lo que usamos sale de memoria
        texturaFondo.dispose();
        texturaBtnPlay.dispose();
        texturaBtnInstrucciones.dispose();
        texturaBtnOpciones.dispose();
        texturaTitulo.dispose();
        texturaBtnPausa.dispose();
        /*musicaMenu.dispose();*/

    }

    private  void leerEntrada(){
        if(Gdx.input.justTouched()==true){
            Vector3 coordenadas = new Vector3();
            coordenadas.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camara.unproject(coordenadas);//Transforma las coordenadas
            float touchX = coordenadas.x;
            float touchY = coordenadas.y;

            if(touchX>=spriteBtnPlay.getX()&&
                    touchX<spriteBtnPlay.getX()+spriteBtnPlay.getWidth()
                    && touchY>=spriteBtnPlay.getY()
                    && touchY<=spriteBtnPlay.getY()+spriteBtnPlay.getHeight()){
               // musicaMenu.stop();
                principal.setScreen(new itesm.mx.garasu.PantallaCargando(principal));
                dispose();

            }
            if(touchX>=spriteBtnInstrucciones.getX()&&
                    touchX<spriteBtnInstrucciones.getX()+spriteBtnInstrucciones.getWidth()
                    && touchY>=spriteBtnInstrucciones.getY()
                    && touchY<=spriteBtnInstrucciones.getY()+spriteBtnInstrucciones.getHeight()){
               // musicaMenu.stop();
                principal.setScreen(new itesm.mx.garasu.PantallaInstrucciones(principal));
            }
            if(touchX>=spriteBtnOpciones.getX()&&
                    touchX<spriteBtnOpciones.getX()+spriteBtnOpciones.getWidth()
                    && touchY>=spriteBtnOpciones.getY()
                    && touchY<=spriteBtnOpciones.getY()+spriteBtnOpciones.getHeight()){
               // musicaMenu.stop();
                principal.setScreen(new itesm.mx.garasu.PantallaOpciones(principal));
            }
            if(touchX>=spriteBtnPausa.getX()&&
                    touchX<spriteBtnPausa.getX()+spriteBtnPausa.getWidth()
                    && touchY>=spriteBtnPausa.getY()
                    && touchY<=spriteBtnPausa.getY()+spriteBtnPausa.getHeight()){
               // musicaMenu.stop();
                principal.setScreen(new itesm.mx.garasu.PantallaCreditos(principal));
            }
        }

    }

}
