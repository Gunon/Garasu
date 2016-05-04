package itesm.mx.garasu;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Enemigo
{
    public static final float VELOCIDAD_Y = -4f;   // Velocidad de caída
    private static final float VELOCIDAD_X = 4f;     // Velocidad horizontal
    private float mov =0;
    public boolean first=true;
    private final Sprite sprite;  // Sprite cuando no se mueve

    // Animación
    private final Animation animacionCaminata;    // Caminando
    private Animation animacionSalto;
    private float timerAnimacion;   // tiempo para calcular el frame

    // Estados del personaje
    private EstadoMovimiento estadoMovimiento;
    private EstadoSalto estadoSalto;

    // SALTO del personaje
    private static final float V0 = 40;     // Velocidad inicial del salto
    private static final float G = 9.81f;
    private static final float G_2 = G/2;   // Gravedad
    private float yInicial;         // 'y' donde inicia el salto
    private float tiempoVuelo;       // Tiempo que estará en el aire
    private float tiempoSalto;      // Tiempo actual de vuelo

    private final float movLim = 400;

    public Enemigo(Texture textura) {
        // Lee la textura como región
        TextureRegion texturaCompleta = new TextureRegion(textura);
        // La divide en frames de 16x32 (ver marioSprite.png)
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(123,181);
        // Crea la animación con tiempo de 0.25 segundos entre frames.
        animacionCaminata = new Animation(0.25f,texturaPersonaje[0][1],
                texturaPersonaje[0][2], texturaPersonaje[0][3]);
        // Animación infinita
        animacionCaminata.setPlayMode(Animation.PlayMode.LOOP);
        // Inicia el timer que contará tiempo para saber qué frame se dibuja
        timerAnimacion = 0;
        // Crea el sprite cuando para el personaje quieto (idle)
        sprite = new Sprite(texturaPersonaje[0][0]);    // quieto
        estadoMovimiento = EstadoMovimiento.INICIANDO;
        estadoSalto = EstadoSalto.EN_PISO;
    }

    // Dibuja el personaje
    public void render(SpriteBatch batch) {
        // Dibuja el personaje dependiendo del estadoMovimiento
        switch (estadoMovimiento) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                // Incrementa el timer para calcular el frame que se dibuja
                timerAnimacion += Gdx.graphics.getDeltaTime();
                // Obtiene el frame que se debe mostrar (de acuerdo al timer)
                TextureRegion region = animacionCaminata.getKeyFrame(timerAnimacion);
                // Dirección correcta

                    if (estadoMovimiento == EstadoMovimiento.MOV_IZQUIERDA) {
                        if (region.isFlipX()) {
                            region.flip(true, false);
                        }
                    } else if (estadoMovimiento == EstadoMovimiento.MOV_DERECHA) {
                        if (!region.isFlipX()) {
                            region.flip(true, false);
                        }
                    }
                    // Dibuja el frame en las coordenadas del sprite
                    batch.draw(region, sprite.getX(), sprite.getY());

                break;
            case INICIANDO:
            case QUIETO:
                sprite.draw(batch); // Dibuja el sprite
                break;
        }

    }

    // Actualiza el sprite, de acuerdo al estadoMovimiento
    public void actualizar() {
        // Ejecutar movimiento horizontal
        float nuevaX = sprite.getX();
        if(first){
            estadoMovimiento=EstadoMovimiento.MOV_DERECHA;
        }

        switch (estadoMovimiento) {
            case MOV_DERECHA:
                // Prueba que no salga del mundo
                first=false;
                nuevaX += VELOCIDAD_X;
                sprite.setX(nuevaX);
                mov+=VELOCIDAD_X;

                if(mov>= movLim){
                    mov=0;
                    estadoMovimiento = EstadoMovimiento.MOV_IZQUIERDA;
                }
                /*if (nuevaX<=Principal.ANCHO_MUNDO-sprite.getWidth()) {

                }*/
                break;
            case MOV_IZQUIERDA:
                // Prueba que no salga del mundo
                nuevaX -= VELOCIDAD_X;
                mov+=VELOCIDAD_X;

                if(mov>= movLim){
                    mov=0;
                    estadoMovimiento = EstadoMovimiento.MOV_DERECHA;
                }
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
        float y = V0 * tiempoSalto - G_2 * tiempoSalto * tiempoSalto;  // Desplazamiento desde que inició el salto
        if (tiempoSalto > tiempoVuelo / 2) { // Llegó a la altura máxima?
            // Inicia caída
            estadoSalto = EstadoSalto.BAJANDO;
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
    public void setEstadoMovimiento() {
        this.estadoMovimiento = EstadoMovimiento.QUIETO;
    }

    public void setEstadoSalto() {
        this.estadoSalto = EstadoSalto.CAIDA_LIBRE;
    }

    // Inicia el salto


    public EstadoSalto getEstadoSalto() {
        return estadoSalto;
    }

    public enum EstadoMovimiento {
        INICIANDO,
        QUIETO,
        MOV_IZQUIERDA,
        MOV_DERECHA
    }

    public enum EstadoSalto {
        EN_PISO,
        SUBIENDO,
        BAJANDO,
        CAIDA_LIBRE // Cayó de una orilla
    }
}
