package pt.projeto.batalhadereinos.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

import pt.projeto.batalhadereinos.BatalhaDeReinos;

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
		game.batch.setProjectionMatrix(camera.combined);

		game.batch.begin();
		game.font.draw(game.batch, "Welcome to Drop!!! ", 100, 150);
		game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
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
