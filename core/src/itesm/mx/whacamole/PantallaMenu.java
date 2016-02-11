package itesm.mx.whacamole;

import com.badlogic.gdx.Game;
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

public class PantallaMenu extends Game implements Screen {

    private OrthographicCamera camara;
    private Viewport vista;

    //Fondo


    private Texture texturaFondo;
    private Sprite spriteFondo;


    //Boton Play
    private Texture texturaBtnPlay;
    private Sprite spriteBtnPlay;

    //Boton Salir
    private Texture texturaBtnSalir;
    private Sprite spriteBtnSalir;

    //Dibujar
    private SpriteBatch batch;


    @Override
    public void show() {

        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO/2,Principal.ALTO_MUNDO/2,0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        batch = new SpriteBatch();

        cargarTeturasSprites();
    }

    private void cargarTeturasSprites() {
        //Fondos
        texturaFondo = new Texture(Gdx.files.internal("M_Fondo.png"));
        spriteFondo = new Sprite(texturaFondo);

       // BotonPlay
        texturaBtnPlay = new Texture(Gdx.files.internal("M_Btn.png"));
        spriteBtnPlay = new Sprite(texturaBtnPlay);
        
        spriteBtnPlay.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth() / 2 - 10, Principal.ALTO_MUNDO / 2 - spriteBtnPlay.getRegionHeight() / 2 - 114);

        //Boton Salir
 //       texturaBtnSalir = new Texture(Gdx.files.internal("exitBtn.png"));
   //     spriteBtnSalir = new Sprite(texturaBtnSalir);
     //   spriteBtnSalir.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnPlay.getWidth() / 2, Principal.ALTO_MUNDO / 4 - spriteBtnPlay.getRegionHeight() / 2);

    }


    @Override
    public void render(float delta) {
        //Borrar la pantalla
        leerEntrada();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camara.combined);
        leerEntrada();
        batch.begin();
        spriteFondo.draw(batch);
        spriteBtnPlay.draw(batch);
        //spriteBtnSalir.draw(batch);
        batch.end();

    }

    @Override
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
                Gdx.app.exit();
            }
        }

    }

}
