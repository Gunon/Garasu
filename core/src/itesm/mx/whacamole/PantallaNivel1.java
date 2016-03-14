package itesm.mx.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
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
public class PantallaNivel1 implements Screen
{
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;
    private OrthographicCamera camaraHUD;

    //Personaje
    private Personaje personaje;

    // Fondo
    private Texture texturaFondo;


    //Pasto
    private Texture texturaPasto;


    //Estrellas
    private Texture texturaEstrellas;

    //Plantas
    private Texture texturaPlantas;


    // Nubes
    private Texture texturaNubes;


    // Suelo
    private Texture texturaSuelo;


    // Arboles
    private Texture texturaArboles;


    //Personaje
    private Texture texturaPersonaje;


    //
    private Texture texturaBtnPausa;
    private Sprite spriteBtnPausa;

    // Botones izquierda/derecha
    private Texture texturaBtnIzquierda;
    private Boton btnIzquierda;
    private Texture texturaBtnDerecha;
    private Boton btnDerecha;

    // Dibujar
    private SpriteBatch batch;

    AssetManager assetManager = new AssetManager();

    //Sonids


    public PantallaNivel1(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
        // Se ejecuta cuando se muestra la pantalla
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        camaraHUD = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camaraHUD.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camaraHUD.update();

        batch = new SpriteBatch();

        cargarRecursos();

    }



    private void cargarRecursos() {



        assetManager.load("FondoF.png",Texture.class);
        assetManager.load("garasu.png",Texture.class);
        assetManager.load("Nubes.png",Texture.class);
        assetManager.load("Plantas.png",Texture.class);
        assetManager.load("Estrellas.png",Texture.class);
        assetManager.load("Pasto.png", Texture.class);
        assetManager.load("Arboles.png", Texture.class);
        assetManager.load("Suelo.png", Texture.class);
        assetManager.load("Btn_Pausa.png", Texture.class);
        assetManager.finishLoading();

        texturaFondo = assetManager.get("FondoF.png");
        texturaPersonaje = assetManager.get("garasu.png");
        texturaNubes = assetManager.get("Nubes.png");
        texturaPlantas = assetManager.get("Plantas.png");
        texturaEstrellas = assetManager.get("Estrellas.png");
        texturaPasto = assetManager.get("Pasto.png");
        texturaArboles = assetManager.get("Arboles.png");
        texturaSuelo = assetManager.get("Suelo.png");

        //Personaje
        personaje = new Personaje(texturaPersonaje);



        texturaBtnPausa = assetManager.get("Btn_Pausa.png");
        spriteBtnPausa = new Sprite(texturaBtnPausa);
        spriteBtnPausa.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPausa.getWidth()/2+550, Principal.ALTO_MUNDO / 2 - spriteBtnPausa.getRegionHeight() / 2 +300);

    }



    @Override
    public void render(float delta) {
        // Leer

        leerEntrada();
        assetManager.update();
        // Actualizar objetos
        // Borrar la pantalla
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Proyectamos la cámara sobre batch
        batch.setProjectionMatrix(camara.combined);
        batch.setProjectionMatrix(camaraHUD.combined);


        // Dibujamos

        batch.begin();
        //batch.draw(texturaFondo, 0, 0);
        //batch.draw(texturaEstrellas,0,0);
        batch.draw(texturaNubes,0,0);
        //batch.draw(texturaArboles,0,0);
        //batch.draw(texturaPasto,0,0);
        //batch.draw(texturaPlantas,0,0);
        //batch.draw(texturaSuelo,0,0);
        //batch.draw(texturaPersonaje,0,0);
        spriteBtnPausa.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void pause() {

    }

    private void actualizarCamara() {
      /*  float posX =
        // Si está en la parte 'media'
        if (posX>=Principal.ANCHO_MUNDO/2 && posX<=Principal.ANCHO_MUNDO/2) {
            // El personaje define el centro de la cámara
            camara.position.set((int)posX, camara.position.y, 0);
        } else if (posX>Principal.ANCHO_MUNDO/2) {    // Si está en la última mitad
            // La cámara se queda a media pantalla antes del fin del mundo  :)
            camara.position.set(Principal.ANCHO_MUNDO/2, camara.position.y, 0);
        }
        camara.update();
        */
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

            if(touchX>=spriteBtnPausa.getX()&&
                    touchX<spriteBtnPausa.getX()+spriteBtnPausa.getWidth()
                    && touchY>=spriteBtnPausa.getY()
                    && touchY<=spriteBtnPausa.getY()+spriteBtnPausa.getHeight()){
                principal.setScreen(new PantallaMenu(principal));
            }

        }
    }

    @Override
    public void dispose() {
        // Cuando la PantallaMenu sale de memoria.
        // LIBERAR los recursos
        texturaFondo.dispose(); // regresamos la memoria
        texturaPasto.dispose();
        texturaArboles.dispose();
        texturaNubes.dispose();
        texturaSuelo.dispose();
        texturaBtnPausa.dispose();
        texturaEstrellas.dispose();
        texturaPlantas.dispose();
        texturaPersonaje.dispose();

    }
}