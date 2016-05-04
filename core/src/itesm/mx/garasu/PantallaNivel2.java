package itesm.mx.garasu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class PantallaNivel2 implements Screen
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

    private Texture texturaGisbar;
    private Sprite spriteGisbar;
    public static final int TAM_CELDA = 16;

    private Enemigo enemigo1;
    private Enemigo enemigo2;
    private Enemigo enemigo3;
    private Enemigo enemigo4;
    private Enemigo enemigo5;
    private Enemigo enemigo6;
    private Enemigo enemigo7;
    private Enemigo enemigo8;
    private Enemigo enemigo9;
    private Enemigo enemigo10;
    private Enemigo enemigo11;
    private Texture texturaEnemigo;

    private Texture texturaPregunta;
    private Sprite spritePregunta;

    private Texture TexturaCorazonLleno;

    private Texture TexturaCorazonMedio;

    private Texture TexturaCorazonVacio;
    private Sprite vida1SpriteF;
    private Sprite vida1SpriteH;
    private Sprite vida1SpriteE;
    private Sprite vida2SpriteF;
    private Sprite vida2SpriteH;
    private Sprite vida2SpriteE;
    private Sprite vida3SpriteF;
    private Sprite vida3SpriteH;
    private Sprite vida3SpriteE;

    public int vidas = 6;

    private Array <Enemigo> enemigos = new Array<Enemigo>();

    private Texture TexturaPausa;
    private Sprite spritePausa;

    private Texture TexturaPerdio;
    private Sprite spritePerdio;

    private Texture texturaGano;
    private Sprite spriteGano;

    //puntaje
    private Texto texto;


    //
    private Texture texturaBtnPausa;
    private Boton btnPausa;

    //Boton Play
    private Texture texturaBtnInicio;
    private Boton btnInicio;

    private Texture texturaBtnReanudar;
    private Boton btnReanudar;
    private Boton btnContinuar;

    // Botones izquierda/derecha
    private Texture texturaBtnIzquierda;
    private Boton btnIzquierda;
    private Texture texturaBtnDerecha;
    private Boton btnDerecha;

    private Texture texturaBtnSalto;
    private Boton btnSalto;

    private Texture texturaBtnAtaque;
    private Boton btnAtaque;

    private Texture texturaBtnDes;
    private Boton btnDesB;
    private Boton btnDesM;
    //Estados
    private EstadosJuego estadoJuego;

    // Dibujar
    private SpriteBatch batch;

    public int countG=0;

    //Sonidos
    private Music musicaNivel2;


    public PantallaNivel2(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {
        // Se ejecuta cuando se muestra la pantalla


        camara = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camara.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camara.update();
        vista = new StretchViewport(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO,camara);



        camaraHUD = new OrthographicCamera(Principal.ANCHO_MUNDO, Principal.ALTO_MUNDO);
        camaraHUD.position.set(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2, 0);
        camaraHUD.update();

        batch = new SpriteBatch();


        crearObjetos();

        Gdx.input.setInputProcessor(new ProcesadorEntrada());

        estadoJuego= EstadosJuego.JUGANDO;

        // Texto
        texto = new Texto();
    }

    private void crearObjetos() {
        AssetManager assetManager = principal.getAssetManager();
         // Referencia al assetManager
        // Carga el mapa en memoria
        mapa = assetManager.get("Nivel2_Mapa.tmx");
        //mapa.getLayers().get(0).setVisible(false);    // Pueden ocultar una capa así
        // Crear el objeto que dibujará el mapa
        rendererMapa = new OrthogonalTiledMapRenderer(mapa,batch);
       // rendererMapa.setView(camara);
        // Cargar frames

        musicaNivel2 = assetManager.get("Nivel2.wav");
        musicaNivel2.setLooping(true);

        if(Principal.musicaT==true) {
            musicaNivel2.play();
        }

        texturaPersonaje = assetManager.get("tiraGarasu.png");
        texturaEnemigo = assetManager.get("tiraEnemigo.png");
        // Crear el personaje
        personaje = new Personaje(texturaPersonaje);
        enemigo1 = new Enemigo(texturaEnemigo);
        enemigo2 = new Enemigo(texturaEnemigo);
        enemigo3 = new Enemigo(texturaEnemigo);
        enemigo4 = new Enemigo(texturaEnemigo);
        enemigo5 = new Enemigo(texturaEnemigo);
        enemigo6 = new Enemigo(texturaEnemigo);
        enemigo7 = new Enemigo(texturaEnemigo);
        enemigo8 = new Enemigo(texturaEnemigo);
        enemigo9 = new Enemigo(texturaEnemigo);
        enemigo10 = new Enemigo(texturaEnemigo);
        enemigo11 = new Enemigo(texturaEnemigo);

        enemigos.add(enemigo1);
        enemigos.add(enemigo2);
        enemigos.add(enemigo3);
        enemigos.add(enemigo4);
        enemigos.add(enemigo5);
        enemigos.add(enemigo6);
        enemigos.add(enemigo7);
        enemigos.add(enemigo8);
        enemigos.add(enemigo9);
        enemigos.add(enemigo10);
        enemigos.add(enemigo11);
        // Posición inicial del personaje
        enemigo1.getSprite().setPosition(Principal.ANCHO_MUNDO / 2 + 1000, principal.ALTO_MUNDO / 2 - 200);
        enemigo2.getSprite().setPosition(Principal.ANCHO_MUNDO / 2 + 3000, principal.ALTO_MUNDO / 2 - 200);
        enemigo3.getSprite().setPosition(Principal.ANCHO_MUNDO / 2 + 5000, principal.ALTO_MUNDO / 2 - 200);
        enemigo4.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+4000,principal.ALTO_MUNDO /2+300);
        enemigo5.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+7000,principal.ALTO_MUNDO /2+300);
        enemigo6.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+1250,principal.ALTO_MUNDO /2+1500);
        enemigo7.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+1500,principal.ALTO_MUNDO /2+100);
        enemigo8.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+2500,principal.ALTO_MUNDO /2+100);
       enemigo9.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+6000,principal.ALTO_MUNDO /2+100);
        enemigo10.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+2500,principal.ALTO_MUNDO /2+300);
        enemigo11.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+5500,principal.ALTO_MUNDO /2+300);
      personaje.getSprite().setPosition(Principal.ANCHO_MUNDO / 2, principal.ALTO_MUNDO / 2);

        // Crear los botones
        texturaBtnIzquierda = assetManager.get("izquierda.png");
        btnIzquierda = new Boton(texturaBtnIzquierda);
        btnIzquierda.setPosicion(TAM_CELDA, 5 * 0);

        texturaBtnDerecha = assetManager.get("derecha.png");
        btnDerecha = new Boton(texturaBtnDerecha);
        btnDerecha.setPosicion(15 * TAM_CELDA, 5 * 0);

        texturaBtnSalto = assetManager.get("salto.png");
        btnSalto= new Boton(texturaBtnSalto);
        btnSalto.setPosicion(68 * TAM_CELDA, 0);

        texturaBtnAtaque = assetManager.get("Boton_atacar.png");
        btnAtaque= new Boton(texturaBtnAtaque);
        btnAtaque.setPosicion(56 * TAM_CELDA, 0);

        texturaBtnInicio = assetManager.get("Btn_InicioP.png");
        btnInicio = new Boton(texturaBtnInicio);
        btnInicio.setPosicion(Principal.ANCHO_MUNDO/2-450,Principal.ALTO_MUNDO/2-200);

        texturaBtnReanudar=assetManager.get("Btn_continuar.png");
        btnReanudar = new Boton(texturaBtnReanudar);
        btnReanudar.setPosicion(Principal.ANCHO_MUNDO/2+200,Principal.ALTO_MUNDO/2-200);
        btnContinuar = new Boton(texturaBtnReanudar);
        btnContinuar.setPosicion(Principal.ANCHO_MUNDO/2-texturaBtnReanudar.getWidth()/2,Principal.ALTO_MUNDO/2-300);
        texturaBtnPausa = assetManager.get("Btn_Pausa.png");
        btnPausa = new Boton(texturaBtnPausa);
        btnPausa.setPosicion(70 * TAM_CELDA, 38 * TAM_CELDA);
        btnPausa.setAlfa(0.7f);

        texturaBtnDes = assetManager.get("boton_decisiones.png");
        btnDesB = new Boton(texturaBtnDes);
        btnDesB.setPosicion(2 * TAM_CELDA, 3 * TAM_CELDA);

        btnDesM = new Boton(texturaBtnDes);
        btnDesM.setPosicion(40 * TAM_CELDA, 3 * TAM_CELDA);



        TexturaCorazonLleno = assetManager.get("Corazon_lleno.png");
        TexturaCorazonMedio = assetManager.get("Corazon_medio.png");
        TexturaCorazonVacio = assetManager.get("Corazon_vacio.png");
        vida1SpriteF = new Sprite(TexturaCorazonLleno);
        vida2SpriteF = new Sprite(TexturaCorazonLleno);
        vida3SpriteF = new Sprite(TexturaCorazonLleno);
        vida1SpriteH = new Sprite(TexturaCorazonMedio);
        vida2SpriteH = new Sprite(TexturaCorazonMedio);
        vida3SpriteH = new Sprite(TexturaCorazonMedio);
        vida1SpriteE = new Sprite(TexturaCorazonVacio);
        vida2SpriteE = new Sprite(TexturaCorazonVacio);
        vida3SpriteE = new Sprite(TexturaCorazonVacio);

        vida1SpriteF.setPosition(TAM_CELDA,37*TAM_CELDA);
        vida2SpriteF.setPosition(10*TAM_CELDA,37*TAM_CELDA);
        vida3SpriteF.setPosition(19*TAM_CELDA,37*TAM_CELDA);

        vida1SpriteH.setPosition(TAM_CELDA,37*TAM_CELDA);
        vida2SpriteH.setPosition(10*TAM_CELDA,37*TAM_CELDA);
        vida3SpriteH.setPosition(19*TAM_CELDA,37*TAM_CELDA);

        vida1SpriteE.setPosition(TAM_CELDA,37*TAM_CELDA);
        vida2SpriteE.setPosition(10*TAM_CELDA,37*TAM_CELDA);
        vida3SpriteE.setPosition(19*TAM_CELDA,37*TAM_CELDA);

        TexturaPausa = assetManager.get("MarcoPausa.png");
        spritePausa = new Sprite(TexturaPausa);

        texturaPregunta = assetManager.get("pregunta2.png");
        spritePregunta = new Sprite(texturaPregunta);



        texturaGisbar = assetManager.get("TiraGisbar.png");
        spriteGisbar = new Sprite(texturaGisbar);
        spriteGisbar.setPosition(Principal.ANCHO_MUNDO-texturaGisbar.getWidth()-50,Principal.ALTO_MUNDO/2-300);

        TexturaPerdio = assetManager.get("GameOver.png");
        spritePerdio = new Sprite(TexturaPerdio);

        texturaGano = assetManager.get("NivelCompletado.png");
        spriteGano = new Sprite(texturaGano);

    }

    @Override
    public void render(float delta) {
        AssetManager assetManager = principal.getAssetManager();
        // Leer
        if(estadoJuego== EstadosJuego.JUGANDO) {
            moverPersonaje();
            for(Enemigo enemigo: enemigos){
                moverEnemigo(enemigo);
            }
            actualizarCamara();
        }



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
        for(Enemigo enemigo: enemigos){
            enemigo.render(batch);
        }
        batch.end();


        batch.setProjectionMatrix(camaraHUD.combined);
        batch.begin();
        if(estadoJuego== EstadosJuego.PERDIO){
            spritePerdio.draw(batch);
            btnReanudar.render(batch);
            for(Enemigo enemigo: enemigos){
                enemigo.setEstadoMovimiento(Enemigo.EstadoMovimiento.QUIETO);
            }
        }
        if(estadoJuego== EstadosJuego.PAUSADO){
            spritePausa.draw(batch);
            btnInicio.render(batch);
            btnReanudar.render(batch);
            for(Enemigo enemigo: enemigos){
                enemigo.setEstadoMovimiento(Enemigo.EstadoMovimiento.QUIETO);
                enemigo.first=true;
            }

        }
        if(estadoJuego== EstadosJuego.FINAL){
            spriteGisbar.draw(batch);

            spritePregunta.draw(batch);
            btnDesB.render(batch);
            btnDesM.render(batch);

            for(Enemigo enemigo: enemigos){
                enemigo.setEstadoMovimiento(Enemigo.EstadoMovimiento.QUIETO);
            }
        }

        if(estadoJuego== EstadosJuego.GANO){

            spriteGano.draw(batch);
            btnContinuar.render(batch);
            for(Enemigo enemigo: enemigos){
                enemigo.setEstadoMovimiento(Enemigo.EstadoMovimiento.QUIETO);
            }
        }

        if(estadoJuego!= EstadosJuego.FINAL&&estadoJuego!= EstadosJuego.GANO) {
            btnDerecha.render(batch);
            btnIzquierda.render(batch);
            btnSalto.render(batch);
            btnPausa.render(batch);
            btnAtaque.render(batch);
        }
        switch (vidas) {
            case 6:
                vida1SpriteF.draw(batch);
                vida2SpriteF.draw(batch);
                vida3SpriteF.draw(batch);
                break;
            case 5:
                vida1SpriteF.draw(batch);
                vida2SpriteF.draw(batch);
                vida3SpriteH.draw(batch);
                break;
            case 4:
                vida1SpriteF.draw(batch);
                vida2SpriteF.draw(batch);
                vida3SpriteE.draw(batch);
                break;
            case 3:
                vida1SpriteF.draw(batch);
                vida2SpriteH.draw(batch);
                vida3SpriteE.draw(batch);
                break;
            case 2:
                vida1SpriteF.draw(batch);
                vida2SpriteE.draw(batch);
                vida3SpriteE.draw(batch);
                break;
            case 1:
                vida1SpriteH.draw(batch);
                vida2SpriteE.draw(batch);
                vida3SpriteE.draw(batch);
                break;
            case 0:
                vida1SpriteE.draw(batch);
                vida2SpriteE.draw(batch);
                vida3SpriteE.draw(batch);
                estadoJuego = EstadosJuego.PERDIO;
                break;

        }
        texto.mostrarMensaje(batch, "Puntaje : " + PantallaNivel1.gemasC, Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO * 0.95f);

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
        }
        if (posX>8960-640) {    // Si está en la última mitad
            // La cámara se queda a media pantalla antes del fin del mundo  :)
            camara.position.set(8960-640, camara.position.y, 0);
            estadoJuego= EstadosJuego.FINAL;
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
                int celdaXDer = (int) (personaje.getX()+30);
                int celdaY = (int) ((personaje.getY() + personaje.VELOCIDAD_Y) / TAM_CELDA);
                // Recuperamos la celda en esta posición
                // La capa 0 es el fondo
                TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
                TiledMapTileLayer.Cell celda = capa.getCell(celdaX, celdaY);
                TiledMapTileLayer.Cell celdaDer = capa.getCell(celdaXDer, celdaY);

              //  Gdx.app.log("Celda:", ""+celda.getTile().getId());
                // probar si la celda está ocupada
                if (celda == null/*&&celdaDer==null*/) {
                    // Celda vacía, entonces el personaje puede avanzar
                    personaje.caer();
                }else {  // Las estrellas no lo detienen :)
                    // Dejarlo sobre la celda que lo detiene
                    personaje.setPosicion((personaje.getX() + (float) 0.5), (celdaY + 1) * TAM_CELDA);
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);

                    probarChoqueParedes();
                    for(Enemigo enemigo: enemigos){
                        probarChoqueEnemigo(enemigo,personaje);
                    }



                }
                break;
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                probarChoqueParedes();
                for(Enemigo enemigo: enemigos){
                    probarChoqueEnemigo(enemigo,personaje);
                }
                break;
            case QUIETO:
                probarChoqueParedes();
                for(Enemigo enemigo: enemigos){
                    probarChoqueEnemigo(enemigo,personaje);
                }


                break;
            case ATAQUE:
                probarChoqueParedes();
                for(Enemigo enemigo: enemigos){
                    probarChoqueEnemigo(enemigo,personaje);
                }
                break;
        }
            switch (personaje.getEstadoSalto()) {
                case SUBIENDO:
                case BAJANDO:
                    probarChoqueParedes();
                    for(Enemigo enemigo: enemigos){
                        probarChoqueEnemigo(enemigo,personaje);
                    }
                    personaje.actualizarSalto();    // Actualizar posición en 'y'
                    break;
            }


        // Prueba si debe caer por llegar a un espacio vacío
        if (personaje.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO
                && (personaje.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO)) {
            // Calcula la celda donde estaría después de moverlo
            int celdaX = (int) (personaje.getX() / TAM_CELDA);
            int celdaXDer = (int) (personaje.getX() / TAM_CELDA+5);
          //  Gdx.app.log("valor",""+celdaX);
            //Gdx.app.log("valor",""+celdaXDer);

            int celdaY = (int) ((personaje.getY() + personaje.VELOCIDAD_Y) / TAM_CELDA);
            // Recuperamos la celda en esta posición
            // La capa 0 es el fondo
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
            TiledMapTileLayer.Cell celdaAbajoDer = capa.getCell(celdaXDer, celdaY);

            // probar si la celda está ocupada
            if ((celdaAbajo == null&&celdaAbajoDer==null)) {
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


    private void moverEnemigo(Enemigo enemy) {
        // Prueba caída libre inicial o movimiento horizontal
        switch (enemy.getEstadoMovimiento()) {
            case INICIANDO:     // Mueve el personaje en Y hasta que se encuentre sobre un bloque
                // Los bloques en el mapa son de 16x16
                // Calcula la celda donde estaría después de moverlo
                int celdaX = (int) (enemy.getX() / TAM_CELDA);
                int celdaY = (int) ((enemy.getY() + enemy.VELOCIDAD_Y) / TAM_CELDA);
                // Recuperamos la celda en esta posición
                // La capa 0 es el fondo
                TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
                TiledMapTileLayer.Cell celda = capa.getCell(celdaX, celdaY);


                // probar si la celda está ocupada
                if (celda == null) {
                    // Celda vacía, entonces el personaje puede avanzar
                    enemy.caer();
                }else {  // Las estrellas no lo detienen :)
                    // Dejarlo sobre la celda que lo detiene
                    enemy.setPosicion((enemy.getX() + (float) 0.5), (celdaY + 1) * TAM_CELDA);
                    enemy.setEstadoMovimiento(Enemigo.EstadoMovimiento.QUIETO);

                }
                break;

        }
        switch (enemy.getEstadoSalto()) {
            case SUBIENDO:
            case BAJANDO:
                enemy.actualizarSalto();    // Actualizar posición en 'y'
                break;
        }


        // Prueba si debe caer por llegar a un espacio vacío
        if (enemy.getEstadoMovimiento() != Enemigo.EstadoMovimiento.INICIANDO
                && (enemy.getEstadoSalto() != Enemigo.EstadoSalto.SUBIENDO)) {
            // Calcula la celda donde estaría después de moverlo
            int celdaX = (int) (enemy.getX() / TAM_CELDA);
            int celdaY = (int) ((enemy.getY() + enemy.VELOCIDAD_Y) / TAM_CELDA);
            // Recuperamos la celda en esta posición
            // La capa 0 es el fondo
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX + 1, celdaY);
            // probar si la celda está ocupada
            if ((celdaAbajo == null && celdaDerecha == null)) {
                // Celda vacía, entonces el personaje puede avanzar
                enemy.caer();
                enemy.setEstadoSalto(Enemigo.EstadoSalto.CAIDA_LIBRE);

            } else {
                // Dejarlo sobre la celda que lo detiene
                enemy.setPosicion(enemy.getX(), (celdaY + 1) * TAM_CELDA);
                enemy.actualizar();



            }

        }
    }


    private void probarChoqueEnemigo(Enemigo enemigo, Personaje personaje) {
        Rectangle a = personaje.getSprite().getBoundingRectangle();
        Rectangle b = enemigo.getSprite().getBoundingRectangle();
        if(a.overlaps(b)){

            if(personaje.getEstadoMovimiento()!= Personaje.EstadoMovimiento.ATAQUE){
                if(personaje.getDer()==true) {
                    personaje.setPosicion(personaje.getX() - 500, (int) personaje.getY());
                }
                if(personaje.getDer()==false) {
                    personaje.setPosicion(personaje.getX() + 500, (int) personaje.getY());
                }
                vidas--;
            }else if(personaje.getEstadoMovimiento()== Personaje.EstadoMovimiento.ATAQUE){
                enemigo.setPosicion(-500, -500);
                PantallaNivel1.gemasC+=100;
            }


        }
    }
    private void probarChoqueParedes() {


        float px = personaje.getX();    // Posición actual

        // Posición después de actualizar
        px = personaje.getEstadoMovimiento() == Personaje.EstadoMovimiento.MOV_DERECHA ? px + Personaje.VELOCIDAD_X :
                px - Personaje.VELOCIDAD_X;

        int celdaX = (int) (px / TAM_CELDA);   // Casilla del personaje en X
        if (personaje.getEstadoMovimiento() == Personaje.EstadoMovimiento.MOV_DERECHA) {
            celdaX++;   // Casilla del lado derecho
        }

        int celdaY = (int) (personaje.getY() / TAM_CELDA); // Casilla del personaje en Y

        TiledMapTileLayer capaprincipal = (TiledMapTileLayer) mapa.getLayers().get("Prueba");
        TiledMapTileLayer gemas = (TiledMapTileLayer) mapa.getLayers().get("Gemas");
        TiledMapTileLayer.Cell gemasCell = gemas.getCell(celdaX, celdaY);



        for(int i=0;i<7;i++){
            for (int j=0;j<5;j++){
                if (gemas.getCell(celdaX+i, celdaY+j)!=null){
                    gemas.setCell(celdaX + i, celdaY+j, null);
                    countG++;
                    if(countG==9) {
                        PantallaNivel1.gemasC += 100;
                        countG=0;
                    }
                }
            }

        }



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
        dispose();
    }



    @Override
    public void dispose() {
        AssetManager assetManager = principal.getAssetManager();
        // Cuando la PantallaMenu sale de memoria.
        // LIBERAR los recursos
      // regresamos la memoria
        assetManager.unload("Nivel2_Mapa.tmx");
        assetManager.unload("pregunta2.png");


    }

   /* Clase utilizada para manejar los eventos de touch en la pantalla
    */
    public class ProcesadorEntrada extends InputAdapter
    {
        private Vector3 coordenadas = new Vector3();
        private float x, y;     // Las coordenadas en la pantalla


        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX, screenY);
            if (estadoJuego== EstadosJuego.JUGANDO) {
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
                    estadoJuego= EstadosJuego.PAUSADO;
                }
                if(btnAtaque.contiene(x,y)){
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.ATAQUE);
                }
                if(btnInicio.contiene(x,y)&&estadoJuego== EstadosJuego.PAUSADO){
                    musicaNivel2.stop();
                    principal.setScreen(new PantallaMenu(principal));
                }
            }else if(estadoJuego== EstadosJuego.PAUSADO){
                if(btnInicio.contiene(x,y)){
                    musicaNivel2.stop();
                    principal.setScreen(new PantallaMenu(principal));
                }
                if(btnReanudar.contiene(x,y)){
                    estadoJuego = EstadosJuego.JUGANDO;
                }
            }else if(estadoJuego== EstadosJuego.PERDIO){
                if(btnReanudar.contiene(x,y)){
                    musicaNivel2.stop();
                    principal.setScreen(new PantallaNivel2(principal));

                }
            }else if(estadoJuego== EstadosJuego.FINAL){
                if(btnDesB.contiene(x,y)){
                    PantallaNivel1.gemasC+=500;
                    estadoJuego= EstadosJuego.GANO;

                }
                else if(btnDesM.contiene(x,y)){
                    PantallaNivel1.gemasC+=250;
                    estadoJuego= EstadosJuego.GANO;
                }
            }else if(estadoJuego== EstadosJuego.GANO){
                if(btnContinuar.contiene(x,y)){
                    musicaNivel2.stop();
                    principal.setScreen(new PantallaNivel3(principal));
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
        FINAL,
        JUGANDO,
        PAUSADO,
        PERDIO,
        GANO
    }

}