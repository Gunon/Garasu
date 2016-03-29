package itesm.mx.whacamole;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Timer;
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

    //MAPA
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;

    //Personaje
    private Personaje personaje;
    private Texture texturaPersonaje;
    public static final int TAM_CELDA = 16;




    //
    private Texture texturaBtnPausa;
    private Boton btnPausa;

    // Botones izquierda/derecha
    private Texture texturaBtnIzquierda;
    private Boton btnIzquierda;
    private Texture texturaBtnDerecha;
    private Boton btnDerecha;

    private Texture texturaBtnSalto;
    private Boton btnSalto;

    //Estados
    private EstadosJuego estadoJuego;

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
        assetManager.setLoader(TiledMap.class,
                new TmxMapLoader(new InternalFileHandleResolver()));
        batch = new SpriteBatch();

        cargarRecursos();

        crearObjetos();
        Gdx.input.setInputProcessor(new ProcesadorEntrada());

        estadoJuego=EstadosJuego.JUGANDO;

    }

    private void crearObjetos() {
         // Referencia al assetManager
        // Carga el mapa en memoria
        mapa = assetManager.get("Nivel_1_LargeMap.tmx");
        //mapa.getLayers().get(0).setVisible(false);    // Pueden ocultar una capa así
        // Crear el objeto que dibujará el mapa
        rendererMapa = new OrthogonalTiledMapRenderer(mapa,batch);
        rendererMapa.setView(camara);
        // Cargar frames
        texturaPersonaje = assetManager.get("TiraGarasu_caminado.png");
        // Crear el personaje
        personaje = new Personaje(texturaPersonaje);
        // Posición inicial del personaje
      personaje.getSprite().setPosition(Principal.ANCHO_MUNDO / 2, principal.ALTO_MUNDO /2);

        // Crear los botones
        texturaBtnIzquierda = assetManager.get("izquierda.png");
        btnIzquierda = new Boton(texturaBtnIzquierda);
        btnIzquierda.setPosicion(TAM_CELDA, 5 * TAM_CELDA);
        btnIzquierda.setAlfa(0.7f); // Un poco de transparencia
        texturaBtnDerecha = assetManager.get("derecha.png");
        btnDerecha = new Boton(texturaBtnDerecha);
        btnDerecha.setPosicion(6 * TAM_CELDA, 5 * TAM_CELDA);
        btnDerecha.setAlfa(0.7f); // Un poco de transparencia

        texturaBtnSalto = assetManager.get("salto.png");
        btnSalto= new Boton(texturaBtnDerecha);
        btnSalto.setPosicion(2 * TAM_CELDA, 8 * TAM_CELDA);
        btnSalto.setAlfa(0.7f); // Un poco de transparencia

        texturaBtnPausa = assetManager.get("Btn_Pausa.png");
        btnPausa = new Boton(texturaBtnPausa);
        btnPausa.setPosicion(70*TAM_CELDA,38*TAM_CELDA);
        btnPausa.setAlfa(0.7f);


    }

    private void cargarRecursos() {

        assetManager.load("Nivel_1_LargeMap.tmx", TiledMap.class);
        assetManager.load("izquierda.png", Texture.class);
        assetManager.load("derecha.png", Texture.class);
        assetManager.load("TiraGarasu_caminado.png", Texture.class);
        assetManager.load("Btn_Pausa.png", Texture.class);
        assetManager.load("salto.png", Texture.class);
        assetManager.finishLoading();






    }



    @Override
    public void render(float delta) {
        // Leer
        moverPersonaje();
        actualizarCamara();

        /*leerEntrada();*/
        assetManager.update();
        // Actualizar objetos
        // Borrar la pantalla
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Proyectamos la cámara sobre batch
        batch.setProjectionMatrix(camara.combined);

        rendererMapa.setView(camara);
        rendererMapa.render();
        // Dibujamos

        batch.begin();
        personaje.render(batch);
        batch.end();


        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();
        btnDerecha.render(batch);
        btnIzquierda.render(batch);
        btnSalto.render(batch);
        btnPausa.render(batch);
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
       float posX = personaje.getX();
        // Si está en la parte 'media'
        if (posX>=Principal.ANCHO_MUNDO/2 /*&& posX<=Principal.ANCHO_MUNDO/2*/) {
            // El personaje define el centro de la cámara
            camara.position.set((int)posX, camara.position.y, 0);
        } else if (posX>Principal.ANCHO_MUNDO/2) {    // Si está en la última mitad
            // La cámara se queda a media pantalla antes del fin del mundo  :)
            camara.position.set(Principal.ANCHO_MUNDO/2, camara.position.y, 0);
        }
        camara.update();

    }

    private void moverPersonaje() {
        // Prueba caída libre inicial o movimiento horizontal
        switch (personaje.getEstadoMovimiento()) {
            case INICIANDO:     // Mueve el personaje en Y hasta que se encuentre sobre un bloque
                // Los bloques en el mapa son de 16x16
                // Calcula la celda donde estaría después de moverlo
                int celdaX = (int) (personaje.getX() / TAM_CELDA);
                int celdaY = (int) ((personaje.getY() + personaje.VELOCIDAD_Y) / TAM_CELDA);
                // Recuperamos la celda en esta posición
                // La capa 0 es el fondo
                TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Piso");
                TiledMapTileLayer.Cell celda = capa.getCell(celdaX, celdaY);
                // probar si la celda está ocupada
                if (celda == null) {
                    // Celda vacía, entonces el personaje puede avanzar
                    personaje.caer();
                }
                break;
            case MOV_DERECHA:       // Se mueve horizontal
            case MOV_IZQUIERDA:
                probarChoqueParedes();      // Prueba si debe moverse
                break;
        }
            switch (personaje.getEstadoSalto()) {
                case SUBIENDO:
                case BAJANDO:
                    personaje.actualizarSalto();    // Actualizar posición en 'y'
                    break;
            }


        // Prueba si debe caer por llegar a un espacio vacío
        if (personaje.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO
                && (personaje.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO)) {
            // Calcula la celda donde estaría después de moverlo
            int celdaX = (int) (personaje.getX() / TAM_CELDA);
            int celdaY = (int) ((personaje.getY() + personaje.VELOCIDAD_Y) / TAM_CELDA);
            // Recuperamos la celda en esta posición
            // La capa 0 es el fondo
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Piso");
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX + 1, celdaY);
            // probar si la celda está ocupada
            if ((celdaAbajo == null && celdaDerecha == null)) {
                // Celda vacía, entonces el personaje puede avanzar
                personaje.caer();
                personaje.setEstadoSalto(Personaje.EstadoSalto.CAIDA_LIBRE);
            } else {
                // Dejarlo sobre la celda que lo detiene
                personaje.setPosicion(personaje.getX(), (celdaY + 1) * TAM_CELDA);
                personaje.setEstadoSalto(Personaje.EstadoSalto.EN_PISO);


            }
        }
    }

    private void probarChoqueParedes() {
        Personaje.EstadoMovimiento estado = personaje.getEstadoMovimiento();
        // Quitar porque este método sólo se llama cuando se está moviendo
        if ( estado!= Personaje.EstadoMovimiento.MOV_DERECHA && estado!=Personaje.EstadoMovimiento.MOV_IZQUIERDA){
            return;
        }
        float px = personaje.getX();    // Posición actual
        // Posición después de actualizar
        px = personaje.getEstadoMovimiento()==Personaje.EstadoMovimiento.MOV_DERECHA? px+Personaje.VELOCIDAD_X:
                px-Personaje.VELOCIDAD_X;
        int celdaX = (int)(px/TAM_CELDA);   // Casilla del personaje en X
        if (personaje.getEstadoMovimiento()== Personaje.EstadoMovimiento.MOV_DERECHA) {
            celdaX++;   // Casilla del lado derecho
        }
        int celdaY = (int)(personaje.getY()/TAM_CELDA); // Casilla del personaje en Y
        TiledMapTileLayer capaprincipal = (TiledMapTileLayer) mapa.getLayers().get("Piso");
        if ( capaprincipal.getCell(celdaX,celdaY) != null || capaprincipal.getCell(celdaX,celdaY+1) != null ) {
            // Colisionará, dejamos de moverlo


                personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }else {
            personaje.actualizar();
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }



    @Override
    public void dispose() {
        // Cuando la PantallaMenu sale de memoria.
        // LIBERAR los recursos
      // regresamos la memoria
        texturaBtnPausa.dispose();

        texturaPersonaje.dispose();

    }

   /* Clase utilizada para manejar los eventos de touch en la pantalla
    */
    public class ProcesadorEntrada extends InputAdapter
    {
        private Vector3 coordenadas = new Vector3();
        private float x, y;     // Las coordenadas en la pantalla

        /*
        Se ejecuta cuando el usuario PONE un dedo sobre la pantalla, los dos primeros parámetros
        son las coordenadas relativas a la pantalla física (0,0) en la esquina superior izquierda
        pointer - es el número de dedo que se pone en la pantalla, el primero es 0
        button - el botón del mouse
         */
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX, screenY);
            if (estadoJuego==EstadosJuego.JUGANDO) {
                // Preguntar si las coordenadas están sobre el botón derecho
                if (btnDerecha.contiene(x, y) && personaje.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO) {
                    // Tocó el botón derecha, hacer que el personaje se mueva a la derecha
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.MOV_DERECHA);
                } else if (btnIzquierda.contiene(x, y) && personaje.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO) {
                    // Tocó el botón izquierda, hacer que el personaje se mueva a la izquierda
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.MOV_IZQUIERDA);
                } else if (btnSalto.contiene(x, y)) {
                    // Tocó el botón saltar
                    personaje.saltar();
                }else
                if(btnPausa.contiene(x,y)){
                    principal.setScreen(new PantallaOpciones(principal));
                }
            }
            return true;    // Indica que ya procesó el evento
        }

        /*
        Se ejecuta cuando el usuario QUITA el dedo de la pantalla.
         */
        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX, screenY);
            // Preguntar si las coordenadas son de algún botón para DETENER el movimiento
            if ( personaje.getEstadoMovimiento()!= Personaje.EstadoMovimiento.INICIANDO && (btnDerecha.contiene(x, y) || btnIzquierda.contiene(x,y)) ) {
                // Tocó el botón derecha, hacer que el personaje se mueva a la derecha
                personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
            return true;    // Indica que ya procesó el evento
        }


        // Se ejecuta cuando el usuario MUEVE el dedo sobre la pantalla
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            transformarCoordenadas(screenX, screenY);
            // Acaba de salir de las fechas (y no es el botón de salto)
            if (x<principal.ANCHO_MUNDO/2 && personaje.getEstadoMovimiento()!= Personaje.EstadoMovimiento.QUIETO) {
                if (!btnIzquierda.contiene(x, y) && !btnDerecha.contiene(x, y) ) {
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                }
            }
            return true;
        }


        private void transformarCoordenadas(int screenX, int screenY) {
            // Transformar las coordenadas de la pantalla física a la cámara HUD
            coordenadas.set(screenX, screenY, 0);
            camaraHUD.unproject(coordenadas);
            // Obtiene las coordenadas relativas a la pantalla virtual
            x = coordenadas.x;
            y = coordenadas.y;
        }
    }

    public enum EstadosJuego {
        GANO,
        JUGANDO,
        PAUSADO,
        PERDIO
    }

}