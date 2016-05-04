package itesm.mx.garasu;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Enemigo
{
    public static final float VELOCIDAD_Y = -4f;
    private static final float VELOCIDAD_X = 4f;
    private float mov =0;
    public boolean first=true;
    private final Sprite sprite;


    private final Animation animacionCaminata;
    private float timerAnimacion;

    private EstadoMovimiento estadoMovimiento;
    private EstadoSalto estadoSalto;

    private static final float V0 = 40;
    private static final float G = 9.81f;
    private static final float G_2 = G/2;
    private float yInicial;
    private float tiempoVuelo;
    private float tiempoSalto;

    private final float movLim = 400;

    public Enemigo(Texture textura) {
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(123,181);
        animacionCaminata = new Animation(0.25f,texturaPersonaje[0][1],
                texturaPersonaje[0][2], texturaPersonaje[0][3]);
        animacionCaminata.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMovimiento = EstadoMovimiento.INICIANDO;
        estadoSalto = EstadoSalto.EN_PISO;
    }


    public void render(SpriteBatch batch) {
        switch (estadoMovimiento) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacionCaminata.getKeyFrame(timerAnimacion);
                    if (estadoMovimiento == EstadoMovimiento.MOV_IZQUIERDA) {
                        if (region.isFlipX()) {
                            region.flip(true, false);
                        }
                    } else if (estadoMovimiento == EstadoMovimiento.MOV_DERECHA) {
                        if (!region.isFlipX()) {
                            region.flip(true, false);
                        }
                    }
                    batch.draw(region, sprite.getX(), sprite.getY());

                break;
            case INICIANDO:
            case QUIETO:
                sprite.draw(batch);
                break;
        }

    }

    public void actualizar() {
        float nuevaX = sprite.getX();
        if(first){
            estadoMovimiento=EstadoMovimiento.MOV_DERECHA;
        }

        switch (estadoMovimiento) {
            case MOV_DERECHA:
                first=false;
                nuevaX += VELOCIDAD_X;
                sprite.setX(nuevaX);
                mov+=VELOCIDAD_X;

                if(mov>= movLim){
                    mov=0;
                    estadoMovimiento = EstadoMovimiento.MOV_IZQUIERDA;
                }

                break;
            case MOV_IZQUIERDA:
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

    public void caer() {
        sprite.setY(sprite.getY() + VELOCIDAD_Y);
    }

    public void actualizarSalto() {
        float y = V0 * tiempoSalto - G_2 * tiempoSalto * tiempoSalto;
        if (tiempoSalto > tiempoVuelo / 2) {
            estadoSalto = EstadoSalto.BAJANDO;
        }
        tiempoSalto += 10 * Gdx.graphics.getDeltaTime();
        sprite.setY(yInicial + y);
        if (y < 0) {
            sprite.setY(yInicial);
            estadoSalto = EstadoSalto.EN_PISO;
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public void setPosicion(float x, int y) {
        sprite.setPosition(x,y);
    }

    public EstadoMovimiento getEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento() {
        this.estadoMovimiento = EstadoMovimiento.QUIETO;
    }

    public void setEstadoSalto() {
        this.estadoSalto = EstadoSalto.CAIDA_LIBRE;
    }



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
        CAIDA_LIBRE
    }
}
