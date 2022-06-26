package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyLabel extends Label {
    public static final Color BLACK = Color.BLACK;
    public static final Color YELLOW = Color.valueOf("E5A900");

    public static LabelStyle generateStyle(int size, Color color) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/MedievalSharp-Regular.ttf"));
        FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();
        fontParameter.size = size;
        fontParameter.color = color;
        BitmapFont font = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();
    
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = font;
        return labelStyle;
    }

    public MyLabel(CharSequence text, LabelStyle style, float x, float y) {
        super(text, style);
        setPosition(x, y);
    }

    public void update(CharSequence newText) {
        setText(newText);
    }

    public void update(int value) {
        setText(value);
    }
    
}
