package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

import pt.projeto.batalhadereinos.BatalhaDeReinos;
import pt.projeto.batalhadereinos.controller.GameController;

public class MainMenuScreen implements Screen{
    final BatalhaDeReinos game;

	OrthographicCamera camera;

	public MainMenuScreen(final BatalhaDeReinos game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		


		camera.update();

		Stage stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Texture myTexture = new Texture(Gdx.files.internal("botao.png"));

		TextureRegion myTextureRegion = new TextureRegion(myTexture);
		TextureRegionDrawable myTexRegionDrawable = new TextureRegionDrawable(myTextureRegion);
		ImageButton myButton = new ImageButton(myTexRegionDrawable);

		myButton.addListener(new InputListener() {
			@Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                new GameController().start(game);
				return true;
            }
		});

        stage.addActor(myButton);


		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
		myButton.draw(game.batch, 1);
		game.batch.end();
	}

	@Override
	public void pause(){}

	@Override
	public void show(){}

	@Override
	public void resume(){}

	@Override
	public void dispose(){}

	@Override
	public void hide(){}

	@Override
	public void resize(int width, int height){}
}
