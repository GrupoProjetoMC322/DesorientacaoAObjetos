package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MyButton extends ImageButton {

    public static ImageButtonStyle generateStyle(String path) {
        Texture buttonTexture = new Texture(Gdx.files.internal(path));
        TextureRegion textureRegion = new TextureRegion(buttonTexture);
        TextureRegionDrawable drawable = new TextureRegionDrawable(textureRegion);
        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = drawable;
        return buttonStyle;
    }

    public MyButton(ImageButtonStyle buttonStyle, float x, float y, final IButtonCommand buttonCommand) {
        super(buttonStyle);
        setPosition(x, y);

        addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                buttonCommand.execute();
                Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
                return true;
            }
        });
    }

    public boolean isMouseOver() {
        return Gdx.input.getX() > this.getX() && Gdx.input.getX() < this.getX() + this.getWidth() &&
                Gdx.graphics.getHeight() - Gdx.input.getY() > this.getY() &&
                Gdx.graphics.getHeight() - Gdx.input.getY() < this.getY() + this.getHeight();
    }
    
}
