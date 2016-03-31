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
 * Created by rmroman on 04/02/16.
 */
public class PantallaInstrucciones implements Screen
{
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    // Fondo
    private Texture texturaFondo;
    private Sprite spriteFondo;

    //Boton Play
    private Texture texturaBtnInicio;
    private Sprite spriteBtnInicio;

    //Boton Ayuda
    private Texture texturaBtnAyuda;
    private Sprite spriteBtnAyuda;

    //Fondo Juego
    private Texture texturaFondoJuego;
    private Sprite spriteFondoJuego;

    //Titulo
    private Texture texturaTitulo;
    private Sprite spriteTitulo;

    //Gema Amarilla
    private Texture texturaGemaAmarilla;
    private Sprite spriteGemaAmarilla;

    //Gema Azul
    private Texture texturaGemaAzul;
    private Sprite spriteGemaAzul;

    //Gema Morada
    private Texture texturaGemaMorada;
    private Sprite spriteGemaMorada;

    // Dibujar
    private SpriteBatch batch;


    public PantallaInstrucciones(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
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

        //BtnInicio
        texturaBtnInicio = new Texture(Gdx.files.internal("Btn_InicioP.png"));
        spriteBtnInicio = new Sprite(texturaBtnInicio);
        spriteBtnInicio.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth()-200 / 2, Principal.ALTO_MUNDO/2-100);

        //BtnInicio
        texturaGemaAmarilla = new Texture(Gdx.files.internal("G_Amarilla.png"));
        spriteGemaAmarilla = new Sprite(texturaGemaAmarilla);
        spriteGemaAmarilla.setPosition(Principal.ANCHO_MUNDO / 2 - spriteGemaAmarilla.getWidth()-200 / 2, Principal.ALTO_MUNDO/2-200);

        //BtnInicio
        texturaGemaAzul = new Texture(Gdx.files.internal("G_Azul.png"));
        spriteGemaAzul = new Sprite(texturaGemaAzul);
        spriteGemaAzul.setPosition(Principal.ANCHO_MUNDO / 2 - spriteGemaAzul.getWidth() / 2, Principal.ALTO_MUNDO/2-200);

        //BtnInicio
        texturaGemaMorada = new Texture(Gdx.files.internal("G_Morado.png"));
        spriteGemaMorada = new Sprite(texturaGemaMorada);
        spriteGemaMorada.setPosition(Principal.ANCHO_MUNDO / 2 - spriteGemaMorada.getWidth()+300 / 2, Principal.ALTO_MUNDO/2-200);

        texturaBtnAyuda = new Texture(Gdx.files.internal("Btn_Ayuda.png"));
        spriteBtnAyuda = new Sprite(texturaBtnAyuda);
        spriteBtnAyuda.setPosition(Principal.ANCHO_MUNDO / 2 + 100, Principal.ALTO_MUNDO / 2 - 100);

        //Titulo
        texturaTitulo = new Texture(Gdx.files.internal("T_Instrucciones.png"));
        spriteTitulo = new Sprite(texturaTitulo);
        spriteTitulo.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicio.getWidth()/2-165, Principal.ALTO_MUNDO / 2 - spriteBtnInicio.getRegionHeight() / 2 +240);

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
        spriteBtnInicio.draw(batch);
        spriteBtnAyuda.draw(batch);
        spriteTitulo.draw(batch);
        spriteGemaAmarilla.draw(batch);
        spriteGemaAzul.draw(batch);
        spriteGemaMorada.draw(batch);
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

        }
    }

    @Override
    public void dispose() {
        // Cuando la PantallaMenu sale de memoria.
        // LIBERAR los recursos
        texturaFondo.dispose(); // regresamos la memoria
        texturaBtnInicio.dispose();
        texturaBtnAyuda.dispose();
        texturaFondoJuego.dispose();

    }
}