package itesm.mx.garasu;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Personaje
{
    public static final float VELOCIDAD_Y = -4f;   // Velocidad de caída
    public static final float VELOCIDAD_X = 7;     // Velocidad horizontal

    private Sprite sprite;  // Sprite cuando no se mueve

    // Animación
    private Animation animacionCaminata;    // Caminando
    private Animation animacionSalto;
    private Animation animacionAtaque;
    private Animation animacionQuieto;

    private float timerAnimacion;   // tiempo para calcular el frame

    // Estados del personaje
    private EstadoMovimiento estadoMovimiento;
    private EstadoSalto estadoSalto;

    // SALTO del personaje
    private static final float V0 = 50;     // Velocidad inicial del salto
    private static final float G = 9.81f;
    private static final float G_2 = G/2;   // Gravedad
    private float yInicial;         // 'y' donde inicia el salto
    private float tiempoVuelo;       // Tiempo que estará en el aire
    private float tiempoSalto;      // Tiempo actual de vuelo
    public static boolean der = true;
    /*
    Constructor del personaje, recibe una imagen con varios frames, (ver imagen marioSprite.png)
     */
    public Personaje(Texture textura) {
        // Lee la textura como región
        TextureRegion texturaCompleta = new TextureRegion(textura);
        // La divide en frames de 16x32 (ver marioSprite.png)
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(119,140);
        // Crea la animación con tiempo de 0.25 segundos entre frames.
        animacionQuieto = new Animation(0.12f,texturaPersonaje[0][0]);
        animacionCaminata = new Animation(0.12f,texturaPersonaje[0][0],
                texturaPersonaje[0][1], texturaPersonaje[0][2],texturaPersonaje[0][3] );
        animacionAtaque =    new Animation(0.12f,texturaPersonaje[0][4],
                texturaPersonaje[0][5], texturaPersonaje[0][6],texturaPersonaje[0][7] );
        animacionSalto =    new Animation(0.12f,
                 texturaPersonaje[0][9]);
        animacionSalto.setPlayMode(Animation.PlayMode.LOOP);
        animacionAtaque.setPlayMode(Animation.PlayMode.LOOP);
        // Animación infinita
        animacionCaminata.setPlayMode(Animation.PlayMode.LOOP);
        // Inicia el timer que contará tiempo para saber qué frame se dibuja
        timerAnimacion = 0;
        // Crea el sprite cuando para el personaje quieto (idle)
        sprite = new Sprite(texturaPersonaje[0][0]);    // quieto
        estadoMovimiento = EstadoMovimiento.INICIANDO;
        estadoSalto = EstadoSalto.EN_PISO;
    }

    public boolean getDer(){
        return der;
    }
    // Dibuja el personaje
    public void render(SpriteBatch batch) {
        // Dibuja el personaje dependiendo del estadoMovimiento
        System.out.println(der);
        switch (estadoMovimiento) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:

                // Incrementa el timer para calcular el frame que se dibuja
                timerAnimacion += Gdx.graphics.getDeltaTime();
                // Obtiene el frame que se debe mostrar (de acuerdo al timer)
                TextureRegion region = animacionCaminata.getKeyFrame(timerAnimacion);
                // Dirección correcta
                if (estadoSalto == EstadoSalto.EN_PISO) {
                    if (estadoMovimiento == EstadoMovimiento.MOV_IZQUIERDA) {
                        if (!region.isFlipX()) {
                            region.flip(true, false);
                        }
                    } else if (estadoMovimiento == EstadoMovimiento.MOV_DERECHA) {
                        if (region.isFlipX()) {
                            region.flip(true, false);
                        }
                    }
                    // Dibuja el frame en las coordenadas del sprite
                    batch.draw(region, sprite.getX(), sprite.getY());
                }
                break;
            case ATAQUE:
                System.out.println(der);
                timerAnimacion += Gdx.graphics.getDeltaTime();
                // Obtiene el frame que se debe mostrar (de acuerdo al timer)

                TextureRegion regionAtaque = animacionAtaque.getKeyFrame(timerAnimacion);
                if (der == true && regionAtaque.isFlipX() ) {
                    regionAtaque.flip(true, false);
                } else if (der == false && !regionAtaque.isFlipX()) {
                    regionAtaque.flip(true, false);


                }
                batch.draw(regionAtaque, sprite.getX(), sprite.getY());
                break;
            case INICIANDO:
            case QUIETO:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion regionQuieto = animacionQuieto.getKeyFrame(timerAnimacion);
                if (estadoSalto == EstadoSalto.EN_PISO && estadoMovimiento != EstadoMovimiento.ATAQUE) {
                    if (der == true && regionQuieto.isFlipX()) {
                        regionQuieto.flip(true, false);
                    } else if (der == false && !regionQuieto.isFlipX()) {
                        regionQuieto.flip(true, false);
                    }

                    batch.draw(regionQuieto, sprite.getX(), sprite.getY());
                    break;
                }
        }
        if((estadoSalto==EstadoSalto.BAJANDO||estadoSalto==EstadoSalto.SUBIENDO||estadoSalto==EstadoSalto.CAIDA_LIBRE)&&estadoMovimiento!=EstadoMovimiento.ATAQUE){
            timerAnimacion += Gdx.graphics.getDeltaTime();
            // Obtiene el frame que se debe mostrar (de acuerdo al timer)
            TextureRegion region = animacionSalto.getKeyFrame(timerAnimacion);
            if (estadoMovimiento == EstadoMovimiento.MOV_IZQUIERDA) {
                if (!region.isFlipX()) {
                    region.flip(true, false);

                }
            } else if (estadoMovimiento == EstadoMovimiento.MOV_DERECHA) {
                if (region.isFlipX()) {
                    region.flip(true, false);

                }
            }
            batch.draw(region,sprite.getX(),sprite.getY());
        }

    }

    // Actualiza el sprite, de acuerdo al estadoMovimiento
    public void actualizar() {
        // Ejecutar movimiento horizontal
        float nuevaX = sprite.getX();
        switch (estadoMovimiento) {
            case MOV_DERECHA:
                // Prueba que no salga del mundo
                nuevaX += VELOCIDAD_X;
                sprite.setX(nuevaX);
                der=true;
                /*if (nuevaX<=Principal.ANCHO_MUNDO-sprite.getWidth()) {

                }*/
                break;
            case MOV_IZQUIERDA:
                // Prueba que no salga del mundo

                der=false;
                nuevaX -= VELOCIDAD_X;
                if (nuevaX>=0) {
                    sprite.setX(nuevaX);
                }
                break;
        }
    }

    // Avanza en su caída
    public void caer() {
        sprite.setY(sprite.getY() + VELOCIDAD_Y);
    }

    // Actualiza la posición en 'y', está saltando
    public void actualizarSalto() {
        // Ejecutar movimiento vertical
        float y = (V0 * tiempoSalto) - G_2 * tiempoSalto * tiempoSalto;  // Desplazamiento desde que inició el salto
        if (tiempoSalto > tiempoVuelo / 2) { // Llegó a la altura máxima?
            // Inicia caída
            estadoSalto = EstadoSalto.BAJANDO;
        }
        float nuevaX = sprite.getX();

        switch (estadoMovimiento) {
            case MOV_DERECHA:
                // Prueba que no salga del mundo
                nuevaX -= VELOCIDAD_X;
                sprite.setX(nuevaX);
                break;
            case MOV_IZQUIERDA:
                // Prueba que no salga del mundo
                nuevaX += VELOCIDAD_X;
                if (nuevaX>=0) {
                    sprite.setX(nuevaX);
                }
                break;
        }

        tiempoSalto += 10 * Gdx.graphics.getDeltaTime();  // Actualiza tiempo
        sprite.setY(yInicial + y);    // Actualiza posición
        if (y < 0) {
            // Regresó al piso
            sprite.setY(yInicial);  // Lo deja donde inició el salto
            estadoSalto = EstadoSalto.EN_PISO;  // Ya no está saltando
        }
    }

    // Accesor de la variable sprite
    public Sprite getSprite() {
        return sprite;
    }

    // Accesores para la posición
    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public void setPosicion(float x, int y) {
        sprite.setPosition(x,y);
    }

    // Accesor del estadoMovimiento
    public EstadoMovimiento getEstadoMovimiento() {
        return estadoMovimiento;
    }

    // Modificador del estadoMovimiento
    public void setEstadoMovimiento(EstadoMovimiento estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public void setEstadoSalto(EstadoSalto estadoSalto) {
        this.estadoSalto = estadoSalto;
    }

    // Inicia el salto
    public void saltar() {
        if (estadoSalto==EstadoSalto.EN_PISO) {
            tiempoSalto = 0;
            yInicial = sprite.getY();
            estadoSalto = EstadoSalto.SUBIENDO;
            tiempoVuelo = 2 * V0 / G;
        }
    }

    public EstadoSalto getEstadoSalto() {
        return estadoSalto;
    }

    public enum EstadoMovimiento {
        INICIANDO,
        QUIETO,
        MOV_IZQUIERDA,
        MOV_DERECHA,
        ATAQUE
    }

    public enum EstadoSalto {
        EN_PISO,
        SUBIENDO,
        BAJANDO,
        CAIDA_LIBRE // Cayó de una orilla
    }
}
