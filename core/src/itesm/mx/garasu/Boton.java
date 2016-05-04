package itesm.mx.garasu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


class Boton
{
    private final Sprite sprite;              // Imagen
    private final Rectangle rectColision;     // Rectangulo para verificar colisiones o touch

    public Boton(Texture textura) {
        sprite = new Sprite(textura);
        // El rectángulo de colisión siempre está 'sobre' el sprite
        rectColision = new Rectangle(sprite.getX(), sprite.getY(),
                sprite.getWidth(), sprite.getHeight());
    }

    public void setPosicion(float x, float y) {
        sprite.setPosition(x, y);
        rectColision.setPosition(x,y);
    }

    public Rectangle getRectColision() {
        return rectColision;
    }

    // Dibuja el botón
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getY() {
        return sprite.getY();
    }

    public float getX() {
        return sprite.getX();
    }

    public void setAlfa() {
        sprite.setAlpha(0.7f);
    }

    public boolean contiene(float x, float y) {
        return rectColision.contains(x,y);
    }
}
