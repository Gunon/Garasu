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

    // Fondo
    private Texture texturaFondo;
    private Sprite spriteFondo;

    private EstadosPantalla estadosPantalla;

    //Instrucciones
    private Texture texturaInstrucciones1;
    private Sprite spriteInstrucciones1;

    private Texture texturaInstrucciones2;
    private Sprite spriteInstrucciones2;

    //Boton Play
    private Texture texturaBtnInicio;
    private Sprite spriteBtnInicio;

    private Texture texturaBtnContinuar;
    private Sprite spriteBtnContinuar;


    //Fondo Juego
    private Texture texturaFondoJuego;
    private Sprite spriteFondoJuego;

    //Titulo
    private Texture texturaTitulo;
    private Sprite spriteTitulo;


    // Dibujar
    private SpriteBatch batch;


    public PantallaInstrucciones(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
        estadosPantalla=EstadosPantalla.IN1;
        // Se ejecuta cuando se muestra la pantalla
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        batch = new SpriteBatch();

        cargarTexturasSprites();

    }



    private void cargarTexturasSprites() {
        // Fondo
        texturaFondo = new Texture(Gdx.files.internal("marco_menus_copy.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaFondoJuego = new Texture(Gdx.files.internal("Pantalla_Inicio_copy.jpg"));
        spriteFondoJuego = new Sprite(texturaFondoJuego);

        texturaInstrucciones1 = new Texture(Gdx.files.internal("instrucciones1.png"));
        spriteInstrucciones1 = new Sprite(texturaInstrucciones1);
        spriteInstrucciones1.setPosition(0,90);

        texturaInstrucciones2 = new Texture(Gdx.files.internal("instrucciones2.png"));
        spriteInstrucciones2 = new Sprite(texturaInstrucciones2);
        spriteInstrucciones2.setPosition(0,90);
//hola
        //BtnInicio
        texturaBtnInicio = new Texture(Gdx.files.internal("Btn_InicioP.png"));
        spriteBtnInicio = new Sprite(texturaBtnInicio);
        spriteBtnInicio.setPosition(Principal.ANCHO_MUNDO / 2-120 , Principal.ALTO_MUNDO/2-350);

        texturaBtnContinuar= new Texture(Gdx.files.internal("Btn_continuar.png"));
        spriteBtnContinuar= new Sprite(texturaBtnContinuar);
        spriteBtnContinuar.setPosition(Principal.ANCHO_MUNDO / 2+300 , Principal.ALTO_MUNDO/2-350);





        //Titulo
        texturaTitulo = new Texture(Gdx.files.internal("T_Instrucciones.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth()/2-270, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 +240);

    }

    @Override
    public void render(float delta) {
        // Leer
        leerEntrada();

        // Actualizar objetos
        // Borrar la pantalla
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Proyectamos la cámara sobre batch
        batch.setProjectionMatrix(camara.combined);
        // Dibujamos
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
        // Cuando la PantallaMenu sale de memoria.
        // LIBERAR los recursos
        texturaFondo.dispose(); // regresamos la memoria
        texturaBtnInicio.dispose();

        texturaFondoJuego.dispose();

    }
    public enum EstadosPantalla {
        IN1,
        IN2
    }
}