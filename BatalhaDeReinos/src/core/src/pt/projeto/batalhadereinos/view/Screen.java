package pt.projeto.batalhadereinos.view;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.IScreenMediator;

public abstract class Screen extends ScreenAdapter {
    protected final BatalhaDeReinos game;
    protected IScreenMediator gameScreenMediator;
	protected SpriteBatch batch;
	protected BitmapFont font;
	protected OrthographicCamera camera;
    protected Stage stage;

    protected Texture bgImg;
    protected ArrayList<MyButton> buttons;


    public Screen(BatalhaDeReinos game, IScreenMediator gameScreenMediator) {
        this.game = game;
        this.gameScreenMediator = gameScreenMediator;
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new Stage();
        camera = new OrthographicCamera();
		camera.setToOrtho(false, 1440, 1024);

        buttons = new ArrayList<MyButton>();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(bgImg, 0, 0);
		batch.end();

        stage.draw();

        for (MyButton btn : buttons) {
            if (btn.isMouseOver()) {
                Gdx.graphics.setSystemCursor(SystemCursor.Hand);
                break;
            } else {
                Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
            }
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void setBackgroundImage(String path) {
        this.bgImg = new Texture(Gdx.files.internal(path));
    }

    public void setBackgroundImage(Texture texture) {
        this.bgImg = texture;
    }

    public MyButton createButton(String path, float x, float y, final IButtonCommand buttonCommand) {
        ImageButtonStyle buttonStyle = MyButton.generateStyle(path);
        return this.createButton(buttonStyle, x, y, buttonCommand);
    }

    public MyButton createButton(ImageButtonStyle buttonStyle, float x, float y, final IButtonCommand buttonCommand) {
        MyButton newButton = new MyButton(buttonStyle, x, y, buttonCommand);
        stage.addActor(newButton);
        buttons.add(newButton);
        return newButton;
    }

    public MyLabel createLabel(CharSequence text, Color color, float x, float y, int size) {
        LabelStyle labelStyle = MyLabel.generateStyle(size, color);
        MyLabel newLabel = new MyLabel(text, labelStyle, x, y);
        stage.addActor(newLabel);
        return newLabel;
    }
}
