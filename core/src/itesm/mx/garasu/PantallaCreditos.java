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
 * Created by rmroman on 04/02/16.
 */
class PantallaCreditos implements Screen
{
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    private Music musicaMenu;
    private Texture texturaFondo;
    private Sprite spriteFondo;

    private Texture texturaBtnInicioP;
    private Sprite spriteBtnInicioP;


    private SpriteBatch batch;




    public PantallaCreditos(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO/2, Principal.ALTO_MUNDO/2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);

        batch = new SpriteBatch();



        cargarTexturasSprites();

    }



    private void cargarTexturasSprites() {
        texturaFondo = new Texture(Gdx.files.internal("creditos.png"));
        spriteFondo = new Sprite(texturaFondo);

        texturaBtnInicioP = new Texture(Gdx.files.internal("Btn_InicioP.png"));
        spriteBtnInicioP = new Sprite(texturaBtnInicioP);
        spriteBtnInicioP.setPosition(Principal.ANCHO_MUNDO / 2 - spriteBtnInicioP.getWidth()/2+550, Principal.ALTO_MUNDO / 2 - spriteBtnInicioP.getRegionHeight() / 2 +300);
    }

    @Override
    public void render(float delta) {
        leerEntrada();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();


        spriteFondo.draw(batch);
        spriteBtnInicioP.draw(batch);

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

            if(touchX>=spriteBtnInicioP.getX()&&
                    touchX<spriteBtnInicioP.getX()+spriteBtnInicioP.getWidth()
                    && touchY>=spriteBtnInicioP.getY()
                    && touchY<=spriteBtnInicioP.getY()+spriteBtnInicioP.getHeight()){


                principal.setScreen(new PantallaMenu(principal));
            }

        }
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
        texturaBtnInicioP.dispose();


    }
}