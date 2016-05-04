package itesm.mx.garasu;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Personaje
{
    public static final float VELOCIDAD_Y = -4f;
    public static final float VELOCIDAD_X = 7;

    private final Sprite sprite;

    private final Animation animacionCaminata;
    private final Animation animacionSalto;
    private final Animation animacionAtaque;
    private final Animation animacionQuieto;

    private float timerAnimacion;

    private EstadoMovimiento estadoMovimiento;
    private EstadoSalto estadoSalto;

    private static final float V0 = 50;
    private static final float G = 9.81f;
    private static final float G_2 = G/2;
    private float yInicial;
    private float tiempoVuelo;
    private float tiempoSalto;
    private static boolean der = true;

    public Personaje(Texture textura) {
        TextureRegion texturaCompleta = new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje = texturaCompleta.split(119,140);
        animacionQuieto = new Animation(0.12f,texturaPersonaje[0][0]);
        animacionCaminata = new Animation(0.12f,texturaPersonaje[0][0],
                texturaPersonaje[0][1], texturaPersonaje[0][2],texturaPersonaje[0][3] );
        animacionAtaque =    new Animation(0.12f,texturaPersonaje[0][4],
                texturaPersonaje[0][5], texturaPersonaje[0][6],texturaPersonaje[0][7] );
        animacionSalto =    new Animation(0.12f,
                 texturaPersonaje[0][9]);
        animacionSalto.setPlayMode(Animation.PlayMode.LOOP);
        animacionAtaque.setPlayMode(Animation.PlayMode.LOOP);
        animacionCaminata.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion = 0;
        sprite = new Sprite(texturaPersonaje[0][0]);
        estadoMovimiento = EstadoMovimiento.INICIANDO;
        estadoSalto = EstadoSalto.EN_PISO;
    }

    public boolean getDer(){
        return der;
    }
    public void render(SpriteBatch batch) {

        switch (estadoMovimiento) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion region = animacionCaminata.getKeyFrame(timerAnimacion);
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
                    batch.draw(region, sprite.getX(), sprite.getY());
                }
                break;
            case ATAQUE:

                timerAnimacion += Gdx.graphics.getDeltaTime();

                TextureRegion regionAtaque = animacionAtaque.getKeyFrame(timerAnimacion);
                if (der && regionAtaque.isFlipX() ) {
                    regionAtaque.flip(true, false);
                } else if (!der && !regionAtaque.isFlipX()) {
                    regionAtaque.flip(true, false);


                }
                batch.draw(regionAtaque, sprite.getX(), sprite.getY());
                break;
            case INICIANDO:
            case QUIETO:
                timerAnimacion += Gdx.graphics.getDeltaTime();
                TextureRegion regionQuieto = animacionQuieto.getKeyFrame(timerAnimacion);
                if (estadoSalto == EstadoSalto.EN_PISO && estadoMovimiento != EstadoMovimiento.ATAQUE) {
                    if (der && regionQuieto.isFlipX()) {
                        regionQuieto.flip(true, false);
                    } else if (!der && !regionQuieto.isFlipX()) {
                        regionQuieto.flip(true, false);
                    }

                    batch.draw(regionQuieto, sprite.getX(), sprite.getY());
                    break;
                }
        }
        if((estadoSalto==EstadoSalto.BAJANDO||estadoSalto==EstadoSalto.SUBIENDO||estadoSalto==EstadoSalto.CAIDA_LIBRE)&&estadoMovimiento!=EstadoMovimiento.ATAQUE){
            timerAnimacion += Gdx.graphics.getDeltaTime();
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

    public void actualizar() {
        float nuevaX = sprite.getX();
        switch (estadoMovimiento) {
            case MOV_DERECHA:
                nuevaX += VELOCIDAD_X;
                sprite.setX(nuevaX);
                der=true;
                break;
            case MOV_IZQUIERDA:

                der=false;
                nuevaX -= VELOCIDAD_X;
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
        float y = (V0 * tiempoSalto) - G_2 * tiempoSalto * tiempoSalto;
        if (tiempoSalto > tiempoVuelo / 2) {
            estadoSalto = EstadoSalto.BAJANDO;
        }
        float nuevaX = sprite.getX();

        switch (estadoMovimiento) {
            case MOV_DERECHA:
                nuevaX -= VELOCIDAD_X;
                sprite.setX(nuevaX);
                break;
            case MOV_IZQUIERDA:
                nuevaX += VELOCIDAD_X;
                if (nuevaX>=0) {
                    sprite.setX(nuevaX);
                }
                break;
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
    public void setEstadoMovimiento(EstadoMovimiento estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public void setEstadoSalto(EstadoSalto estadoSalto) {
        this.estadoSalto = estadoSalto;
    }

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
        CAIDA_LIBRE
    }
}
