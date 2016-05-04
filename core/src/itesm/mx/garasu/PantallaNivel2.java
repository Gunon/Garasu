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
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer rendererMapa;
    private Personaje personaje;
    private Sprite spriteGisbar;
    private static final int TAM_CELDA = 16;
    private Sprite spritePregunta;
    private Sprite vida1SpriteF;
    private Sprite vida1SpriteH;
    private Sprite vida1SpriteE;
    private Sprite vida2SpriteF;
    private Sprite vida2SpriteH;
    private Sprite vida2SpriteE;
    private Sprite vida3SpriteF;
    private Sprite vida3SpriteH;
    private Sprite vida3SpriteE;
    private int vidas = 6;
    private final Array <Enemigo> enemigos = new Array<Enemigo>();
    private Sprite spritePausa;
    private Sprite spritePerdio;
    private Sprite spriteGano;
    private Texto texto;


    private Boton btnPausa;

    private Boton btnInicio;

    private Boton btnReanudar;
    private Boton btnContinuar;

    private Boton btnIzquierda;
    private Boton btnDerecha;

    private Boton btnSalto;

    private Boton btnAtaque;

    private Boton btnDesB;
    private Boton btnDesM;

    private EstadosJuego estadoJuego;

    private SpriteBatch batch;

    private int countG=0;
    private int gemasCL = PantallaNivel1.gemasC;
    private Music musicaNivel2;


    public PantallaNivel2(Principal principal) {
        this.principal = principal;
    }


    @Override
    public void show() {


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
        texto = new Texto();
    }

    private void crearObjetos() {
        AssetManager assetManager = principal.getAssetManager();

        mapa = assetManager.get("Nivel2_Mapa.tmx");

        rendererMapa = new OrthogonalTiledMapRenderer(mapa,batch);

        musicaNivel2 = assetManager.get("Nivel2.wav");
        musicaNivel2.setLooping(true);

        if(Principal.musicaT) {
            musicaNivel2.play();
        }

        Texture texturaPersonaje = assetManager.get("tiraGarasu.png");
        Texture texturaEnemigo = assetManager.get("tiraEnemigo.png");
        personaje = new Personaje(texturaPersonaje);
        Enemigo enemigo1 = new Enemigo(texturaEnemigo);
        Enemigo enemigo2 = new Enemigo(texturaEnemigo);
        Enemigo enemigo3 = new Enemigo(texturaEnemigo);
        Enemigo enemigo4 = new Enemigo(texturaEnemigo);
        Enemigo enemigo5 = new Enemigo(texturaEnemigo);
        Enemigo enemigo6 = new Enemigo(texturaEnemigo);
        Enemigo enemigo7 = new Enemigo(texturaEnemigo);
        Enemigo enemigo8 = new Enemigo(texturaEnemigo);
        Enemigo enemigo9 = new Enemigo(texturaEnemigo);
        Enemigo enemigo10 = new Enemigo(texturaEnemigo);
        Enemigo enemigo11 = new Enemigo(texturaEnemigo);

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
        enemigo1.getSprite().setPosition(Principal.ANCHO_MUNDO / 2 + 1000, Principal.ALTO_MUNDO / 2 - 200);
        enemigo2.getSprite().setPosition(Principal.ANCHO_MUNDO / 2 + 3000, Principal.ALTO_MUNDO / 2 - 200);
        enemigo3.getSprite().setPosition(Principal.ANCHO_MUNDO / 2 + 5000, Principal.ALTO_MUNDO / 2 - 200);
        enemigo4.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+4000, Principal.ALTO_MUNDO /2+300);
        enemigo5.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+7000, Principal.ALTO_MUNDO /2+300);
        enemigo6.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+1250, Principal.ALTO_MUNDO /2+1500);
        enemigo7.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+1500, Principal.ALTO_MUNDO /2+100);
        enemigo8.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+2500, Principal.ALTO_MUNDO /2+100);
       enemigo9.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+6000, Principal.ALTO_MUNDO /2+100);
        enemigo10.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+2500, Principal.ALTO_MUNDO /2+300);
        enemigo11.getSprite().setPosition(Principal.ANCHO_MUNDO / 2+5500, Principal.ALTO_MUNDO /2+300);
      personaje.getSprite().setPosition(Principal.ANCHO_MUNDO / 2, Principal.ALTO_MUNDO / 2);

        Texture texturaBtnIzquierda = assetManager.get("izquierda.png");
        btnIzquierda = new Boton(texturaBtnIzquierda);
        btnIzquierda.setPosicion(TAM_CELDA, 5 * 0);

        Texture texturaBtnDerecha = assetManager.get("derecha.png");
        btnDerecha = new Boton(texturaBtnDerecha);
        btnDerecha.setPosicion(15 * TAM_CELDA, 5 * 0);

        Texture texturaBtnSalto = assetManager.get("salto.png");
        btnSalto= new Boton(texturaBtnSalto);
        btnSalto.setPosicion(68 * TAM_CELDA, 0);

        Texture texturaBtnAtaque = assetManager.get("Boton_atacar.png");
        btnAtaque= new Boton(texturaBtnAtaque);
        btnAtaque.setPosicion(56 * TAM_CELDA, 0);

        Texture texturaBtnInicio = assetManager.get("Btn_InicioP.png");
        btnInicio = new Boton(texturaBtnInicio);
        btnInicio.setPosicion(Principal.ANCHO_MUNDO/2-450,Principal.ALTO_MUNDO/2-200);

        Texture texturaBtnReanudar = assetManager.get("Btn_continuar.png");
        btnReanudar = new Boton(texturaBtnReanudar);
        btnReanudar.setPosicion(Principal.ANCHO_MUNDO/2+200,Principal.ALTO_MUNDO/2-200);
        btnContinuar = new Boton(texturaBtnReanudar);
        btnContinuar.setPosicion(Principal.ANCHO_MUNDO/2- texturaBtnReanudar.getWidth()/2,Principal.ALTO_MUNDO/2-300);
        Texture texturaBtnPausa = assetManager.get("Btn_Pausa.png");
        btnPausa = new Boton(texturaBtnPausa);
        btnPausa.setPosicion(70 * TAM_CELDA, 38 * TAM_CELDA);
        btnPausa.setAlfa();

        Texture texturaBtnDes = assetManager.get("boton_decisiones.png");
        btnDesB = new Boton(texturaBtnDes);
        btnDesB.setPosicion(2 * TAM_CELDA, 3 * TAM_CELDA);

        btnDesM = new Boton(texturaBtnDes);
        btnDesM.setPosicion(40 * TAM_CELDA, 3 * TAM_CELDA);


        Texture texturaCorazonLleno = assetManager.get("Corazon_lleno.png");
        Texture texturaCorazonMedio = assetManager.get("Corazon_medio.png");
        Texture texturaCorazonVacio = assetManager.get("Corazon_vacio.png");
        vida1SpriteF = new Sprite(texturaCorazonLleno);
        vida2SpriteF = new Sprite(texturaCorazonLleno);
        vida3SpriteF = new Sprite(texturaCorazonLleno);
        vida1SpriteH = new Sprite(texturaCorazonMedio);
        vida2SpriteH = new Sprite(texturaCorazonMedio);
        vida3SpriteH = new Sprite(texturaCorazonMedio);
        vida1SpriteE = new Sprite(texturaCorazonVacio);
        vida2SpriteE = new Sprite(texturaCorazonVacio);
        vida3SpriteE = new Sprite(texturaCorazonVacio);

        vida1SpriteF.setPosition(TAM_CELDA,37*TAM_CELDA);
        vida2SpriteF.setPosition(10*TAM_CELDA,37*TAM_CELDA);
        vida3SpriteF.setPosition(19*TAM_CELDA,37*TAM_CELDA);

        vida1SpriteH.setPosition(TAM_CELDA,37*TAM_CELDA);
        vida2SpriteH.setPosition(10*TAM_CELDA,37*TAM_CELDA);
        vida3SpriteH.setPosition(19*TAM_CELDA,37*TAM_CELDA);

        vida1SpriteE.setPosition(TAM_CELDA,37*TAM_CELDA);
        vida2SpriteE.setPosition(10*TAM_CELDA,37*TAM_CELDA);
        vida3SpriteE.setPosition(19*TAM_CELDA,37*TAM_CELDA);

        Texture texturaPausa = assetManager.get("MarcoPausa.png");
        spritePausa = new Sprite(texturaPausa);

        Texture texturaPregunta = assetManager.get("pregunta2.png");
        spritePregunta = new Sprite(texturaPregunta);


        Texture texturaGisbar = assetManager.get("TiraGisbar.png");
        spriteGisbar = new Sprite(texturaGisbar);
        spriteGisbar.setPosition(Principal.ANCHO_MUNDO- texturaGisbar.getWidth()-50,Principal.ALTO_MUNDO/2-300);

        Texture texturaPerdio = assetManager.get("GameOver.png");
        spritePerdio = new Sprite(texturaPerdio);

        Texture texturaGano = assetManager.get("NivelCompletado.png");
        spriteGano = new Sprite(texturaGano);

    }

    @Override
    public void render(float delta) {
        AssetManager assetManager = principal.getAssetManager();
        if(estadoJuego== EstadosJuego.JUGANDO) {
            moverPersonaje();
            for(Enemigo enemigo: enemigos){
                moverEnemigo(enemigo);
            }
            actualizarCamara();
        }



        assetManager.update();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camara.combined);

        rendererMapa.setView(camara);
        rendererMapa.render();

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
                enemigo.setEstadoMovimiento();
            }
        }
        if(estadoJuego== EstadosJuego.PAUSADO){
            spritePausa.draw(batch);
            btnInicio.render(batch);
            btnReanudar.render(batch);
            for(Enemigo enemigo: enemigos){
                enemigo.setEstadoMovimiento();
                enemigo.first=true;
            }

        }
        if(estadoJuego== EstadosJuego.FINAL){
            spriteGisbar.draw(batch);

            spritePregunta.draw(batch);
            btnDesB.render(batch);
            btnDesM.render(batch);

            for(Enemigo enemigo: enemigos){
                enemigo.setEstadoMovimiento();
            }
        }

        if(estadoJuego== EstadosJuego.GANO){

            spriteGano.draw(batch);
            btnContinuar.render(batch);
            for(Enemigo enemigo: enemigos){
                enemigo.setEstadoMovimiento();
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
        if (posX>=Principal.ANCHO_MUNDO/2 ) {
            camara.position.set((int)posX, camara.position.y, 0);
        }
        if (posX>8960-640) {
            camara.position.set(8960-640, camara.position.y, 0);
            estadoJuego= EstadosJuego.FINAL;
        }
        camara.update();

    }

    private void moverPersonaje() {
        switch (personaje.getEstadoMovimiento()) {
            case INICIANDO:
                int celdaX = (int) (personaje.getX() / TAM_CELDA);
                int celdaXDer = (int) (personaje.getX()+50);
                int celdaY = (int) ((personaje.getY() + Personaje.VELOCIDAD_Y) / TAM_CELDA);

                TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
                TiledMapTileLayer.Cell celda = capa.getCell(celdaX, celdaY);
                TiledMapTileLayer.Cell celdaDer = capa.getCell(celdaXDer, celdaY);

                if (celda == null&&celdaDer==null) {
                    personaje.caer();
                }else {
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
                    personaje.actualizarSalto();
                    break;
            }


        if (personaje.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO
                && (personaje.getEstadoSalto() != Personaje.EstadoSalto.SUBIENDO)) {
            int celdaX = (int) (personaje.getX() / TAM_CELDA);
            int celdaXDer = (int) (personaje.getX() / TAM_CELDA+5);

            int celdaY = (int) ((personaje.getY() + Personaje.VELOCIDAD_Y) / TAM_CELDA);

            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
            TiledMapTileLayer.Cell celdaAbajoDer = capa.getCell(celdaXDer, celdaY);

            if ((celdaAbajo == null&&celdaAbajoDer==null)) {
                personaje.caer();
                personaje.setEstadoSalto(Personaje.EstadoSalto.CAIDA_LIBRE);

            } else {
                personaje.setPosicion(personaje.getX(), (celdaY + 1) * TAM_CELDA);
                personaje.setEstadoSalto(Personaje.EstadoSalto.EN_PISO);





            }

        }
    }


    private void moverEnemigo(Enemigo enemy) {
        switch (enemy.getEstadoMovimiento()) {
            case INICIANDO:
                int celdaX = (int) (enemy.getX() / TAM_CELDA);
                int celdaY = (int) ((enemy.getY() + Enemigo.VELOCIDAD_Y) / TAM_CELDA);

                TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
                TiledMapTileLayer.Cell celda = capa.getCell(celdaX, celdaY);


                if (celda == null) {
                    enemy.caer();
                }else {
                    enemy.setPosicion((enemy.getX() + (float) 0.5), (celdaY + 1) * TAM_CELDA);
                    enemy.setEstadoMovimiento();

                }
                break;

        }
        switch (enemy.getEstadoSalto()) {
            case SUBIENDO:
            case BAJANDO:
                enemy.actualizarSalto();
                break;
        }


        if (enemy.getEstadoMovimiento() != Enemigo.EstadoMovimiento.INICIANDO
                && (enemy.getEstadoSalto() != Enemigo.EstadoSalto.SUBIENDO)) {
            int celdaX = (int) (enemy.getX() / TAM_CELDA);
            int celdaY = (int) ((enemy.getY() + Enemigo.VELOCIDAD_Y) / TAM_CELDA);
            TiledMapTileLayer capa = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
            TiledMapTileLayer.Cell celdaAbajo = capa.getCell(celdaX, celdaY);
            TiledMapTileLayer.Cell celdaDerecha = capa.getCell(celdaX + 1, celdaY);
            if ((celdaAbajo == null && celdaDerecha == null)) {
                enemy.caer();
                enemy.setEstadoSalto();

            } else {
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
                if(personaje.getDer()) {
                    personaje.setPosicion(personaje.getX() - 500, (int) personaje.getY());
                }
                if(!personaje.getDer()) {
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


        float px = personaje.getX();
        px = personaje.getEstadoMovimiento() == Personaje.EstadoMovimiento.MOV_DERECHA ? px + Personaje.VELOCIDAD_X :
                px - Personaje.VELOCIDAD_X;

        int celdaX = (int) (px / TAM_CELDA);
        if (personaje.getEstadoMovimiento() == Personaje.EstadoMovimiento.MOV_DERECHA) {
            celdaX++;
        }

        int celdaY = (int) (personaje.getY() / TAM_CELDA);
        TiledMapTileLayer capaprincipal = (TiledMapTileLayer) mapa.getLayers().get("Plataformas");
        TiledMapTileLayer gemas = (TiledMapTileLayer) mapa.getLayers().get("Gemas");




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




    }
    public class ProcesadorEntrada extends InputAdapter
    {
        private final Vector3 coordenadas = new Vector3();
        private float x, y;


        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX, screenY);
            if (estadoJuego== EstadosJuego.JUGANDO) {
                if (btnDerecha.contiene(x, y) && personaje.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO) {
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.MOV_DERECHA);
                } else if (btnIzquierda.contiene(x, y) && personaje.getEstadoMovimiento() != Personaje.EstadoMovimiento.INICIANDO) {
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.MOV_IZQUIERDA);
                } else if (btnSalto.contiene(x, y)) {
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
                    PantallaNivel1.gemasC = gemasCL;
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
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            transformarCoordenadas(screenX, screenY);
            if ( personaje.getEstadoMovimiento()!= Personaje.EstadoMovimiento.INICIANDO && (btnDerecha.contiene(x, y) || btnIzquierda.contiene(x,y)) ) {
                personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
            }
            return true;
        }
        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            transformarCoordenadas(screenX, screenY);
            if (x< Principal.ANCHO_MUNDO /2 && personaje.getEstadoMovimiento()!= Personaje.EstadoMovimiento.QUIETO) {
                if (!btnIzquierda.contiene(x, y) && !btnDerecha.contiene(x, y) ) {
                    personaje.setEstadoMovimiento(Personaje.EstadoMovimiento.QUIETO);
                }
            }
            return true;
        }


        private void transformarCoordenadas(int screenX, int screenY) {
            coordenadas.set(screenX, screenY, 0);
            camaraHUD.unproject(coordenadas);
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