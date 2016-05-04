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


public class PantallaInstrucciones implements Screen
{
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private Texture texturaFondo;
    private Sprite spriteFondo;

    private EstadosPantalla estadosPantalla;

    private Sprite spriteInstrucciones1;

    private Sprite spriteInstrucciones2;

    private Texture texturaBtnInicio;
    private Sprite spriteBtnInicio;

    private Sprite spriteBtnContinuar;
    private Texture texturaFondoJuego;
    private Sprite spriteFondoJuego;

    private Sprite spriteTitulo;
    private SpriteBatch batch;


    public PantallaInstrucciones(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
       Principal.musicaMenu.stop();
        estadosPantalla=EstadosPantalla.IN1;
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        batch = new SpriteBatch();

        cargarTexturasSprites();

    }



    private void cargarTexturasSprites() {
        texturaFondo = new Texture(Gdx.files.internal("marco_menus_copy.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaFondoJuego = new Texture(Gdx.files.internal("Pantalla_Inicio_copy.jpg"));
        spriteFondoJuego = new Sprite(texturaFondoJuego);

        Texture texturaInstrucciones1 = new Texture(Gdx.files.internal("instrucciones1.png"));
        spriteInstrucciones1 = new Sprite(texturaInstrucciones1);
        spriteInstrucciones1.setPosition(0,90);

        Texture texturaInstrucciones2 = new Texture(Gdx.files.internal("instrucciones2.png"));
        spriteInstrucciones2 = new Sprite(texturaInstrucciones2);
        spriteInstrucciones2.setPosition(0,90);
        texturaBtnInicio = new Texture(Gdx.files.internal("Btn_InicioP.png"));
        spriteBtnInicio = new Sprite(texturaBtnInicio);
        spriteBtnInicio.setPosition(Principal.ANCHO_MUNDO / 2-120 , Principal.ALTO_MUNDO/2-350);

        Texture texturaBtnContinuar = new Texture(Gdx.files.internal("Btn_continuar.png"));
        spriteBtnContinuar= new Sprite(texturaBtnContinuar);
        spriteBtnContinuar.setPosition(Principal.ANCHO_MUNDO / 2+300 , Principal.ALTO_MUNDO/2-350);

        Texture texturaTitulo = new Texture(Gdx.files.internal("T_Instrucciones.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth()/2-270, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 +240);

    }

    @Override
    public void render(float delta) {
        leerEntrada();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();

        spriteFondoJuego.draw(batch);
        spriteFondo.draw(batch);
        if(estadosPantalla==EstadosPantalla.IN1){
            spriteInstrucciones1.draw(batch);
            spriteBtnContinuar.draw(batch);
        }
        if(estadosPantalla==EstadosPantalla.IN2){
            spriteInstrucciones2.draw(batch);
        }


        spriteBtnInicio.draw(batch);

        spriteTitulo.draw(batch);

        batch.end();

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

    private void leerEntrada() {
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

            if(touchX>=spriteBtnContinuar.getX()&&
                    touchX<spriteBtnContinuar.getX()+spriteBtnContinuar.getWidth()
                    && touchY>=spriteBtnContinuar.getY()
                    && touchY<=spriteBtnContinuar.getY()+spriteBtnContinuar.getHeight()){
                estadosPantalla = EstadosPantalla.IN2;
            }

        }
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
        texturaBtnInicio.dispose();

        texturaFondoJuego.dispose();

    }
    public enum EstadosPantalla {
        IN1,
        IN2
    }
}