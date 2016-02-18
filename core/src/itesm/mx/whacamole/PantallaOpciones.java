package itesm.mx.whacamole;

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

    //Fondo


    private Texture texturaFondo;
    private Sprite spriteFondo;


    //Boton Play
    private Texture texturaBtnInicio;
    private Sprite spriteBtnInicio;

    //Boton Salir
    private Texture texturaBtnMusica;
    private Sprite spriteBtnMusica;

    //Boton Opciones
    private Texture texturaBtnSonidos;
    private Sprite spriteBtnSonidos;

    //Titulo
    private Texture texturaTitulo;
    private Sprite spriteTitulo;





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
        texturaFondo = new Texture(Gdx.files.internal("Marco_Menus.png"));
        spriteFondo = new Sprite(texturaFondo);


        // BotonPlay
        texturaBtnInicio = new Texture(Gdx.files.internal("Btn_Inicio.png"));
        spriteBtnInicio = new Sprite(texturaBtnInicio);

        //Titulo
        texturaTitulo = new Texture(Gdx.files.internal("T_Opciones.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth()/2, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 +240);


        spriteBtnInicio.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth()/2, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 - 140);

        //Boton Instrucciones
        texturaBtnMusica = new Texture(Gdx.files.internal("Btn_Musica.png"));
        spriteBtnMusica = new Sprite(texturaBtnMusica);
        spriteBtnMusica.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnMusica.getWidth()-200 / 2, Principal.ALTO_MUNDO/2-100);

        //Boton Opciones
        texturaBtnSonidos = new Texture(Gdx.files.internal("Btn_Sonidos.png"));
        spriteBtnSonidos = new Sprite(texturaBtnSonidos);
        spriteBtnSonidos.setPosition(Principal.ANCHO_MUNDO / 2 + 100, Principal.ALTO_MUNDO / 2 - 100);

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
        spriteFondo.draw(batch);
        spriteBtnInicio.draw(batch);
        spriteBtnMusica.draw(batch);
        spriteBtnSonidos.draw(batch);
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

    }

    @Override
    public void dispose() {
        //Cuando lo que usamos sale de memoria
        texturaFondo.dispose();
        texturaBtnInicio.dispose();
        texturaBtnMusica.dispose();
        texturaBtnSonidos.dispose();
        texturaTitulo.dispose();

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

        }

    }

}
