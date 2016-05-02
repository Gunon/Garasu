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


public class PantallaHistoria implements Screen
{
    private final Principal principal;
    private OrthographicCamera camara;
    private Viewport vista;

    // Fondo
    private Texture texturaFondo;
    private Sprite spriteFondo;

    private EstadosPantalla estadosPantalla;

    //Instrucciones
    private Texture texturaHistoria1;
    private Sprite spriteHistoria1;

    private Texture texturaHistoria2;
    private Sprite spriteHistoria2;

    private Texture texturaHistoria3;
    private Sprite spriteHistoria3;


    private Texture texturaHistoria4;
    private Sprite spriteHistoria4;


    private Texture texturaHistoria5;
    private Sprite spriteHistoria5;


    private Texture texturaHistoria6;
    private Sprite spriteHistoria6;

    private Texture texturaTexto1;
    private Sprite spriteTexto1;


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


    public PantallaHistoria(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
        estadosPantalla= EstadosPantalla.IN1;
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

        texturaTexto1 = new Texture(Gdx.files.internal("Texto.png"));
        spriteTexto1 = new Sprite(texturaTexto1);
        spriteTexto1.setPosition(0,0);



        texturaHistoria1 = new Texture(Gdx.files.internal("F1.jpg"));
        spriteHistoria1 = new Sprite(texturaHistoria1);
        spriteHistoria1.setPosition(0,0);

        texturaHistoria2 = new Texture(Gdx.files.internal("F2.jpg"));
        spriteHistoria2 = new Sprite(texturaHistoria2);
        spriteHistoria2.setPosition(0,0);

        texturaHistoria3 = new Texture(Gdx.files.internal("F3.jpg"));
        spriteHistoria3 = new Sprite(texturaHistoria3);
        spriteHistoria3.setPosition(0,0);

        texturaHistoria4 = new Texture(Gdx.files.internal("F4.jpg"));
        spriteHistoria4 = new Sprite(texturaHistoria4);
        spriteHistoria4.setPosition(0,0);

        texturaHistoria5 = new Texture(Gdx.files.internal("F5.jpg"));
        spriteHistoria5 = new Sprite(texturaHistoria5);
        spriteHistoria5.setPosition(0,0);

        texturaHistoria6 = new Texture(Gdx.files.internal("F6.jpg"));
        spriteHistoria6 = new Sprite(texturaHistoria6);
        spriteHistoria6.setPosition(0,0);
//hola
        //BtnInicio


        texturaBtnContinuar= new Texture(Gdx.files.internal("derecha.png"));
        spriteBtnContinuar= new Sprite(texturaBtnContinuar);
        spriteBtnContinuar.setPosition(Principal.ANCHO_MUNDO / 2+450 , Principal.ALTO_MUNDO/2-350);





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


        if(estadosPantalla== EstadosPantalla.IN1){
            spriteHistoria1.draw(batch);
            spriteTexto1.draw(batch);
            spriteBtnContinuar.draw(batch);
        }
        if(estadosPantalla== EstadosPantalla.IN2){

            spriteHistoria2.draw(batch);
            spriteTexto1.draw(batch);

            spriteBtnContinuar.draw(batch);
        }
        if(estadosPantalla== EstadosPantalla.IN3){

            spriteHistoria3.draw(batch);
            spriteTexto1.draw(batch);

            spriteBtnContinuar.draw(batch);
        }
        if(estadosPantalla== EstadosPantalla.IN4){

            spriteHistoria4.draw(batch);
            spriteTexto1.draw(batch);

            spriteBtnContinuar.draw(batch);
        }
        if(estadosPantalla== EstadosPantalla.IN5){

            spriteHistoria5.draw(batch);
            spriteTexto1.draw(batch);

            spriteBtnContinuar.draw(batch);
        }
        if(estadosPantalla== EstadosPantalla.IN6){

            spriteHistoria6.draw(batch);
            spriteTexto1.draw(batch);

            spriteBtnContinuar.draw(batch);
        }

/*
        spriteBtnInicio.draw(batch);
*/

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


            if(touchX>=spriteBtnContinuar.getX()&&
                    touchX<spriteBtnContinuar.getX()+spriteBtnContinuar.getWidth()
                    && touchY>=spriteBtnContinuar.getY()
                    && touchY<=spriteBtnContinuar.getY()+spriteBtnContinuar.getHeight()){
                switch (estadosPantalla){
                    case IN1:
                        estadosPantalla = EstadosPantalla.IN2;
                        break;
                    case IN2:
                        estadosPantalla = EstadosPantalla.IN3;
                        break;
                    case IN3:
                        estadosPantalla = EstadosPantalla.IN4;
                        break;
                    case IN4:
                        estadosPantalla = EstadosPantalla.IN5;
                        break;
                    case IN5:
                        estadosPantalla = EstadosPantalla.IN6;
                        break;
                    case IN6:
                        principal.setScreen(new PantallaNivel2(principal));
                        break;
                }

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
        IN2,
        IN3,
        IN4,
        IN5,
        IN6
    }
}